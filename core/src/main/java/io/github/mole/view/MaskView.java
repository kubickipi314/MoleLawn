package io.github.mole.view;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MaskView {
    Sprite maskSprite;

    public MaskView(Vector2 position) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1); // White
        pixmap.fill();
        Texture maskTexture = new Texture(pixmap);
        maskSprite = new Sprite(maskTexture);
        maskSprite.setPosition(position.x, position.y);
        maskSprite.setSize(50, 50);
    }

    public void setMask(float airLevel) {
        maskSprite.setColor(0, 0, 0, (1 - airLevel)*0.5f);
    }

    public void draw(SpriteBatch batch) {
        maskSprite.draw(batch);
    }

}
