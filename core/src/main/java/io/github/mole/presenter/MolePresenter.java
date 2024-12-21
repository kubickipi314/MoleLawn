package io.github.mole.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.MoleView;

public class MolePresenter {
    MoleView moleView;

    public MolePresenter(){
        moleView = new MoleView(new Vector2(30,30));
    }

    public void render(SpriteBatch batch) {
        moleView.draw(batch);
    }
}
