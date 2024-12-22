package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.MoveStyle;

import static io.github.mole.presenter.utils.MoveDirection.*;
import static io.github.mole.presenter.utils.MoveStyle.FREE;
import static io.github.mole.presenter.utils.TileType.*;

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
        boardPresenter.update();
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
        MoveDirection direction = NONE;
        boolean action = false;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {xOffset--; action = true; direction = LEFT;}
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {xOffset++; action = true; direction = RIGHT;}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {yOffset++; action = true; direction = UP;}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {yOffset--; action = true; direction = DOWN;}
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {action = true; direction = NONE;}

        if (action) {
            BoardPosition destination = new BoardPosition(molePosition.x() + xOffset, molePosition.y() + yOffset);

            molePresenter.moveMole(destination, direction, FREE);
            if (destination.y() < 5) {
                boardPresenter.changeTile(destination, direction, TUNNEL);
                boardPresenter.startAnimation();
            }
        }
    }
}
