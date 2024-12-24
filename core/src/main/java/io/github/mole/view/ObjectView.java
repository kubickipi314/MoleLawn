package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.presenter.utils.BoardPosition;

public class ObjectView {
    Sprite tileSprite;
    public ObjectView(Vector2 position){
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

    public BoardPosition getPosition() {
        return null;
    }
}
