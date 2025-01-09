package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.controller.specialities.BootController;
import io.github.mole.controller.specialities.DiggingController;
import io.github.mole.controller.specialities.SpadeController;
import io.github.mole.controller.specialities.WormsController;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.MoveDirection.NONE;
import static io.github.mole.utils.MoveStyle.DIGGING;
import static io.github.mole.utils.MoveStyle.FREE;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.DIRT;
import static io.github.mole.utils.TileType.STONE;

public class GameController implements GameControllable {
    GamePresentable gamePresentable;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    Board board;
    Mole mole;

    DiggingController diggingController;
    SpadeController spadeController;
    WormsController wormsController;
    BootController bootController;
    Helper helper;

    public GameController() {
        board = new Board();
        mole = new Mole();

        helper = new Helper(board, mole);
        diggingController = new DiggingController(board, mole, helper);
        spadeController = new SpadeController(board, mole);
        wormsController = new WormsController(board, mole);
        bootController = new BootController(board, mole, helper);

        diggingController.setSpade(spadeController);
        diggingController.setWorms(wormsController);
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
        diggingController.setPresentable(gamePresentable);
        spadeController.setPresentable(gamePresentable);
        wormsController.setPresentable(gamePresentable);
        bootController.setPresentable(gamePresentable);
    }

    public void initializePresentable() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileType type = board.getType(x, y);
                BoardPosition position = new BoardPosition(x, y);
                gamePresentable.setTile(position, type);
            }
        }
        gamePresentable.setMolePosition(mole.getPosition());
        gamePresentable.setEnergyLevel(mole.getEnergyLevel());
    }

    public void makeMove(MoveDirection direction) {

        int destinationX = mole.getX();
        int destinationY = mole.getY();

        switch (direction) {
            case LEFT:
                destinationX--;
                break;
            case RIGHT:
                destinationX++;
                break;
            case UP:
                destinationY--;
                break;
            case DOWN:
                destinationY++;
                break;
        }

        spadeController.preMoveHandle();
        bootController.preMoveHandle();

        MoveStyle moveStyle;
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            mole.changePosition(destinationX, destinationY);

            if (board.getType(mole.getPosition()).equals(DIRT)) {
                moveStyle = DIGGING;
                diggingController.handleDigging(direction);
            } else {
                moveStyle = FREE;
            }
        } else {
            moveStyle = DIGGING;
        }

        spadeController.postMoveHandle();
        bootController.postMoveHandle();
        wormsController.postMoveHandle();
        handleEncounters();

        gamePresentable.moveMole(mole.getPosition(), direction, moveStyle);
    }

    @Override
    public void retry() {
        System.out.println("Retry!");
    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= width) return false;
        if (destinationY < 0 || destinationY >= height) return false;

        BoardPosition destination = new BoardPosition(destinationX, destinationY);
        if (board.isObject(destination,BOOT)) return false;
        if (board.getType(destinationX, destinationY).equals(STONE)) return false;
        return true;
    }

    public void handleEncounters() {
        BoardPosition position = mole.getPosition();
        if (board.isAnyObject(position)) {
            if (board.isObject(position, WORM)) {
               wormsController.eatWorm();
            }
            if (board.isObject(position, SPADE) || board.isObject(helper.getBottomPosition(), SPADE)) {
                System.out.println("die from Spade");
                gamePresentable.moleDie();
            }
            if (board.isObject(position, BOOT)) {
                System.out.println("die from Boot");
                gamePresentable.moleDie();
            }
        }
    }

}
