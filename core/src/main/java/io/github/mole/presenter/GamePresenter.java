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
import io.github.mole.presenter.specialities.*;
import io.github.mole.utils.*;

import java.util.List;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.TWO;
import static io.github.mole.utils.MoveDirection.*;

public class GamePresenter implements GamePresentable, GameInputable {

    OrthographicCamera camera;

    GameControllable controllable;
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter sightPresenter;
    DashboardPresenter dashboardPresenter;
    FinalPresenter finalPresenter;

    List<PresenterSpeciality> specialities;

    SpriteBatch batch;

    boolean gameFinised;

    public GamePresenter(GameControllable controllable) {
        this.controllable = controllable;

        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
        sightPresenter = new BackgroundPresenter();
        dashboardPresenter = new DashboardPresenter();

        specialities = List.of(boardPresenter, molePresenter, objectsPresenter, sightPresenter, dashboardPresenter);

        batch = new SpriteBatch();
        gameFinised = false;

        camera = new OrthographicCamera();
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        camera.setToOrtho(false, (float) windowWidth / 2, (float) windowHeight / 2);
        camera.position.lerp(new Vector3(200, 0, 0), 0.1f);

        dashboardPresenter.setPosition(camera.position, camera.viewportWidth, camera.viewportHeight);
    }

    public void update() {
        setCamera();
        if (!molePresenter.isActive()) {
            handleInput();
        }

        for (var speciality : specialities) {
            speciality.update();
        }

    }

    private void setCamera() {
        float targetX = molePresenter.getMoleX();
        float targetY = molePresenter.getMoleY();
        float cameraX = MathUtils.clamp(targetX, camera.viewportWidth / 2 - 10, CONST.BOARD_WIDTH * 50 + 10 - camera.viewportWidth / 2);
        float cameraY = MathUtils.clamp(targetY, camera.viewportHeight / 2 - 10, CONST.BOARD_HEIGHT * 50 + 50 - camera.viewportHeight / 2);
        camera.position.lerp(new Vector3(cameraX, cameraY, 0), 0.075f);
        camera.update();

        dashboardPresenter.setPosition(camera.position, camera.viewportWidth, camera.viewportHeight);
    }

    public void render() {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (var speciality : specialities) {
            speciality.render(batch, TWO);
        }
        for (var speciality : specialities) {
            speciality.render(batch, ONE);
        }
        batch.end();
    }

    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        molePresenter.moveMole(destination, direction, style);
        boardPresenter.startAnimation();
    }

    public void moleDie() {
        molePresenter.moleDie();
    }

    public void changeTile(BoardPosition position, MoveDirection direction, TileType type) {
        boardPresenter.changeTile(position, direction, type);
    }

    public void insertObject(ObjectType type, BoardPosition position) {
        objectsPresenter.insertObject(type, position);
    }

    public void deleteObject(ObjectType type, BoardPosition position) {
        objectsPresenter.deleteObject(type, position);
    }

    public void setEnergyLevel(int energyLevel) {
        dashboardPresenter.setEnergyLevel(energyLevel);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            controllable.makeMove(LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            controllable.makeMove(RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            controllable.makeMove(UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            controllable.makeMove(DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            controllable.makeMove(NONE);
        }
    }

    public void setTile(BoardPosition position, TileType type) {
        boardPresenter.setTile(position, type);
    }

    public void setMolePosition(BoardPosition position) {
        molePresenter.setMolePosition(position);
    }

    @Override
    public void retry() {
        controllable.retry();
    }

    @Override
    public void exit() {

    }
}
