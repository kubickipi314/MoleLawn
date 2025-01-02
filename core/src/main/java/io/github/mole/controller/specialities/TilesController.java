package io.github.mole.controller.specialities;

import io.github.mole.controller.Helper;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.MoveDirection.UP;
import static io.github.mole.utils.ObjectType.CANAL;
import static io.github.mole.utils.ObjectType.HILL;
import static io.github.mole.utils.TileType.*;

public class TilesController {

    Board board;
    Helper helper;
    GamePresentable gamePresentable;
    public TilesController(Board board, Helper helper) {
        this.board = board;
        this.helper = helper;
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }

    public void handleDigging(int moleX, int moleY, MoveDirection direction){
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
        if (helper.isPositionOnBoard(front) && front.y()>0) {
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

}
