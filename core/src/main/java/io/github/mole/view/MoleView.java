package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MoleView {
    Sprite moleSprite;
    public MoleView(Vector2 position){
        moleSprite = new Sprite(new Texture("textures/mole/normal_left.png"));
        moleSprite.setPosition(position.x, position.y);
        moleSprite.setSize(50,50);
    }

    public void setPosition(Vector2 position){
        moleSprite.setPosition(position.x, position.y);
    }

    public void draw(SpriteBatch batch) {
        moleSprite.draw(batch);
    }
}
