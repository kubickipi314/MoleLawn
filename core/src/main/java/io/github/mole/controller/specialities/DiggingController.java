package io.github.mole.controller.specialities;

import io.github.mole.controller.PositionHelper;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class DiggingController {

    Board board;
    Mole mole;
    PositionHelper positionHelper;
    GamePresentable gamePresentable;
    SpadeController spadeController;
    WormsController wormsController;
    public DiggingController(Board board, Mole mole, PositionHelper positionHelper) {
        this.board = board;
        this.mole = mole;
        this.positionHelper = positionHelper;
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }

    public void handleDigging(MoveDirection direction){

        boolean clearDigging = true;


        board.setType(mole.getPosition(), TUNNEL);
        gamePresentable.changeTile(mole.getPosition(), direction, TUNNEL);


        BoardPosition left = positionHelper.getLeftPosition(direction);
        if (positionHelper.isPositionOnBoard(left)) {
            if (board.getType(left).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(left, positionHelper.getLeftDirection(direction));
            tryMakeCanal(left);
        }

        BoardPosition right = positionHelper.getRightPosition(direction);
        if (positionHelper.isPositionOnBoard(right)) {
            if (board.getType(right).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(right, positionHelper.getRightDirection(direction));
            tryMakeCanal(right);
        }

        BoardPosition front = positionHelper.getFrontPosition(direction);
        if (positionHelper.isPositionOnBoard(front)) {
            if (board.getType(front).equals(TUNNEL)) clearDigging = false;
            tryBuryTunnel(front, direction);
            tryMakeHill(front);
        }

        if (clearDigging){
            mole.changeEnergyLevel(-0.7f);
        }
        else {
            mole.changeEnergyLevel(-0.3f);
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
