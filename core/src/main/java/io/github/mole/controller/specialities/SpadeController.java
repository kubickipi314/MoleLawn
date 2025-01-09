package io.github.mole.controller.specialities;

import io.github.mole.CONST;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;

import java.util.Random;

import static io.github.mole.utils.ObjectType.BOOT;
import static io.github.mole.utils.ObjectType.SPADE;

public class SpadeController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;
    Random random;
    boolean activeByHill;
    boolean activeByCanal;
    boolean spadeIn;
    BoardPosition spadePosition;

    public SpadeController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
        random = new Random();
        activeByHill = false;
        activeByCanal = false;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void preMoveHandle() {
        if (activeByHill) {
            spadePosition = mole.getPosition();
            if (board.isObject(new BoardPosition(mole.getX(), 0), BOOT)) return;
            board.addObject(spadePosition, SPADE);
            gamePresentable.insertObject(SPADE, spadePosition);

            spadeIn = true;
            activeByHill = false;

        } else if (activeByCanal) {
            int moleX = mole.getX();
            moleX += random.nextInt(3) - 1;
            if (moleX == -1) moleX++;
            if (moleX == CONST.BOARD_WIDTH) moleX--;

            if (board.isObject(new BoardPosition(mole.getX(), 0), BOOT)) return;

            spadePosition = new BoardPosition(moleX, 1);
            board.addObject(spadePosition, SPADE);
            gamePresentable.insertObject(SPADE, spadePosition);

            spadeIn = true;
            activeByCanal = false;

        } else if (spadeIn) {
            board.removeObject(spadePosition, SPADE);
            gamePresentable.deleteObject(SPADE, spadePosition);
            spadeIn = false;
        }
    }

    public void activateByHill() {
        activeByHill = true;
    }

    public void activateByCanal() {
        if (!spadeIn)
            activeByCanal = true;
    }

    public void postHhandle() {
    }

    public void postMoveHandle() {
    }
}
