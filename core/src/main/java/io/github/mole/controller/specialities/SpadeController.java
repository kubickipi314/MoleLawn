package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;

public class SpadeController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;
    public SpadeController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }

    public void handleSpade() {
        if (mole.getY() == 0){

        }
    }
}
