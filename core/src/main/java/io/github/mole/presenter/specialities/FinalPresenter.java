package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.presenter.GameInputable;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.utils.DeathType;

public class FinalPresenter  implements PresenterSpeciality {

    Sprite diedFromSprite;
    Sprite deathTypeSprite;
    Sprite retrySprite;

    CoordinatesCalculator calculator;

    public FinalPresenter(GameInputable gamePresenter){
        calculator = new CoordinatesCalculator();

        diedFromSprite = new Sprite(new Texture("textures/ending/died_from.png"));
        diedFromSprite.setSize(180,90);

        retrySprite = new Sprite(new Texture("textures/ending/retry.png"));
        retrySprite.setSize(80,80);
    }

    public void showEnding(DeathType deathType) {
        if (deathType.equals(DeathType.SPADE)){
            deathTypeSprite = new Sprite(new Texture("textures/ending/spade.png"));
            deathTypeSprite.setSize(180,90);
        }
    }

    public void update() {
    }

    public void render(SpriteBatch batch, int one) {
        diedFromSprite.draw(batch);
        deathTypeSprite.draw(batch);
        retrySprite.draw(batch);
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        float endingY = cameraPosition.y - 100;
        float endingX = cameraPosition.x - 220;
        diedFromSprite.setPosition(endingX, endingY);
        deathTypeSprite.setPosition(endingX + 185, endingY);
        retrySprite.setPosition(endingX + 365, endingY);
    }
}
