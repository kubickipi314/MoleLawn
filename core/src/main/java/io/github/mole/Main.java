package io.github.mole;

import com.badlogic.gdx.Game;
import io.github.mole.controller.GameController;
import io.github.mole.presenter.GamePresenter;
import io.github.mole.screens.GameScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    GameController gameController;
    GamePresenter gamePresenter;

    @Override
    public void create() {
        initializeGame();
    }

    public void retry(){
        initializeGame();
    }

    private void initializeGame(){
        gameController = new GameController(this);
        gamePresenter = new GamePresenter(gameController);

        gameController.setPresentable(gamePresenter);
        gameController.initializePresentable();

        setScreen(new GameScreen(gamePresenter));
    }
}
