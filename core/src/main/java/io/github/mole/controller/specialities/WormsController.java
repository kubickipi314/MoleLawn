package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;

public class WormsController {
    GamePresentable gamePresentable;
    public WormsController(Board board) {
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }
}
