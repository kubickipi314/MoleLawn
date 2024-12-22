package io.github.mole.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.MoveStyle;

public interface MolePresenterInterface {
    void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style);
    void update();
    void render(SpriteBatch batch);
}
