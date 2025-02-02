package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class AirBarView {
    Sprite airFrame;
    Sprite airStrip;
    float airFrameX;
    float airStripX;
    float airStripLength;
    float dashboardY;

    float airLevel;
    float oldAirLevel;

    public AirBarView() {
        airFrame = new Sprite(new Texture("textures/dashboard/energy/frame.png"));
        airFrame.setSize(160, 40);

        airStrip = new Sprite(new Texture("textures/dashboard/air/strip.png"));
        airStrip.setSize(132, 40);
        airStripLength = 132;

        airLevel = 1;
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;

        airFrameX = cameraPosition.x + viewWidth / 2 - 165;

        airStripX = airFrameX + 12 + 132;
        airStrip.setSize(airStripLength, 40);

        airFrame.setPosition(airFrameX, dashboardY);
        airStrip.setPosition(airStripX - airStripLength, dashboardY);
    }

    public void setAirLevel(float air) {
        oldAirLevel = airLevel;
        airLevel = air;
    }

    public void update(float progress){
        float air = (airLevel - oldAirLevel) * progress + oldAirLevel;
        airStripLength = 132 * air;
        airStrip.setPosition(airStripX - airStripLength, dashboardY);
        airStrip.setSize(airStripLength, 40);
    }

    public void setPosition(float currentY){
        airFrame.setPosition(airFrameX, currentY);
        airStrip.setPosition(airStripX - airStripLength, currentY);
    }

    public void draw(SpriteBatch batch){
        airFrame.draw(batch);
        airStrip.draw(batch);
    }
}
