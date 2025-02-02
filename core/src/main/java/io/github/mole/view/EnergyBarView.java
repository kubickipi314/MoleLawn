package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.CONST;

public class EnergyBarView {
    Sprite energyFrame;
    Sprite energyStrip;
    float energyFrameX;
    float energyStripX;
    float dashboardY;

    float energyLevel;
    float oldEnergyLevel;

    public EnergyBarView() {
        energyFrame = new Sprite(new Texture("textures/dashboard/energy/frame.png"));
        energyFrame.setSize(160, 40);

        energyStrip = new Sprite(new Texture("textures/dashboard/energy/strip.png"));
        energyStrip.setSize(132, 40);

        energyLevel = CONST.ENERGY_LEVEL;
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;
        energyFrameX = cameraPosition.x - viewWidth / 2;
        energyStripX = energyFrameX + 12;

        energyFrame.setPosition(energyFrameX, dashboardY);
        energyStrip.setPosition(energyStripX, dashboardY);
    }

    public void setPosition(float currentY) {
        energyFrame.setPosition(energyFrameX, currentY);
        energyStrip.setPosition(energyFrameX, currentY);
    }

    public void setEnergyLevel(float energy) {
        oldEnergyLevel = energyLevel;
        energyLevel = energy;
    }

    public void update(float progress){
        float energy = (energyLevel - oldEnergyLevel) * progress + oldEnergyLevel;
        energyStrip.setSize((132 * energy) / CONST.ENERGY_LEVEL, 40);
    }

    public void draw(SpriteBatch batch) {
        energyStrip.draw(batch);
        energyFrame.draw(batch);
    }

}
