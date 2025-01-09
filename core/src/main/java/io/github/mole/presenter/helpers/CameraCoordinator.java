package io.github.mole.presenter.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.CONST;
import io.github.mole.presenter.specialities.DashboardPresenter;
import io.github.mole.presenter.specialities.MolePresenter;

public class CameraCoordinator {
    OrthographicCamera camera;
    MolePresenter molePresenter;
    DashboardPresenter dashboardPresenter;


    public CameraCoordinator(MolePresenter molePresenter, DashboardPresenter dashboardPresenter) {
        this.molePresenter = molePresenter;
        this.dashboardPresenter = dashboardPresenter;

        camera = new OrthographicCamera();
        initialize();
    }

    private void initialize() {
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        camera.setToOrtho(false, (float) windowWidth / 2, (float) windowHeight / 2);
        camera.position.lerp(new Vector3(200, 0, 0), 0.1f);

        dashboardPresenter.setPosition(camera.position, camera.viewportWidth, camera.viewportHeight);
    }

    public void setCamera() {
        float targetX = molePresenter.getMoleX();
        float targetY = molePresenter.getMoleY();
        float cameraX = MathUtils.clamp(targetX, camera.viewportWidth / 2 - 10, CONST.BOARD_WIDTH * 50 + 10 - camera.viewportWidth / 2);
        float cameraY = MathUtils.clamp(targetY, camera.viewportHeight / 2 - 10, CONST.BOARD_HEIGHT * 50 + 50 - camera.viewportHeight / 2);
        camera.position.lerp(new Vector3(cameraX, cameraY, 0), 0.075f);
        camera.update();

        dashboardPresenter.setPosition(camera.position, camera.viewportWidth, camera.viewportHeight);
    }

    public void setBatch(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
    }
}
