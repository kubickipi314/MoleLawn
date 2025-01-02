package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;

public class TilesController {
    GamePresentable gamePresentable;
    public TilesController() {
    }

    public void setPresentable(GamePresentable gamePresentable){
        this.gamePresentable = gamePresentable;
    }
}
