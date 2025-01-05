package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.CONST;
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

    OrthographicCamera camera;

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

        camera = new OrthographicCamera();
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        camera.setToOrtho(false, (float) windowWidth/1.8f, (float) windowHeight/1.8f);
        camera.position.lerp(new Vector3(150, 200, 0), 0.1f);
    }

    public void update() {
        float targetX = molePresenter.getMoleX();
        float targetY = molePresenter.getMoleY();
        float cameraX = MathUtils.clamp(targetX, camera.viewportWidth / 2 - 10,  CONST.BOARD_WIDTH * 50 + 10 - camera.viewportWidth / 2);
        float cameraY = MathUtils.clamp(targetY, camera.viewportHeight / 2 - 10, CONST.BOARD_HEIGHT * 50 + 50 - camera.viewportHeight / 2);
        camera.position.lerp(new Vector3(cameraX, cameraY, 0), 0.075f);
        camera.update();

        if (!molePresenter.isActive()) {
            handleInput();
        }
        molePresenter.update();
        boardPresenter.update();
        objectsPresenter.update();
    }

    public void render() {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
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

    public void moleDie(){
        molePresenter.moleDie();
    }

    public void changeTile(BoardPosition position, MoveDirection direction, TileType type){
        boardPresenter.changeTile(position, direction, type);
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
