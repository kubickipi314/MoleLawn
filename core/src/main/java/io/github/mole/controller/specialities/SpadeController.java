package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;

public class SpadeController {
    GamePresentable gamePresentable;
    public SpadeController() {
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }
}
