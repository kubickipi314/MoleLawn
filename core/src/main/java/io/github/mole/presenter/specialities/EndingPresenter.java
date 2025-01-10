package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.presenter.GameInputable;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.utils.DeathType;

public class EndingPresenter implements PresenterSpeciality {

    DeathType deathType;

    Sprite diedFromSprite;
    Sprite deathTypeSprite;
    Sprite retrySprite;


    boolean isEnding;


    public EndingPresenter(GameInputable gamePresenter, DeathType deathType){
        this.deathType = deathType;

        diedFromSprite = new Sprite(new Texture("textures/ending/died_from.png"));
        diedFromSprite.setSize(180,90);

        retrySprite = new Sprite(new Texture("textures/ending/retry.png"));
        retrySprite.setSize(80,80);

        if (deathType.equals(DeathType.SPADE)){
            deathTypeSprite = new Sprite(new Texture("textures/ending/spade.png"));
            deathTypeSprite.setSize(180,90);
        }

        isEnding = false;
    }

    public void showEnding() {
        isEnding = true;

    }

    public void update() {
    }

    public void render(SpriteBatch batch, int one) {
        if (isEnding) {
            diedFromSprite.draw(batch);
            deathTypeSprite.draw(batch);
            retrySprite.draw(batch);
        }
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        float endingY = cameraPosition.y - 100;
        float endingX = cameraPosition.x - 220;
        diedFromSprite.setPosition(endingX, endingY);
        deathTypeSprite.setPosition(endingX + 185, endingY);
        retrySprite.setPosition(endingX + 365, endingY);
    }
}
