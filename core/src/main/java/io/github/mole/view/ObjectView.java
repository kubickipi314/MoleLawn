package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.presenter.helpers.ObjectsTextureLoader;
import io.github.mole.utils.ObjectType;

import java.util.List;

public class ObjectView {
    Sprite objectSprite;
    List<Texture> textures;
    ObjectsTextureLoader loader;
    ObjectType type;
    int actualFrame;
    int frameSwitch;

    public ObjectView(ObjectType type, Vector2 position, Vector2 size, ObjectsTextureLoader loader) {
        textures = loader.getInsertMotive(type);
        objectSprite = new Sprite(textures.get(0));
        objectSprite.setPosition(position.x, position.y);
        objectSprite.setSize(size.x, size.y);
        this.loader = loader;
        this.type = type;
        frameSwitch = 0;
        actualFrame = 0;
    }

    public void setStillMotive() {
        actualFrame = -1;
        textures = loader.getSillMotive(type);
        objectSprite.setTexture(textures.get(0));
    }

    public void setInsertMotive() {
        actualFrame = 0;
        textures = loader.getInsertMotive(type);
        objectSprite.setTexture(textures.get(0));
    }

    public void setDeleteMotive() {
        actualFrame = 0;
        textures = loader.getDeleteMotive(type);
        objectSprite.setTexture(textures.get(0));
    }

    public void updateAnimationMotive(float progress) {
        int motiveSize = textures.size();
        if (actualFrame != (int) (motiveSize * progress)) {
            actualFrame++;
            objectSprite.setTexture(textures.get((int) (motiveSize * progress)));
        }
    }

    public void updateMotive() {
        frameSwitch = (frameSwitch + 1) % textures.size();
        objectSprite.setTexture(textures.get(frameSwitch));
    }

    public void draw(SpriteBatch batch) {
        objectSprite.draw(batch);
    }
}
