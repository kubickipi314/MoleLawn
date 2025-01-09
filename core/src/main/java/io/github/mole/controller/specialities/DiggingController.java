package io.github.mole.controller.specialities;

import io.github.mole.controller.Helper;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class DiggingController {

    Board board;
    Mole mole;
    Helper helper;
    GamePresentable gamePresentable;
    SpadeController spadeController;
    WormsController wormsController;
    public DiggingController(Board board, Mole mole, Helper helper) {
        this.board = board;
        this.mole = mole;
        this.helper = helper;
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }

    public void handleDigging(MoveDirection direction){

        boolean clearDigging = true;


        board.setType(mole.getPosition(), TUNNEL);
        gamePresentable.changeTile(mole.getPosition(), direction, TUNNEL);


        BoardPosition left = helper.getLeftPosition(direction);
        if (helper.isPositionOnBoard(left)) {
            if (board.getType(left).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(left, helper.getLeftDirection(direction));
            tryMakeCanal(left);
        }

        BoardPosition right = helper.getRightPosition(direction);
        if (helper.isPositionOnBoard(right)) {
            if (board.getType(right).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(right, helper.getRightDirection(direction));
            tryMakeCanal(right);
        }

        BoardPosition front = helper.getFrontPosition(direction);
        if (helper.isPositionOnBoard(front)) {
            if (board.getType(front).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(front, direction);
            tryMakeHill(front);
        }

        if (clearDigging){
            int moleEnergy = Math.max(mole.getEnergyLevel() - 1, 0);
            mole.setEnergyLevel(moleEnergy);
            gamePresentable.setEnergyLevel(moleEnergy);
        }
    }

    private void tryBuryTunnel(BoardPosition position, MoveDirection direction) {
        if (board.getType(position).equals(TUNNEL)) {
            board.setType(position, DIRT);
            gamePresentable.changeTile(position, direction, DIRT);
            if (board.isAnyObject(position)) {
                if (board.isObject(position, WORM)) {
                    wormsController.destroyWorm(position);
                }
            }
        }
    }

    private void tryMakeCanal(BoardPosition position){
        if (board.getType(position).equals(AIR)) {
            if (!board.isObject(position, CANAL)
                && !board.isObject(position, HILL)
                && !board.isObject(position, BOOT)) {
                board.addObject(position, CANAL);
                gamePresentable.insertObject(CANAL, position);
                spadeController.activateByCanal();
            }
        }
    }

    private void tryMakeHill(BoardPosition position){
        if (board.getType(position).equals(AIR)) {
            if (!board.isObject(position, HILL)
                && !board.isObject(position, BOOT)) {
                board.addObject(position, HILL);
                gamePresentable.insertObject(HILL, position);
                spadeController.activateByHill();

                if (board.isObject(position, CANAL)) {
                    board.removeObject(position, CANAL);
                    gamePresentable.deleteObject(CANAL, position);
                }
            }
        }
    }

    public void setSpade(SpadeController spadeController) {
        this.spadeController = spadeController;
    }
    public void setWorms(WormsController wormsController) {
        this.wormsController = wormsController;
    }
}
