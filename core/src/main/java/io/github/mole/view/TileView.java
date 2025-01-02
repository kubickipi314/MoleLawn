package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.helpers.TileTextureLoader;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.TileType;

import java.util.List;

import static io.github.mole.utils.TileType.DIRT;

public class TileView {
    TileTextureLoader loader;
    List<Texture> textures;
    Sprite tileSprite;
    TileType actualMotive;
    boolean isStill;
    int actualFrame;

    public TileView(TileTextureLoader loader, Vector2 position) {
        this.loader = loader;
        textures = loader.getStillMotive(DIRT);
        int randomIndex = (int) (Math.random() * textures.size());
        tileSprite = new Sprite(textures.get(randomIndex));
        tileSprite.setPosition(position.x, position.y);
        tileSprite.setSize(50, 50);
        isStill = true;
        actualFrame = -1;
    }

    public void setStillMotive(TileType type) {
        isStill = true;
        actualMotive = type;
        textures = loader.getStillMotive(type);
        int randomIndex = (int) (Math.random() * textures.size());
        tileSprite.setTexture(textures.get(randomIndex));
    }

    public void setStillMotive() {
        isStill = true;
        textures = loader.getStillMotive(actualMotive);
        int randomIndex = (int) (Math.random() * textures.size());
        tileSprite.setTexture(textures.get(randomIndex));
    }

    public void updateStillMotive() {
        if (isStill) {
            int randomIndex = (int) (Math.random() * textures.size());
            tileSprite.setTexture(textures.get(randomIndex));
        }
    }

    public void setArisingMotive(MoveDirection direction, TileType type) {
        actualFrame = -1;
        actualMotive = type;
        isStill = false;
        textures = loader.getArisingMotive(type, direction);
    }

    public void updateArisingMotive(float progress) {
        int motiveSize = textures.size();
        if (actualFrame != (int) (motiveSize * progress)) {
            actualFrame++;
            tileSprite.setTexture(textures.get((int) (motiveSize * progress)));
        }
    }

    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
    }
}
