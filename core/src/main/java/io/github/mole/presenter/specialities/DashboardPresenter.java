package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import static io.github.mole.CONST.ZERO;

public class DashboardPresenter implements PresenterSpeciality {
    Sprite energyFrame;
    Sprite energyStrip;
    Sprite airFrame;
    Sprite airStrip;
    float energyFrameX;
    float energyStripX;
    float airFrameX;
    float airStripX;

    float airStripLength;
    float dashboardY;

    float currentY;

    float movementTime;

    boolean isHiding;
    public DashboardPresenter() {
        energyFrame = new Sprite(new Texture("textures/dashboard/energy/frame.png"));
        energyFrame.setSize(160, 40);

        energyStrip = new Sprite(new Texture("textures/dashboard/energy/strip.png"));
        energyStrip.setSize(132, 40);

        airFrame = new Sprite(new Texture("textures/dashboard/energy/frame.png"));
        airFrame.setSize(160, 40);

        airStrip = new Sprite(new Texture("textures/dashboard/air/strip.png"));
        airStrip.setSize(40, 40);

        isHiding = false;
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;
        energyFrameX = cameraPosition.x - viewWidth / 2;
        energyStripX = energyFrameX + 12;

        energyFrame.setPosition(energyFrameX, dashboardY);
        energyStrip.setPosition(energyStripX, dashboardY);

        airFrameX = cameraPosition.x + viewWidth / 2 - 165;

        airStripX = airFrameX + 12 + 132;
        airStrip.setSize(airStripLength, 40);

        airFrame.setPosition(airFrameX, dashboardY);
        airStrip.setPosition(airStripX - airStripLength, dashboardY);
    }

    public void setEnergyLevel(int energy) {
        energyStrip.setSize((float) (132 * energy) / 20, 40);
    }

    public void setAirLevel(float air) {
        airStripLength = 132 * air;
        airStrip.setPosition(airStripX - airStripLength, dashboardY);
        airStrip.setSize(airStripLength, 40);
    }

    public void hideDashboard() {
        movementTime = 0;
        isHiding = true;
    }

    public void update() {
        if (isHiding) {
            movementTime += Gdx.graphics.getDeltaTime();
            float animationDuration = 0.5f;
            float progress = Math.min(1.0f, movementTime / animationDuration);

            currentY = dashboardY - 40 * progress;

            energyFrame.setPosition(energyFrameX, currentY);
            energyStrip.setPosition(energyStripX, currentY);
            airFrame.setPosition(airFrameX, currentY);
            airStrip.setPosition(airStripX - airStripLength, currentY);
        }
    }


    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ZERO) {
            energyStrip.draw(batch);
            energyFrame.draw(batch);

            airFrame.draw(batch);
            airStrip.draw(batch);
        }
    }
}
