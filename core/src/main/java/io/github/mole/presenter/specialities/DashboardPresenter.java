package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.presenter.helpers.CoordinatesCalculator;

import static io.github.mole.CONST.ONE;

public class DashboardPresenter  implements PresenterSpeciality {
    Sprite backgroudSprite;
    CoordinatesCalculator calculator;
    Sprite[] energySprites;
    int actualEnergy;
    int actualAir;

    Sprite[] bubbleSprites;

    Texture energyTexture;
    Texture bubbleTexture;

    public DashboardPresenter() {
        calculator = new CoordinatesCalculator();
        backgroudSprite = new Sprite(new Texture("textures/dashboard/energy/background.png"));
        backgroudSprite.setSize(calculator.getHealthSize().x, calculator.getHealthSize().y);

        energyTexture = new Texture("textures/dashboard/energy/still_0.png");
        energySprites = new Sprite[20];
        for (int i = 0; i < 20; ++i) {
            energySprites[i] = new Sprite(energyTexture);
            energySprites[i].setSize(50, 50);
        }

        bubbleTexture = new Texture("textures/dashboard/air/still_0.png");
        bubbleSprites = new Sprite[5];
        for (int i = 0; i < 5; ++i) {
            bubbleSprites[i] = new Sprite(bubbleTexture);
            bubbleSprites[i].setSize(40, 40);
        }
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        float healthY = calculator.getEnergyY(cameraPosition, viewHeight);
        float healthX = calculator.getEnergyX(cameraPosition, viewWidth, 0);
        backgroudSprite.setPosition(healthX, healthY);
        for (int i = 0; i < 20; ++i) {
            energySprites[i].setPosition(calculator.getEnergyX(cameraPosition, viewWidth, i), healthY);
        }

        for (int i = 0; i < 5; ++i) {
            bubbleSprites[i].setPosition(calculator.getBubbleX(cameraPosition, viewWidth, i), healthY);
        }
        actualAir = 5;
    }

    public void setEnergyLevel(int energy) {
        actualEnergy = energy;
    }

    public void setAirLevel(int air) {
        actualAir = air;
    }

    public void update() {
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ONE) {
            backgroudSprite.draw(batch);

            for (int i = 0; i < actualEnergy; ++i) {
                energySprites[i].draw(batch);
            }
            for (int i = 0; i < actualAir; ++i) {
                bubbleSprites[i].draw(batch);
            }
        }
    }
}
