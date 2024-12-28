package io.github.mole.controller;


import io.github.mole.presenter.GamePresenter;
import io.github.mole.presenter.utils.MoveDirection;

public class GameController {
    GamePresenter gamePresenter;

    public GameController(){
    }

    public void setGamePresenter(GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
    }

    public void makeMove(MoveDirection direction){

    }
}
