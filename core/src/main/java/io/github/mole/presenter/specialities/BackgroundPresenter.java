package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.CONST;
import io.github.mole.view.SightView;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.TWO;

public class BackgroundPresenter  implements PresenterSpeciality {
    int positionY = (CONST.BOARD_HEIGHT - 1) * 50;
    SightView background1;
    SightView background2;
    SightView background3;
    SightView background4;
    SightView foreground1;
    SightView foreground2;
    SightView foreground3;
    SightView foreground4;
    public BackgroundPresenter() {
        Texture fence = new Texture("textures/sight/fence.png");
        Texture grass = new Texture("textures/sight/grass.png");
        background1 = new SightView(new Vector2(0, positionY), fence);
        background2 = new SightView(new Vector2(300, positionY), fence);
        background3 = new SightView(new Vector2(600, positionY), fence);
        background4 = new SightView(new Vector2(900, positionY), fence);
        foreground1 = new SightView(new Vector2(0, positionY), grass);
        foreground2 = new SightView(new Vector2(300, positionY), grass);
        foreground3 = new SightView(new Vector2(600, positionY), grass);
        foreground4 = new SightView(new Vector2(900, positionY), grass);
    }

    @Override
    public void update() {

    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == TWO) {
            background1.draw(batch);
            background2.draw(batch);
            background3.draw(batch);
            background4.draw(batch);
        }
        else if (stageNumber == ONE) {
            foreground1.draw(batch);
            foreground2.draw(batch);
            foreground3.draw(batch);
            foreground4.draw(batch);
        }
    }
}
