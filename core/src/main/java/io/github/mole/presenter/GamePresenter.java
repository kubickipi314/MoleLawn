package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.presenter.specialities.BackgroundPresenter;
import io.github.mole.presenter.specialities.BoardPresenter;
import io.github.mole.presenter.specialities.MolePresenter;
import io.github.mole.presenter.specialities.ObjectsPresenter;
import io.github.mole.utils.*;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.TWO;
import static io.github.mole.utils.MoveDirection.*;

public class GamePresenter implements GamePresentable {

    GameControllable controllerInput;
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter sightPresenter;
    SpriteBatch batch;


    public GamePresenter(GameControllable controllable) {
        this.controllerInput = controllable;

        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
        sightPresenter = new BackgroundPresenter();

        batch = new SpriteBatch();
    }

    public void update() {
        if (!molePresenter.isActive()) {
            handleInput();
        }
        molePresenter.update();
        boardPresenter.update();
        objectsPresenter.update();
    }

    public void render() {
        batch.begin();
        sightPresenter.render(batch, TWO);
        boardPresenter.render(batch, ONE);
        molePresenter.render(batch, ONE);
        objectsPresenter.render(batch, ONE);
        sightPresenter.render(batch, ONE);

        batch.end();
    }

    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        molePresenter.moveMole(destination, direction, style);
        boardPresenter.startAnimation();
    }

    public void changeTile(BoardPosition position, MoveDirection direction, TileType type){
        boardPresenter.changeTile(position, direction, type);
        System.out.println("hey");
    }

    public void insertObject(ObjectType type, BoardPosition position){
        objectsPresenter.insertObject(type, position);
    }

    public void deleteObject(ObjectType type, BoardPosition position){
        objectsPresenter.deleteObject(type, position);
    }

    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
           controllerInput.makeMove(LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            controllerInput.makeMove(RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            controllerInput.makeMove(UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            controllerInput.makeMove(DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            controllerInput.makeMove(NONE);
        }
    }

    public void setTile(BoardPosition position, TileType type){
        boardPresenter.setTile(position, type);
    }

    public void setMolePosition(BoardPosition position){
        molePresenter.setMolePosition(position);
    }
}
