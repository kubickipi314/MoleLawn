package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.presenter.GameInputable;
import io.github.mole.presenter.GamePresenter;
import io.github.mole.utils.DeathType;

public class FinalPresenter  implements PresenterSpeciality {

    Sprite diedFromSprite;
    Sprite deathTypeSprite;
    public FinalPresenter(GameInputable gamePresenter){
        diedFromSprite = new Sprite(new Texture("textures/ending/died_from.png"));
        diedFromSprite.setPosition(100, 170);
        diedFromSprite.setSize(200,100);
    }

    public void showEnding(DeathType deathType) {

    }

    public void update() {
    }

    public void render(SpriteBatch batch, int one) {
        diedFromSprite.draw(batch);
    }
}
