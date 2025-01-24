package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.Main;
import io.github.mole.controller.specialities.*;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.*;

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

    Main main;

    DiggingController diggingController;
    SpadeController spadeController;
    WormsController wormsController;
    BootController bootController;
    PositionHelper positionHelper;
    AirController airController;

    public GameController(Main main) {
        this.main = main;

        MapLoader mapLoader = new MapLoader();
        board = new Board(mapLoader.loadMap("maps/map1.png"));
        mole = new Mole();

        positionHelper = new PositionHelper(board, mole);
        airController = new AirController(board, mole);

        diggingController = new DiggingController(board, mole, positionHelper);
        spadeController = new SpadeController(board, mole);
        wormsController = new WormsController(board, mole);
        bootController = new BootController(board, mole, positionHelper);

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

        gamePresentable.setAirMask(airController.getAirMask());
        gamePresentable.setAirLevel(mole.getAirLevel());
    }

    public void makeMove(MoveDirection direction) {
        mole.changeEnergyLevel(-0.1f);
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

        if (!direction.equals(NONE)){
            mole.changeEnergyLevel(-0.1f);
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
        handleEnergy();

        gamePresentable.moveMole(mole.getPosition(), direction, moveStyle);

        airController.update();
        gamePresentable.setAirMask(airController.getAirMask());

        mole.setAirLevel((mole.getAirLevel() + board.getAirLevel(mole.getX(), mole.getY()))/2);
        gamePresentable.setAirLevel(mole.getAirLevel());
        handleAir();
    }

    @Override
    public void retry() {
        main.retry();
        System.out.println("Retry!");
    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= width) return false;
        if (destinationY < 0 || destinationY >= height) return false;

        BoardPosition destination = new BoardPosition(destinationX, destinationY);
        if (board.isObject(destination,BOOT)) return false;
        return !board.getType(destination).equals(STONE);
    }

    private void handleEncounters() {
        BoardPosition position = mole.getPosition();
        if (board.isAnyObject(position)) {
            if (board.isObject(position, WORM)) {
               wormsController.eatWorm();
            }
            if (board.isObject(position, SPADE) || board.isObject(positionHelper.getBottomPosition(), SPADE)) {
                System.out.println("die from Spade");
                gamePresentable.moleDie(DeathType.SPADE);
            }
            if (board.isObject(position, BOOT)) {
                System.out.println("die from Boot");
                gamePresentable.moleDie(DeathType.BOOT);
            }
        }
    }

    private void handleEnergy(){
        gamePresentable.setEnergyLevel(mole.getEnergyLevel());

        if (mole.getEnergyLevel() <= 0) {
            System.out.println("die from Hunger");
            gamePresentable.moleDie(DeathType.HUNGER);
        }
    }

    private void handleAir(){
        if (mole.getAirLevel() <= 0.1f){
            System.out.println("die from Suffocation");
            gamePresentable.moleDie(DeathType.SUFFOCATION);
        }
    }

}
