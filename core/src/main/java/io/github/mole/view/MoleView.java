package io.github.mole.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.MoleTextureLoader;
import io.github.mole.presenter.utils.MoveDirection;

import static io.github.mole.presenter.utils.MoveDirection.*;

public class MoleView {
    MoleTextureLoader loader;
    Sprite moleSprite;

    public MoleView(Vector2 position) {
        loader = new MoleTextureLoader();
        moleSprite = new Sprite(loader.getTextures(NONE));
        moleSprite.setPosition(position.x, position.y);
        moleSprite.setSize(50, 50);
    }

    public void setPosition(Vector2 position) {
        moleSprite.setPosition(position.x, position.y);
    }

    public void setTexture(MoveDirection direction) {
        moleSprite.setTexture(loader.getTextures(direction));
    }

    public void draw(SpriteBatch batch) {
        moleSprite.draw(batch);
    }
}
