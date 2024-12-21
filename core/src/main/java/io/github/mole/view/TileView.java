package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TileView {
    Sprite tileSprite;
    public TileView(Vector2 position){
        tileSprite = new Sprite(new Texture("textures/tiles/dirt.png"));
        tileSprite.setPosition(position.x, position.y);
        tileSprite.setSize(50,50);
    }

    public void setTexture(Texture texture){
        tileSprite.setTexture(texture);
    }

    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
    }
}
