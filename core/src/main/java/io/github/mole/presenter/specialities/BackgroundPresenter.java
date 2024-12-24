package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.BackgroundView;

public class BackgroundPresenter {
    BackgroundView background1;
    BackgroundView background2;

    public BackgroundPresenter(){
        Texture fence = new Texture("textures/background/fence.png");
        background1 = new BackgroundView(new Vector2(50,300), fence);
        background2 = new BackgroundView(new Vector2(350,300), fence);
    }

    public void render(SpriteBatch batch){
        background1.draw(batch);
        background2.draw(batch);
    }
}
