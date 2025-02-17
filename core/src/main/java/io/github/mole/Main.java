package io.github.mole;

import com.badlogic.gdx.Game;
import io.github.mole.controllerr.GameController;
import io.github.mole.controllerr.ControllerFactory;
import io.github.mole.mod.GameModel;
import io.github.mole.presenter.GamePresenter;
import io.github.mole.screens.GameScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    GameModel gameModel;
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
        gameModel = new GameModel();
        gameController = ControllerFactory.getController(this, gameModel);
        gamePresenter = new GamePresenter(gameController);

        gameController.setPresentable(gamePresenter);

        setScreen(new GameScreen(gamePresenter));
    }
}
