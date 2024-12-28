package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.presenter.specialities.BackgroundPresenter;
import io.github.mole.presenter.specialities.BoardPresenter;
import io.github.mole.presenter.specialities.MolePresenter;
import io.github.mole.presenter.specialities.ObjectsPresenter;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.MoveDirection;

import static io.github.mole.presenter.utils.MoveDirection.*;
import static io.github.mole.presenter.utils.MoveStyle.*;
import static io.github.mole.presenter.utils.ObjectType.*;
import static io.github.mole.presenter.utils.TileType.*;

public class GamePresenter {
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter backgroundPresenter;
    SpriteBatch batch;

    boolean hillSwitch;

    public GamePresenter() {
        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
        backgroundPresenter = new BackgroundPresenter();

        batch = new SpriteBatch();

        hillSwitch = true;
    }

    public void update() {
        if (!molePresenter.isActive()) {
            handleInput();
        }
        molePresenter.update();
        boardPresenter.update();
        objectsPresenter.update();
        //foregroundPresenter.update();
    }

    public void render() {
        batch.begin();
        backgroundPresenter.render(batch);
        boardPresenter.render(batch);
        molePresenter.render(batch);
        objectsPresenter.render(batch);
        //foregroundPresenter.render(batch);
        batch.end();
    }

    private void handleInput() {
        BoardPosition molePosition = molePresenter.getMolePosition();
        int xOffset = 0;
        int yOffset = 0;
        MoveDirection direction = NONE;
        boolean action = false;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xOffset--;
            action = true;
            direction = LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xOffset++;
            action = true;
            direction = RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yOffset++;
            action = true;
            direction = UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yOffset--;
            action = true;
            direction = DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            action = true;
        }

        if (action) {
            BoardPosition destination = new BoardPosition(molePosition.x() + xOffset, molePosition.y() + yOffset);

            molePresenter.moveMole(destination, direction, DIGGING);
            if (destination.y() < 5) {
                if (!boardPresenter.isTunnel(destination)) {
                    boardPresenter.changeTile(destination, direction, TUNNEL);

                    if (direction.equals(LEFT)) {
                        if (destination.y() + 1 < 5) {
                            boardPresenter.changeTile(new BoardPosition(destination.x(), destination.y() + 1), UP, DIRT);
                        }
                        if (destination.y() - 1 >= 0) {
                            boardPresenter.changeTile(new BoardPosition(destination.x(), destination.y() - 1), DOWN, DIRT);
                        }
                    } else if (direction.equals(RIGHT)) {
                        if (destination.y() + 1 < 5) {
                            boardPresenter.changeTile(new BoardPosition(destination.x(), destination.y() + 1), UP, DIRT);
                        }
                        if (destination.y() - 1 >= 0) {
                            boardPresenter.changeTile(new BoardPosition(destination.x(), destination.y() - 1), DOWN, DIRT);
                        }
                    } else if (direction.equals(UP)) {
                        if (destination.x() + 1 < 12) {
                            boardPresenter.changeTile(new BoardPosition(destination.x() + 1, destination.y()), RIGHT, DIRT);
                        }
                        if (destination.x() - 1 >= 0) {
                            boardPresenter.changeTile(new BoardPosition(destination.x() - 1, destination.y()), LEFT, DIRT);
                        }
                    } else if (direction.equals(DOWN)) {
                        if (destination.x() + 1 < 12) {
                            boardPresenter.changeTile(new BoardPosition(destination.x() + 1, destination.y()), RIGHT, DIRT);
                        }
                        if (destination.x() - 1 >= 0) {
                            boardPresenter.changeTile(new BoardPosition(destination.x() - 1, destination.y()), LEFT, DIRT);
                        }
                    }
                }

                boardPresenter.startAnimation();
            }

            if (direction.equals(UP)) {
                if (hillSwitch) {
                    objectsPresenter.insertObject(HILL, new BoardPosition(2, 0));
                    hillSwitch = !hillSwitch;
                } else {
                    objectsPresenter.deleteObject(HILL, new BoardPosition(2, 0));
                    hillSwitch = !hillSwitch;
                }
            }
        }
    }
}
