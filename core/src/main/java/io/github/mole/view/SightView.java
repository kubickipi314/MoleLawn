package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SightView {
    Sprite sprite;

    public SightView(Vector2 position, Texture texture) {
        sprite = new Sprite(texture);
        sprite.setPosition(position.x, position.y);
        sprite.setSize(300, 100);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
