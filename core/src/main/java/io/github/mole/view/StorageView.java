package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class StorageView {
    Sprite storageFrame;
    Sprite storedObject;

    float storageFrameX;
    float dashboardY;

    public StorageView() {
        storageFrame = new Sprite(new Texture("textures/dashboard/storage/frame.png"));
        storageFrame.setSize(100, 100);
    }
    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;

        storageFrameX = cameraPosition.x - viewWidth / 2;
        storageFrame.setPosition(storageFrameX, dashboardY);
    }

    public void update(float progress){

    }

    public void setPosition(float currentY){
        storageFrame.setPosition(storageFrameX, currentY);
    }

    public void draw(SpriteBatch batch){
        storageFrame.draw(batch);
    }
}
