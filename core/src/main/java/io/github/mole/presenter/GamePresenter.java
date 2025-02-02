package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.controller.GameControllable;
import io.github.mole.presenter.helpers.CameraCoordinator;
import io.github.mole.presenter.specialities.*;
import io.github.mole.utils.*;

import java.util.List;

import static io.github.mole.CONST.*;
import static io.github.mole.utils.MoveDirection.*;

public class GamePresenter implements GamePresentable, GameInputable {
    GameControllable controllable;
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter sightPresenter;
    DashboardPresenter dashboardPresenter;
    EndingPresenter endingPresenter;
    List<PresenterSpeciality> specialities;

    CameraCoordinator cameraCoordinator;
    SpriteBatch batch;
    boolean gameOn;
    boolean endingOn;

    public GamePresenter(GameControllable controllable) {
        this.controllable = controllable;

        molePresenter = new MolePresenter();
        boardPresenter = new BoardPresenter();
        objectsPresenter = new ObjectsPresenter();
        sightPresenter = new BackgroundPresenter();
        dashboardPresenter = new DashboardPresenter();
        specialities = List.of(boardPresenter, molePresenter, objectsPresenter, sightPresenter, dashboardPresenter);

        batch = new SpriteBatch();
        cameraCoordinator = new CameraCoordinator(molePresenter, dashboardPresenter);
        gameOn = true;
    }

    public void update() {
        cameraCoordinator.setCamera();

        if (gameOn && !molePresenter.isActive()) {
            handleInput();
        }
        for (var speciality : specialities) {
            speciality.update();
        }

        if (!gameOn && !molePresenter.isActive()) {
            handleEnding();
        }
    }

    public void render() {
        batch.begin();
        cameraCoordinator.setBatch(batch);

        for (var speciality : specialities) {
            speciality.render(batch, TWO);
        }
        for (var speciality : specialities) {
            speciality.render(batch, ONE);
        }
        boardPresenter.render(batch, ZERO);
        dashboardPresenter.render(batch, ZERO);

        if (!gameOn) {
            endingPresenter.render(batch, ONE);
        }
        batch.end();
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

    private void handleEnding() {
        if (endingOn) {
            endingPresenter.update();
        } else {
            endingOn = true;
            cameraCoordinator.setFinalPresenter(endingPresenter);
            dashboardPresenter.hideDashboard();
            endingPresenter.showEnding();
        }
    }

    public void setMolePosition(BoardPosition position) {
        molePresenter.setMolePosition(position);
    }

    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        molePresenter.moveMole(destination, direction, style);
        boardPresenter.startAnimation();
        dashboardPresenter.startAnimation();
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

    public void setTile(BoardPosition position, TileType type) {
        boardPresenter.setTile(position, type);
    }

    public void setEnergyLevel(float energyLevel) {
        dashboardPresenter.setEnergyLevel(energyLevel);
    }

    public void setAirLevel(float airLevel) {
        dashboardPresenter.setAirLevel(airLevel);
    }

    public void moleDie(DeathType deathType) {
        molePresenter.moleDie();
        endingPresenter = new EndingPresenter(this, deathType);
        gameOn = false;
    }

    public void putStorage(ObjectType type) {
        dashboardPresenter.putStorage(type);
    }

    public void removeStorage() {
        dashboardPresenter.removeStorage();
    }

    public void setAirMask(float[][] airMask) {
        boardPresenter.updateMask(airMask);
    }

    public void retry() {
        controllable.retry();
    }

    public void exit() {

    }
}
