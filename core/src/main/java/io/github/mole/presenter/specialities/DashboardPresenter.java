package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.CONST;
import io.github.mole.utils.ObjectType;
import io.github.mole.view.AirBarView;
import io.github.mole.view.EnergyBarView;
import io.github.mole.view.StorageView;

import static io.github.mole.CONST.ZERO;

public class DashboardPresenter implements PresenterSpeciality {
    EnergyBarView energyBarView;
    AirBarView airBarView;
    StorageView storageView;
    float movementTime;
    boolean isAnimation;
    boolean isHiding;
    public DashboardPresenter() {
        energyBarView = new EnergyBarView();
        airBarView = new AirBarView();
        storageView = new StorageView();
        isHiding = false;
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        energyBarView.setPosition(cameraPosition, viewWidth, viewHeight);
        airBarView.setPosition(cameraPosition, viewWidth, viewHeight);
        storageView.setPosition(cameraPosition, viewWidth, viewHeight);
    }

    public void setEnergyLevel(float energy) {
        energyBarView.setEnergyLevel(energy);
    }

    public void setAirLevel(float air) {
        airBarView.setAirLevel(air);
    }

    public void putStorage(ObjectType type) {
        storageView.setObject(type);
    }
    public void removeStorage() {
        storageView.removeObject();
    }
    public void hideDashboard() {
        movementTime = 0;
        isHiding = true;
    }

    public void update() {
        if (isHiding) {
            movementTime += Gdx.graphics.getDeltaTime();
            float animationDuration = 1.2f;
            float progress = Math.min(1.0f, movementTime / animationDuration);

            float offset = 100 * progress;

            energyBarView.setPosition(offset);
            airBarView.setPosition(offset);
            storageView.setPosition(offset);
        }
        if (isAnimation) {
            movementTime += Gdx.graphics.getDeltaTime();
            float animationDuration = 0.5f;
            float progress = Math.min(1.0f, movementTime / animationDuration);

            energyBarView.update(progress);
            airBarView.update(progress);

            if (progress >= 1) {
                movementTime = 0;
                isAnimation = false;
            }
        }
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ZERO) {
            energyBarView.draw(batch);
            airBarView.draw(batch);
            storageView.draw(batch);
        }
    }

    public void startAnimation() {
        isAnimation = true;
    }
}
