package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.controller.specialities.HillsController;
import io.github.mole.controller.specialities.SpadeController;
import io.github.mole.controller.specialities.TilesController;
import io.github.mole.controller.specialities.WormsController;
import io.github.mole.model.Board;
import io.github.mole.utils.*;

import java.util.Random;

import static io.github.mole.utils.MoveDirection.*;
import static io.github.mole.utils.MoveStyle.*;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class GameController implements GameControllable {
    GamePresentable gamePresentable;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    Board board;
    int moleX;
    int moleY;
    int energyLevel;
    TilesController tilesController;
    SpadeController spadeController;
    HillsController hillsController;
    WormsController wormsController;
    Helper helper;

    public GameController() {
        board = new Board();
        helper = new Helper();

        tilesController = new TilesController(board, helper);
        spadeController = new SpadeController(board);
        hillsController = new HillsController(board);
        wormsController = new WormsController(board);

        moleX = CONST.MOLE_POSITION_X;
        moleY = CONST.MOLE_POSITION_Y;
        energyLevel = CONST.ENERGY_LEVEL;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
        tilesController.setPresentable(gamePresentable);
        spadeController.setPresentable(gamePresentable);
        hillsController.setPresentable(gamePresentable);
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
        BoardPosition molePosition = new BoardPosition(moleX, moleY);
        gamePresentable.setMolePosition(molePosition);
    }

    public void makeMove(MoveDirection direction) {

        int destinationX = moleX;
        int destinationY = moleY;

        MoveStyle moveStyle;

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

        //MOVING
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            moleX = destinationX;
            moleY = destinationY;

            //DIGGING
            if (board.getType(moleX,moleY).equals(DIRT)) {
                moveStyle = DIGGING;
                tilesController.handleDigging(moleX, moleY, direction);
            }
            //FREE MOVE
            else {
                moveStyle = FREE;
            }
        }
        //NOT MOVING
        else {
            moveStyle = DIGGING;
        }

        insertRandomWorm();
        tryEatingWorm();

        BoardPosition destination = new BoardPosition(moleX, moleY);
        gamePresentable.moveMole(destination, direction, moveStyle);

    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= width) return false;
        if (destinationY < 0 || destinationY >= height) return false;

        if (board.getType(destinationX,destinationY).equals(STONE)) return false;
        return true;
    }


    private void insertRandomWorm(){
        Random random = new Random();

        int x = random.nextInt(width);
        int y = random.nextInt(height);
        tryInsertWorm(x, y);
    }

    private void tryInsertWorm(int x, int y){
        if (x == moleX && y == moleY) return;
        if (board.getType(x,y).equals(TUNNEL)){
            if (!board.isObject(x,y,WORM)){
                board.addObject(x,y,WORM);
                gamePresentable.insertObject(WORM, new BoardPosition(x, y));
            }
        }
    }

    private void tryEatingWorm(){
        if (board.isObject(moleX, moleY, WORM)){
            board.removeObject(moleX, moleY, WORM);
            gamePresentable.deleteObject(WORM, new BoardPosition(moleX, moleY));
        }
    }
}
