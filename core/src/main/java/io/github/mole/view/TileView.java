package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.TileTextureLoader;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.TileType;

import java.util.List;

import static io.github.mole.presenter.utils.MoveDirection.*;
import static io.github.mole.presenter.utils.TileType.*;

public class TileView {
    TileTextureLoader loader;
    List<Texture> textures;
    Sprite tileSprite;
    TileType actualMotive;

    public TileView(TileTextureLoader loader, Vector2 position){
        this.loader = loader;
        textures = loader.getStillMotive(DIRT);
        tileSprite = new Sprite(textures.get(0));
        tileSprite.setPosition(position.x, position.y);
        tileSprite.setSize(50,50);
    }

    public void setStillMotive(TileType type){
        actualMotive = type;
        textures = loader.getStillMotive(type);
        tileSprite.setTexture(textures.get(0));
    }

    public void setStillMotive(){
        textures = loader.getStillMotive(actualMotive);
        tileSprite.setTexture(textures.get(0));
    }
    public void setArisingMotive(MoveDirection direction, TileType type){
        actualMotive = type;
        textures = loader.getArisingMotive(type,direction);
    }

    public void updateArisingMotive(float progress){
        int motiveSize = textures.size();
        tileSprite.setTexture(textures.get((int)(motiveSize * progress)));
    }

    public TileType getMotive(){
        return actualMotive;
    }

    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
    }
}
