package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.presenter.helpers.MoleTextureLoader;
import io.github.mole.presenter.utils.MoveDirection;

import java.util.List;

import static io.github.mole.presenter.utils.MoveDirection.*;

public class MoleView {
    MoleTextureLoader loader;
    Sprite moleSprite;
    List<Texture> textures;
    int frameSwitch;

    public MoleView(Vector2 position) {
        loader = new MoleTextureLoader();
        textures = loader.getNormalMotive(NONE);
        moleSprite = new Sprite(textures.get(0));
        moleSprite.setPosition(position.x, position.y);
        CoordinatesCalculator calculator = new CoordinatesCalculator();
        Vector2 size = calculator.getMoleSize();
        moleSprite.setSize(size.x, size.y);
        frameSwitch = 0;
    }

    public void setPosition(Vector2 position) {
        moleSprite.setPosition(position.x, position.y);
    }

    public void setNormalMotive(MoveDirection direction) {
        textures = loader.getNormalMotive(direction);
        moleSprite.setTexture(textures.get(frameSwitch));
    }

    public void setDiggingMotive(MoveDirection direction) {
        textures = loader.getDiggingMotive(direction);
        moleSprite.setTexture(textures.get(frameSwitch));
    }

    public void updateMotive() {
        frameSwitch = (frameSwitch + 1) % textures.size();
        moleSprite.setTexture(textures.get(frameSwitch));
    }

    public void draw(SpriteBatch batch) {
        moleSprite.draw(batch);
    }
}
