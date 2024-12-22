package io.github.mole.view.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HillView {
    Sprite tileSprite;
    public HillView(Vector2 position){
        tileSprite = new Sprite(new Texture("textures/objects/hill.png"));
        tileSprite.setPosition(position.x, position.y);
        tileSprite.setSize(150,50);
    }

    public void setTexture(Texture texture){
        tileSprite.setTexture(texture);
    }

    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
    }
}
