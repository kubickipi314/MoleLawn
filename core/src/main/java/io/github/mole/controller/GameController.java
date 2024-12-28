package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Tile;
import io.github.mole.utils.*;

import static io.github.mole.utils.MoveDirection.*;
import static io.github.mole.utils.MoveStyle.*;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class GameController implements GameControllable {
    GamePresentable gamePresentable;
    Tile[][] board;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    int positionX;
    int positionY;

    boolean isSpadeAttack;
    boolean isSpadeBacked;
    int spadeAttackX;


    public GameController() {
        board = new Tile[CONST.BOARD_HEIGHT][CONST.BOARD_WIDTH];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileType type = DIRT;
                if (i == 0) type = AIR;
                board[i][j] = new Tile(type);
            }
        }

        positionX = 2;
        positionY = height - 1;

        isSpadeAttack = false;
        isSpadeBacked = false;
    }

    public void setGamePresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void initializePresentable() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileType type = board[i][j].getType();
                BoardPosition position = new BoardPosition(j, i);
                gamePresentable.setTile(position, type);
            }
        }
        BoardPosition molePosition = new BoardPosition(positionX, positionY);
        gamePresentable.setMolePosition(molePosition);
    }

    public void makeMove(MoveDirection direction) {

        int destinationX = positionX;
        int destinationY = positionY;

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
        //PLANNED ACTIONS
        if (isSpadeBacked){
            isSpadeBacked = false;
            BoardPosition spadePosition = new BoardPosition(spadeAttackX, 1);
            gamePresentable.deleteObject(SPADE, spadePosition);
        }
        if (isSpadeAttack){
            isSpadeAttack = false;
            isSpadeBacked = true;
            BoardPosition spadePosition = new BoardPosition(spadeAttackX, 1);
            gamePresentable.insertObject(SPADE, spadePosition);
        }

        //MOVING
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            positionX = destinationX;
            positionY = destinationY;
            Tile destTile = board[height - 1 - positionY][positionX];
            //DIGGING
            if (destTile.getType().equals(DIRT)) {
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

        BoardPosition destination = new BoardPosition(positionX, positionY);
        gamePresentable.moveMole(destination, direction, moveStyle);

        if (isMoleDead()) {
            //animate death
        }
    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= width) return false;
        if (destinationY < 0 || destinationY >= height) return false;

        Tile destinationTile = board[height - 1 - destinationY][destinationX];
        if (destinationTile.getType().equals(STONE)) return false;
        return true;
    }

    private boolean isMoleDead() {
        return false;
    }

    private void handleDigging(MoveDirection direction){
        BoardPosition position = new BoardPosition(positionX, positionY);
        board[height - 1 - positionY][positionX].setType(TUNNEL);
        gamePresentable.changeTile(position, direction, TUNNEL);

        BoardPosition leftPosition = getLeftPosition(direction);
        if (isPositionOnBoard(leftPosition)) {
            if (isTunnel(leftPosition)) {
                board[height - 1 - leftPosition.y()][leftPosition.x()].setType(DIRT);
                gamePresentable.changeTile(leftPosition, getLeftDirection(direction), DIRT);
            }
            if (isAir(leftPosition)) {
                if (!board[height - 1 - leftPosition.y()][leftPosition.x()].isObject(CANAL)
                    && !board[height - 1 - leftPosition.y()][leftPosition.x()].isObject(HILL)) {
                    board[height - 1 - leftPosition.y()][leftPosition.x()].addObject(CANAL);
                    gamePresentable.insertObject(CANAL, leftPosition);
                }
            }
        }

        BoardPosition rightPosition = getRightPosition(direction);
        if (isPositionOnBoard(rightPosition)) {
            if (isTunnel(rightPosition)) {
                board[height - 1 - rightPosition.y()][rightPosition.x()].setType(DIRT);
                gamePresentable.changeTile(rightPosition, getRightDirection(direction), DIRT);
            }
            if (isAir(rightPosition)) {
                if (!board[height - 1 - rightPosition.y()][rightPosition.x()].isObject(CANAL)
                && !board[height - 1 - rightPosition.y()][rightPosition.x()].isObject(HILL)) {
                    board[height - 1 - rightPosition.y()][rightPosition.x()].addObject(CANAL);
                    gamePresentable.insertObject(CANAL, leftPosition);
                }
            }
        }

        BoardPosition frontPosition = getFrontPosition(direction);
        if (isPositionOnBoard(frontPosition) && frontPosition.y()<height-1) {
            if (isTunnel(frontPosition)) {
                board[height - 1 - frontPosition.y()][frontPosition.x()].setType(DIRT);
                gamePresentable.changeTile(frontPosition, direction, DIRT);
            }
        }

        if (direction.equals(UP)){
            BoardPosition upPosition = getUpperPosition();
            if (isPositionOnBoard(upPosition)){

                if (board[height - 1 - upPosition.y()][upPosition.x()].getType().equals(AIR)) {
                    if (!board[height - 1 - upPosition.y()][upPosition.x()].isObject(HILL)) {
                        board[height - 1 - upPosition.y()][upPosition.x()].addObject(HILL);
                        gamePresentable.insertObject(HILL, upPosition);

                        isSpadeAttack = true;
                        spadeAttackX = positionX;

                        if (!board[height - 1 - upPosition.y()][upPosition.x()].isObject(CANAL)) {
                            board[height - 1 - upPosition.y()][upPosition.x()].removeObject(CANAL);
                            gamePresentable.deleteObject(CANAL, upPosition);
                        }
                    }
                }
            }
        }
    }

    private BoardPosition getLeftPosition(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(positionX, positionY - 1);
            case RIGHT -> new BoardPosition(positionX, positionY + 1);
            case UP -> new BoardPosition(positionX - 1, positionY);
            case DOWN -> new BoardPosition(positionX + 1, positionY);
            default -> null;
        };
    }

    private BoardPosition getRightPosition(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(positionX, positionY + 1);
            case RIGHT -> new BoardPosition(positionX, positionY - 1);
            case UP -> new BoardPosition(positionX + 1, positionY);
            case DOWN -> new BoardPosition(positionX - 1, positionY);
            default -> null;
        };
    }

    private BoardPosition getFrontPosition(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(positionX - 1, positionY);
            case RIGHT -> new BoardPosition(positionX + 1, positionY);
            case UP -> new BoardPosition(positionX, positionY + 1);
            case DOWN -> new BoardPosition(positionX, positionY - 1);
            default -> null;
        };
    }

    private BoardPosition getUpperPosition() {
        return new BoardPosition(positionX, positionY + 1);
    }

    private boolean isTunnel(BoardPosition position) {
        return board[height - 1 - position.y()][position.x()].getType().equals(TUNNEL);
    }

    private boolean isAir(BoardPosition position) {
        return board[height - 1 - position.y()][position.x()].getType().equals(AIR);
    }

    private MoveDirection getLeftDirection(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
            case UP -> LEFT;
            case NONE -> NONE;
        };
    }

    private MoveDirection getRightDirection(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> UP;
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case NONE -> NONE;
        };
    }

    private boolean isPositionOnBoard(BoardPosition position){
        if (position.x() < 0 || position.x() >= width) return false;
        if (position.y() < 0 || position.y() >= height) return false;
        return true;
    }
}
