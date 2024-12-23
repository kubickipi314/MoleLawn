package io.github.mole.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.MoleTextureLoader;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.MoveStyle;

import java.util.List;

import static io.github.mole.presenter.utils.MoveDirection.*;

public class MoleView {
    MoleTextureLoader loader;
    Sprite moleSprite;
    List<Texture> motive;

    int frameSwitch;

    public MoleView(Vector2 position) {
        loader = new MoleTextureLoader();
        motive = loader.getNormalMotive(NONE);
        moleSprite = new Sprite(motive.get(0));
        moleSprite.setPosition(position.x, position.y);
        moleSprite.setSize(50, 50);
        frameSwitch = 0;
    }

    public void setPosition(Vector2 position) {
        moleSprite.setPosition(position.x, position.y);
    }

    public void setNormalMotive(MoveDirection direction){
        motive = loader.getNormalMotive(direction);
        moleSprite.setTexture(motive.get(frameSwitch));
    }

    public void setDiggingMotive(MoveDirection direction){
        motive = loader.getDiggingMotive(direction);
        moleSprite.setTexture(motive.get(frameSwitch));
    }

    public void updateMotive(){
        frameSwitch = (frameSwitch+1)%motive.size();
        moleSprite.setTexture(motive.get(frameSwitch));
    }

    public void draw(SpriteBatch batch) {
        moleSprite.draw(batch);
    }
}
