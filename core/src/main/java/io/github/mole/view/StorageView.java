package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.utils.ObjectType;
import io.github.mole.view.helpers.ObjectsTextureLoader;

import static io.github.mole.utils.ObjectType.MOSS;
import static io.github.mole.utils.ObjectType.PETARD;

public class StorageView {
    Sprite storageFrame;
    Sprite storedObject;

    float storageFrameX;
    float dashboardY;

    boolean emptyStorage;
    ObjectType object;

    public StorageView() {
        storageFrame = new Sprite(new Texture("textures/dashboard/storage/frame.png"));
        storageFrame.setSize(90, 90);

        storedObject = new Sprite(new Texture("textures/objects/moss/still_0.png"));
        storedObject.setSize(36, 36);

        emptyStorage = true;
    }
    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        dashboardY = cameraPosition.y - viewHeight / 2;

        storageFrameX = cameraPosition.x - viewWidth / 2;
        storageFrame.setPosition(storageFrameX, dashboardY);
        storedObject.setPosition(storageFrameX + 13, dashboardY + 45);
    }

    public void update(float progress){

    }

    public void setObject(ObjectType object){
        this.object = object;
        if (object.equals(MOSS)) {
            storedObject.setTexture(new Texture("textures/objects/moss/still_0.png"));
        }
        else if (object.equals(PETARD)) {
            storedObject.setTexture(new Texture("textures/objects/petard/still_0.png"));
        }
        emptyStorage = false;
    }

    public void removeObject() {
        emptyStorage = true;
    }

    public void setPosition(float currentY){
        storageFrame.setPosition(storageFrameX, currentY);
    }

    public void draw(SpriteBatch batch){
        storageFrame.draw(batch);
        if (!emptyStorage) storedObject.draw(batch);
    }
}
