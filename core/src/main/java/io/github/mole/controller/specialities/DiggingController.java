package io.github.mole.controller.specialities;

import io.github.mole.controller.Helper;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.MoveDirection.UP;
import static io.github.mole.utils.ObjectType.CANAL;
import static io.github.mole.utils.ObjectType.HILL;
import static io.github.mole.utils.TileType.*;

public class DiggingController {

    Board board;
    Mole mole;
    Helper helper;
    GamePresentable gamePresentable;
    SpadeController spadeController;
    public DiggingController(Board board, Mole mole, Helper helper) {
        this.board = board;
        this.mole = mole;
        this.helper = helper;
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }

    public void handleDigging(MoveDirection direction){
        board.setType(mole.getPosition(), TUNNEL);
        gamePresentable.changeTile(mole.getPosition(), direction, TUNNEL);

        BoardPosition left = helper.getLeftPosition(direction);
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
                    spadeController.activateByCanal();
                }
            }
        }

        BoardPosition right = helper.getRightPosition(direction);
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
                    spadeController.activateByCanal();
                }
            }
        }

        BoardPosition front = helper.getFrontPosition(direction);
        if (helper.isPositionOnBoard(front) && front.y()>0) {
            if (board.getType(front).equals(TUNNEL)) {
                board.setType(front, DIRT);
                gamePresentable.changeTile(front, direction, DIRT);
            }
        }

        if (direction.equals(UP)){
            BoardPosition upper = helper.getUpperPosition();
            if (helper.isPositionOnBoard(upper)){

                if (board.getType(upper).equals(AIR)) {
                    if (!board.isObject(upper, HILL)) {
                        board.addObject(upper, HILL);
                        gamePresentable.insertObject(HILL, upper);
                        spadeController.activateByHill();


                        if (board.isObject(upper, CANAL)) {
                            board.removeObject(upper, CANAL);
                            gamePresentable.deleteObject(CANAL, upper);
                        }
                    }
                }
            }
        }

    }

    public void setSpade(SpadeController spadeController) {
        this.spadeController = spadeController;
    }
}
