package io.github.mole.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;

public interface MolePresenterInterface {
    void update();

    void render(SpriteBatch batch);
}
