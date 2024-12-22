package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.TileTextureLoader;
import io.github.mole.presenter.TileType;

import static io.github.mole.presenter.TileType.DIRT;

public class TileView {
    TileTextureLoader loader;
    Sprite tileSprite;
    public TileView(TileTextureLoader loader, Vector2 position){
        this.loader = loader;
        tileSprite = new Sprite(loader.getTileTexture(DIRT));
        tileSprite.setPosition(position.x, position.y);
        tileSprite.setSize(50,50);
    }

    public void setTexture(TileType type){
        tileSprite.setTexture(loader.getTileTexture(type));
    }

    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
    }
}
