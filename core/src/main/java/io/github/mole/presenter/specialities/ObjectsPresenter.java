package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.ObjectType;
import io.github.mole.view.ObjectView;

import java.util.EnumMap;
import java.util.List;

public class ObjectsPresenter {
    EnumMap<ObjectType, List<ObjectView>> objects;

    public ObjectsPresenter(){
        objects = new EnumMap<>(ObjectType.class);
    }

    public void insertObject(ObjectType type, BoardPosition position){

    }

    public void deleteObject(ObjectType type, BoardPosition position){
        for (var object : objects.get(type)){
            if (object.getPosition().equals(position)){
                //animate deletion
            }
        }
    }

    public void update() {
    }

    public void render(SpriteBatch batch){
        for (var list : objects.values()){
            for (var object : list){
                object.draw(batch);
            }
        }
    }
}
