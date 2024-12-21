package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.mole.presenter.MoveDirection;

public class GamePresenter {
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;

    ObjectsPresenter objectsPresenter;
    SpriteBatch batch;

    public GamePresenter(){

        batch = new SpriteBatch();

        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
    }

    public void update() {
        handleInput();
    }

    public void render() {
        batch.begin();
        boardPresenter.render(batch);
        molePresenter.render(batch);
        objectsPresenter.render(batch);
        batch.end();
    }

    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            molePresenter.moveMole(MoveDirection.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) molePresenter.moveMole(MoveDirection.RIGHT);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) molePresenter.moveMole(MoveDirection.UP);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) molePresenter.moveMole(MoveDirection.DOWN);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) molePresenter.moveMole(MoveDirection.NONE);
    }
}
