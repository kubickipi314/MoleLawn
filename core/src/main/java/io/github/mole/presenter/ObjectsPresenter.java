package io.github.mole.presenter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.objects.HillView;

public class ObjectsPresenter {
    HillView hill;

    public ObjectsPresenter(){
        Texture t = new Texture("textures/objects/hill.png");

        hill = new HillView(new Vector2(100,300));
        hill.setTexture(t);
    }

    public void render(SpriteBatch batch){
        hill.draw(batch);
    }
}
