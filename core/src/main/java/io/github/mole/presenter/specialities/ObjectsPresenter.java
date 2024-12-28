package io.github.mole.presenter.specialities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.presenter.helpers.ObjectsTextureLoader;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.ObjectType;
import io.github.mole.view.ObjectView;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import static io.github.mole.CONST.ONE;

public class ObjectsPresenter {
    ObjectsTextureLoader loader;
    CoordinatesCalculator calculator;
    EnumMap<ObjectType, List<SingleObject>> objects;

    public ObjectsPresenter() {
        objects = new EnumMap<>(ObjectType.class);
        for (var value : ObjectType.values()) {
            objects.put(value, new LinkedList<>());
        }
        calculator = new CoordinatesCalculator();
        loader = new ObjectsTextureLoader();
    }

    public void insertObject(ObjectType type, BoardPosition boardPosition) {
        Vector2 position = calculator.getObjectCoordinates(type, boardPosition);
        Vector2 size = calculator.getObjectSize(type);
        ObjectView newObjectView = new ObjectView(type, position, size, loader);
        SingleObject newObject = new SingleObject(type, boardPosition, newObjectView);
        objects.get(type).add(newObject);
    }

    public void deleteObject(ObjectType type, BoardPosition boardPosition) {
        for (var object : objects.get(type)) {
            if (object.getPosition().equals(boardPosition)) {
                object.delete();
            }
        }
    }

    public void update() {
        for (var list : objects.values()) {
            list.removeIf(SingleObject::isDeleted);
            for (var object : list) {
                object.update();
            }
        }
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ONE){
            for (var list : objects.values()) {
                for (var object : list) {
                    object.render(batch);
                }
            }
        }
    }
}
