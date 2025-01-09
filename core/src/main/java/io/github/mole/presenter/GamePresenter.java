package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.controller.GameControllable;
import io.github.mole.presenter.helpers.CameraCoordinator;
import io.github.mole.presenter.specialities.*;
import io.github.mole.utils.*;

import java.util.List;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.TWO;
import static io.github.mole.utils.MoveDirection.*;

public class GamePresenter implements GamePresentable, GameInputable {
    GameControllable controllable;
    MolePresenter molePresenter;
    BoardPresenter boardPresenter;
    ObjectsPresenter objectsPresenter;
    BackgroundPresenter sightPresenter;
    DashboardPresenter dashboardPresenter;
    FinalPresenter finalPresenter;
    List<PresenterSpeciality> specialities;

    CameraCoordinator cameraCoordinator;
    SpriteBatch batch;
    boolean gameOn;

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
        if (!gameOn) finalPresenter.update();
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
        if (!gameOn){
            finalPresenter.render(batch, ONE);
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

    public void setMolePosition(BoardPosition position) {
        molePresenter.setMolePosition(position);
    }

    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        molePresenter.moveMole(destination, direction, style);
        boardPresenter.startAnimation();
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

    public void setEnergyLevel(int energyLevel) {
        dashboardPresenter.setEnergyLevel(energyLevel);
    }

    public void moleDie(DeathType deathType) {
        molePresenter.moleDie();
        gameOn = false;
        finalPresenter = new FinalPresenter(this);
        finalPresenter.showEnding(deathType);
    }

    @Override
    public void retry() {
        controllable.retry();
    }

    @Override
    public void exit() {

    }
}
