package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.SightView;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.TWO;

public class BackgroundPresenter {
    SightView background1;
    SightView background2;

    SightView foreground1;
    SightView foreground2;

    public BackgroundPresenter() {
        Texture fence = new Texture("textures/sight/fence.png");
        Texture grass = new Texture("textures/sight/grass.png");
        background1 = new SightView(new Vector2(50, 300), fence);
        background2 = new SightView(new Vector2(350, 300), fence);
        foreground1 = new SightView(new Vector2(50, 300), grass);
        foreground2 = new SightView(new Vector2(350, 300), grass);
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == TWO) {
            background1.draw(batch);
            background2.draw(batch);
        }
        else if (stageNumber == ONE) {
            foreground1.draw(batch);
            foreground2.draw(batch);
        }
    }
}
