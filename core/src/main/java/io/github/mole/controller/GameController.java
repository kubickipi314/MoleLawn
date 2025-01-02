package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.controller.specialities.HillsController;
import io.github.mole.controller.specialities.SpadeController;
import io.github.mole.controller.specialities.WormsController;
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

    SpadeController spadeController;
    HillsController hillsController;
    WormsController wormsController;
    Helper helper;

    public GameController() {
        spadeController = new SpadeController();
        hillsController = new HillsController();
        wormsController = new WormsController();
        helper = new Helper();

        board = new Board();
        moleX = CONST.MOLE_POSITION_X;
        moleY = CONST.MOLE_POSITION_Y;
        energyLevel = CONST.ENERGY_LEVEL;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
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
                destinationY++;
                break;
            case DOWN:
                destinationY--;
                break;
        }

        //MOVING
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            moleX = destinationX;
            moleY = destinationY;

            //DIGGING
            if (board.getType(moleX,moleY).equals(DIRT)) {
                moveStyle = DIGGING;
                handleDigging(direction);
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

    private void handleDigging(MoveDirection direction){
        BoardPosition position = new BoardPosition(moleX, moleY);
        board.setType(moleX, moleY, TUNNEL);
        gamePresentable.changeTile(position, direction, TUNNEL);

        BoardPosition left = helper.getLeftPosition(moleX, moleY, direction);
        if (helper.isPositionOnBoard(left)) {
            if (board.getType(left).equals(TUNNEL)) {
                board.setType(left, DIRT);
                gamePresentable.changeTile(left, helper.getLeftDirection(direction), DIRT);
            }
            if (board.getType(left).equals(AIR)) {
                if (!board.isObject(left, CANAL)
                    && !board.isObject(left, HILL)) {
                    board.addObject(left, CANAL);
                    gamePresentable.insertObject(CANAL, left);
                }
            }
        }

        BoardPosition right = helper.getRightPosition(moleX, moleY, direction);
        if (helper.isPositionOnBoard(right)) {
            if (board.getType(right).equals(TUNNEL)) {
                board.setType(right.x(), right.y(), DIRT);
                gamePresentable.changeTile(right, helper.getRightDirection(direction), DIRT);
            }
            if (board.getType(right).equals(AIR)) {
                if (!board.isObject(right, CANAL)
                && !board.isObject(right, HILL)) {
                    board.addObject(right, CANAL);
                    gamePresentable.insertObject(CANAL, left);
                }
            }
        }

        BoardPosition front = helper.getFrontPosition(moleX, moleY, direction);
        if (helper.isPositionOnBoard(front) && front.y()<height-1) {
            if (board.getType(front).equals(TUNNEL)) {
                board.setType(front, DIRT);
                gamePresentable.changeTile(front, direction, DIRT);
            }
        }

        if (direction.equals(UP)){
            BoardPosition upper = helper.getUpperPosition(moleX, moleY);
            if (helper.isPositionOnBoard(upper)){

                if (board.getType(upper).equals(AIR)) {
                    if (!board.isObject(upper, HILL)) {
                        board.addObject(upper, HILL);
                        gamePresentable.insertObject(HILL, upper);


                        if (board.isObject(upper, CANAL)) {
                            board.removeObject(upper, CANAL);
                            gamePresentable.deleteObject(CANAL, upper);
                        }
                    }
                }
            }
        }
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
