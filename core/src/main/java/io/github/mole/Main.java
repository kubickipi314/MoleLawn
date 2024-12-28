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
        gameController = new GameController();
        gamePresenter = new GamePresenter(gameController);

        gameController.setGamePresentable(gamePresenter);
        gameController.initializePresentable();

        setScreen(new GameScreen(gamePresenter));
    }
}
