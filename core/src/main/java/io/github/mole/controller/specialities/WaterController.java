package io.github.mole.controller.specialities;

import io.github.mole.controller.Helper;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;

import static io.github.mole.utils.ObjectType.*;


public class WaterController {
    Board board;
    Mole mole;
    Helper helper;
    GamePresentable gamePresentable;

    int activationCounter;
    boolean isHose;
    boolean endHose;
    int hoseX;
    BoardPosition bootPosition1;
    BoardPosition bootPosition2;

    public WaterController(Board board, Mole mole, Helper helper) {
        this.board = board;
        this.mole = mole;
        this.helper = helper;

        activationCounter = 10;
        isHose = false;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void handleBoot() {
        if (isHose) {

        } else if (activationCounter == 0) {

            activationCounter = 10;
        } else {
            activationCounter--;
        }
    }

    private void takeBoot() {
        gamePresentable.deleteObject(BOOT, bootPosition1);
    }

}
