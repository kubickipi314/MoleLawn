package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.controller.specialities.HillsController;
import io.github.mole.controller.specialities.SpadeController;
import io.github.mole.controller.specialities.DiggingController;
import io.github.mole.controller.specialities.WormsController;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.*;


import static io.github.mole.utils.MoveDirection.*;
import static io.github.mole.utils.MoveStyle.*;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class GameController implements GameControllable {
    GamePresentable gamePresentable;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    Board board;
    Mole mole;

    DiggingController diggingController;
    SpadeController spadeController;
    WormsController wormsController;
    Helper helper;

    public GameController() {
        board = new Board();
        mole = new Mole();

        helper = new Helper(board, mole);
        diggingController = new DiggingController(board, mole, helper);
        spadeController = new SpadeController(board, mole);
        wormsController = new WormsController(board, mole);

        diggingController.setSpade(spadeController);
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
        diggingController.setPresentable(gamePresentable);
        spadeController.setPresentable(gamePresentable);
        wormsController.setPresentable(gamePresentable);
    }

    public void initializePresentable() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileType type = board.getType(x,y);
                BoardPosition position = new BoardPosition(x, y);
                gamePresentable.setTile(position, type);
            }
        }
        gamePresentable.setMolePosition(mole.getPosition());
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

        spadeController.handleSpade();

        MoveStyle moveStyle;
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            mole.changePosition(destinationX, destinationY);

            if (board.getType(mole.getPosition()).equals(DIRT)) {
                moveStyle = DIGGING;
                diggingController.handleDigging(direction);
            }
            else {
                moveStyle = FREE;
            }
        }
        else {
            moveStyle = DIGGING;
        }

        wormsController.handleWorms();

        handleEncounters();

        gamePresentable.moveMole(mole.getPosition(), direction, moveStyle);
    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= width) return false;
        if (destinationY < 0 || destinationY >= height) return false;

        if (board.getType(destinationX,destinationY).equals(STONE)) return false;
        return true;
    }

    public void handleEncounters(){
        BoardPosition position = mole.getPosition();
        if (board.isAnyObject(position)){
            if (board.isObject(position, WORM)){
                board.removeObject(position, WORM);
                gamePresentable.deleteObject(WORM, position);
            }
            if (board.isObject(position, SPADE)){
                System.out.println("die from Spade");
            }
        }
    }

}
