package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static io.github.mole.presenter.TileType.*;

public class GamePresenter {
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter backgroundPresenter;
    SpriteBatch batch;

    public GamePresenter(){
        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
        backgroundPresenter = new BackgroundPresenter();

        batch = new SpriteBatch();
    }

    public void update() {
        if (!molePresenter.isActive()){
            handleInput();
        }
        molePresenter.update();
    }

    public void render() {
        batch.begin();
        backgroundPresenter.render(batch);
        boardPresenter.render(batch);
        molePresenter.render(batch);
        objectsPresenter.render(batch);
        batch.end();
    }

    private void handleInput(){
        BoardPosition molePosition = molePresenter.getMolePosition();
        int xOffset = 0;
        int yOffset = 0;
        boolean action = false;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {xOffset--; action = true;}
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {xOffset++; action = true;}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {yOffset++; action = true;}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {yOffset--; action = true;}
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {action = true;}

        if (action) {
            BoardPosition destination = new BoardPosition(molePosition.x() + xOffset, molePosition.y() + yOffset);
            molePresenter.moveMole(destination, MoveStyle.NORMAL);
            if (destination.y() < 4)boardPresenter.changeTile(destination, TUNNEL);
        }
    }
}
