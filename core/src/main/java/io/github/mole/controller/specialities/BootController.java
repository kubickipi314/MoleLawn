package io.github.mole.controller.specialities;

import io.github.mole.controller.PositionHelper;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.mole.utils.MoveDirection.DOWN;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.*;

public class BootController {
    Board board;
    Mole mole;
    PositionHelper positionHelper;
    GamePresentable gamePresentable;

    int activationCounter;
    boolean isBoot;
    boolean endBoot;
    int bootX;
    BoardPosition bootPosition1;
    BoardPosition bootPosition2;

    public BootController(Board board, Mole mole, PositionHelper positionHelper) {
        this.board = board;
        this.mole = mole;
        this.positionHelper = positionHelper;

        activationCounter = 8;
        isBoot = false;
        endBoot = false;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void preMoveHandle(){

    }
    public void postMoveHandle() {
        if (isBoot) {
            if (endBoot){
                takeBoot();
                isBoot = false;
                endBoot = false;
            }
            else {
                board.removeObject(bootPosition1, BOOT);
                board.removeObject(bootPosition2, BOOT);
                endBoot = true;
            }
        } else if (activationCounter == 0) {
            tryPutBoot();
            activationCounter = 15;
        } else {
            activationCounter--;
        }
    }

    private void takeBoot() {
        gamePresentable.deleteObject(BOOT, bootPosition1);
    }

    private void tryPutBoot() {
        tryGetPosition();
        if (isBoot) {
            destroyHill(bootPosition1);
            destroyHill(bootPosition2);
            insertBoot();
            BoardPosition buryPosition1 = new BoardPosition(bootX, 1);
            BoardPosition buryPosition2 = new BoardPosition(bootX - 1, 1);
            tryBuryTunnel(buryPosition1);
            tryBuryTunnel(buryPosition2);
        }
    }

    private void tryGetPosition() {
        List<Integer> hillsX = getHillsPositions();
        if (hillsX.isEmpty()) return;

        Random random = new Random();
        bootX = hillsX.get(random.nextInt(hillsX.size()));
        if (bootX == 0) bootX++;

        if (board.isObject(new BoardPosition(bootX, 1),SPADE)) return;
        if (board.isObject(new BoardPosition(bootX - 1, 1),SPADE)) return;

        bootPosition1 = new BoardPosition(bootX, 0);
        bootPosition2 = new BoardPosition(bootX - 1, 0);

        isBoot = true;
    }

    private void destroyHill(BoardPosition position) {

        if (board.isObject(position, HILL)) {
            board.removeObject(position, HILL);
            gamePresentable.deleteObject(HILL, position);
        }
        if (board.isObject(position, CANAL)) {
            board.removeObject(position, CANAL);
            gamePresentable.deleteObject(CANAL, position);
        }
    }

    private void insertBoot() {
        gamePresentable.insertObject(BOOT, bootPosition1);
        board.addObject(bootPosition1, BOOT);
        board.addObject(bootPosition2, BOOT);
    }

    private List<Integer> getHillsPositions() {
        List<Integer> hillsPositions = new ArrayList<>();
        for (int x = 0; x < board.getWidth(); ++x) {
            if (board.isObject(new BoardPosition(x, 0), HILL)) {
                hillsPositions.add(x);
            }
        }
        return hillsPositions;
    }

    public void tryBuryTunnel(BoardPosition position) {
        if (board.getType(position).equals(TUNNEL)) {
            if (!mole.getPosition().equals(position)) {
                board.setType(position, DIRT);
                gamePresentable.changeTile(position, DOWN, DIRT);
            }
            if (board.isAnyObject(position)) {
                if (board.isObject(position, WORM)) {
                    board.removeObject(position, WORM);
                    gamePresentable.deleteObject(WORM, position);
                }
            }
        }
    }
}
