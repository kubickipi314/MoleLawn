package io.github.mole.view;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ShaderView {
    Sprite shaderSprite;
    float startingLevel;
    float targetLevel;

    public ShaderView(Vector2 position) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        Texture maskTexture = new Texture(pixmap);
        shaderSprite = new Sprite(maskTexture);
        shaderSprite.setPosition(position.x, position.y);
        shaderSprite.setSize(50, 50);

        startingLevel = 0;
    }

    public void setMask(float airLevel) {
        startingLevel = targetLevel;
        targetLevel = airLevel;
        shaderSprite.setColor(0, 0, 0, (1 - airLevel)*0.5f);
    }

    public void draw(SpriteBatch batch) {
        shaderSprite.draw(batch);
    }

    public void updateShader(float progress) {
        float actualLevel = startingLevel + (targetLevel - startingLevel)*progress;
        shaderSprite.setColor(0, 0, 0, (1 - actualLevel)*0.5f);
    }
}
