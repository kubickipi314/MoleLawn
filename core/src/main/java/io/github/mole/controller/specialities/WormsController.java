package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;

public class WormsController {
    GamePresentable gamePresentable;
    public WormsController() {
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }
}
