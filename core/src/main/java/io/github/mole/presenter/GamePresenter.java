package io.github.mole.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GamePresenter {
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    SpriteBatch batch;

    public GamePresenter(){

        batch = new SpriteBatch();

        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
    }

    public void update() {
    }

    public void render() {
        batch.begin();
        molePresenter.render(batch);
        //boardPresenter.render(batch);
        batch.end();
    }
}
