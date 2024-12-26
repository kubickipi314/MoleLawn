package io.github.mole.presenter.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.presenter.utils.ObjectType;

import java.util.EnumMap;
import java.util.List;

import static io.github.mole.presenter.utils.ObjectType.*;

public class ObjectsTextureLoader {

    EnumMap<ObjectType, List<Texture>> stillObjects;
    EnumMap<ObjectType, List<Texture>> insertedObjects;
    EnumMap<ObjectType, List<Texture>> deletedObjects;

    public ObjectsTextureLoader() {
        stillObjects = new EnumMap<>(ObjectType.class);
        insertedObjects = new EnumMap<>(ObjectType.class);
        deletedObjects = new EnumMap<>(ObjectType.class);

        stillObjects.put(HILL, List.of(new Texture("textures/objects/hill/still_0.png"),
            new Texture("textures/objects/hill/still_1.png")));
        insertedObjects.put(HILL, List.of(new Texture("textures/objects/hill/insert_0.png"),
            new Texture("textures/objects/hill/insert_1.png"),
            new Texture("textures/objects/hill/insert_2.png"),
            new Texture("textures/objects/hill/insert_3.png")));
        deletedObjects.put(HILL, List.of(new Texture("textures/objects/hill/delete_0.png"),
            new Texture("textures/objects/hill/delete_1.png"),
            new Texture("textures/objects/hill/delete_2.png"),
            new Texture("textures/objects/hill/delete_3.png")));

        stillObjects.put(SPADE, List.of(new Texture("textures/objects/spade/still_0.png"),
            new Texture("textures/objects/spade/still_1.png")));
        insertedObjects.put(SPADE, List.of(new Texture("textures/objects/spade/delete_5.png"),
            new Texture("textures/objects/spade/delete_4.png"),
            new Texture("textures/objects/spade/delete_3.png"),
            new Texture("textures/objects/spade/delete_2.png"),
            new Texture("textures/objects/spade/delete_1.png"),
            new Texture("textures/objects/spade/delete_0.png")));
        deletedObjects.put(SPADE, List.of(new Texture("textures/objects/spade/delete_0.png"),
            new Texture("textures/objects/spade/delete_1.png"),
            new Texture("textures/objects/spade/delete_2.png"),
            new Texture("textures/objects/spade/delete_3.png"),
            new Texture("textures/objects/spade/delete_4.png"),
            new Texture("textures/objects/spade/delete_5.png")));

        stillObjects.put(WORM, List.of(new Texture("textures/objects/worm/still_0.png"),
            new Texture("textures/objects/worm/still_1.png")));
        insertedObjects.put(WORM, List.of(new Texture("textures/objects/worm/insert_0.png"),
            new Texture("textures/objects/worm/insert_1.png"),
            new Texture("textures/objects/worm/insert_2.png")));
        deletedObjects.put(WORM, List.of(new Texture("textures/objects/worm/delete_0.png"),
            new Texture("textures/objects/worm/delete_1.png"),
            new Texture("textures/objects/worm/delete_2.png")));
    }

    public List<Texture> getSillMotive(ObjectType type) {
        return stillObjects.get(type);
    }

    public List<Texture> getInsertMotive(ObjectType type) {
        return insertedObjects.get(type);
    }

    public List<Texture> getDeleteMotive(ObjectType type) {
        return deletedObjects.get(type);
    }
}
