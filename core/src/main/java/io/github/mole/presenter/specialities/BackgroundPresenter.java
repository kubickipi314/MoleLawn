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
    SightView[] background;
    SightView[] foreground;
    public BackgroundPresenter() {
        Texture fence = new Texture("textures/sight/fence.png");
        Texture grass = new Texture("textures/sight/grass.png");
        background = new SightView[4];
        foreground = new SightView[4];

        for (int i=0; i<4; ++i){
            background[i] = new SightView(new Vector2(i * 300, positionY), fence);
            foreground[i] = new SightView(new Vector2(i * 300, positionY), grass);
        }
    }

    @Override
    public void update() {

    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == TWO) {
            for (int i=0; i<4; ++i) {
                background[i].draw(batch);
            }
        }
        else if (stageNumber == ONE) {
            for (int i=0; i<4; ++i) {
                foreground[i].draw(batch);
            }
        }
    }
}
