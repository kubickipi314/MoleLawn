package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import static io.github.mole.CONST.ONE;

public class DashboardPresenter implements PresenterSpeciality {
    Sprite energyFrame;
    Sprite energyStrip;
    Sprite airFrame;
    Sprite airStrip;
    int actualEnergy;
    int actualAir;

    float energyFrameX;
    float energyStripX;
    float airFrameX;
    float airStripX;
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
        actualAir = 3;
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;
        energyFrameX = cameraPosition.x - viewWidth / 2;
        energyStripX = energyFrameX + 12;

        energyFrame.setPosition(energyFrameX, dashboardY);
        energyStrip.setPosition(energyStripX, dashboardY);

        airFrameX = cameraPosition.x + viewWidth / 2 - 165;


        float airStripLength = (float) (132 * actualAir) / 5;
        airStripX = airFrameX + 12 + 132 - airStripLength;
        airStrip.setSize(airStripLength, 40);

        airFrame.setPosition(airFrameX, dashboardY);
        airStrip.setPosition(airStripX, dashboardY);

    }

    public void setEnergyLevel(int energy) {
        energyStrip.setSize((float) (132 * energy) / 20, 40);
    }

    public void setAirLevel(int air) {
        float airStripLength = (float) (132 * air) / 5;
        airStrip.setPosition(airFrameX + 12 + 132 - airStripLength, dashboardY);
        airStrip.setSize(airStripLength, 40);
    }

    public void hideDashboard() {
        movementTime = 0;
        isHiding = true;
    }

    public void update() {
        if (isHiding) {
            movementTime += Gdx.graphics.getDeltaTime();
            float animationDuration = 2.0f;
            float progress = Math.min(1.0f, movementTime / animationDuration);

            currentY = dashboardY - 40 * progress;

            energyFrame.setPosition(energyFrameX, currentY);
            energyStrip.setPosition(energyStripX, currentY);
            airFrame.setPosition(airFrameX, currentY);
            airStrip.setPosition(airStripX, currentY);

        }
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ONE) {
            energyStrip.draw(batch);
            energyFrame.draw(batch);

            airFrame.draw(batch);
            airStrip.draw(batch);
        }
    }
}
