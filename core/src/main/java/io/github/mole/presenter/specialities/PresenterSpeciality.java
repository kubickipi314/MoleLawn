package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PresenterSpeciality {
    void update();
    void render(SpriteBatch batch, int stageNumber);
}
