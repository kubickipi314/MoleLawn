package io.github.mole;

import com.badlogic.gdx.Game;
import io.github.mole.presenter.GamePresenter;
import io.github.mole.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    GamePresenter gamePresenter;
    @Override
    public void create() {
        gamePresenter = new GamePresenter();
        setScreen(new GameScreen(gamePresenter));
    }
}
