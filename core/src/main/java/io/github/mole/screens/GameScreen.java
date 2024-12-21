package io.github.mole.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.mole.presenter.GamePresenter;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {
    private final GamePresenter gamePresenter;
    public GameScreen(GamePresenter gamePresenter){
        this.gamePresenter = gamePresenter;
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0.12f, 0.12f, 0.12f, 1);
    }

    @Override
    public void render(float delta) {
        gamePresenter.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gamePresenter.render();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
