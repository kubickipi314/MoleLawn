package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.utils.ObjectType;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.mole.utils.ObjectType.*;

public class ObjectsTextureLoader {

    EnumMap<ObjectType, List<Texture>> stillObjects;
    EnumMap<ObjectType, List<Texture>> insertedObjects;
    EnumMap<ObjectType, List<Texture>> deletedObjects;

    public ObjectsTextureLoader() {
        stillObjects = new EnumMap<>(ObjectType.class);
        insertedObjects = new EnumMap<>(ObjectType.class);
        deletedObjects = new EnumMap<>(ObjectType.class);


        stillObjects.put(HILL, getTextureList("textures/objects/hill/still",2));
        insertedObjects.put(HILL, getTextureList("textures/objects/hill/insert",4));
        deletedObjects.put(HILL, getTextureList("textures/objects/hill/delete",3));

        stillObjects.put(CANAL, getTextureList("textures/objects/canal/still",2));
        insertedObjects.put(CANAL, getTextureList("textures/objects/canal/insert",5));
        deletedObjects.put(CANAL, getTextureList("textures/objects/canal/delete",2));

        stillObjects.put(SPADE, getTextureList("textures/objects/spade/still",2));
        insertedObjects.put(SPADE, getTextureList("textures/objects/spade/insert",5));
        deletedObjects.put(SPADE, getTextureList("textures/objects/spade/delete",6));

        stillObjects.put(BOOT, getTextureList("textures/objects/boot/still",2));
        insertedObjects.put(BOOT, getTextureList("textures/objects/boot/insert",6));
        deletedObjects.put(BOOT, getTextureList("textures/objects/boot/delete",11));

        stillObjects.put(PETARD, getTextureList("textures/objects/petard/still",2));
        insertedObjects.put(PETARD, getTextureList("textures/objects/petard/insert",15));
        deletedObjects.put(PETARD, getTextureList("textures/objects/petard/delete",2));

        stillObjects.put(EXPLOSION, getTextureList("textures/objects/explosion/still",2));
        insertedObjects.put(EXPLOSION, getTextureList("textures/objects/explosion/insert",3));
        deletedObjects.put(EXPLOSION, getTextureList("textures/objects/explosion/delete",3));

        stillObjects.put(WORM, getTextureList("textures/objects/worm/still",2));
        insertedObjects.put(WORM, getTextureList("textures/objects/worm/insert",3));
        deletedObjects.put(WORM, getTextureList("textures/objects/worm/delete",3));

        stillObjects.put(WATER, getTextureList("textures/objects/water/still",7));
        insertedObjects.put(WATER, getTextureList("textures/objects/water/still",2));
        deletedObjects.put(WATER, getTextureList("textures/objects/water/still",2));

        stillObjects.put(MOSS, getTextureList("textures/objects/moss/still",2));
        insertedObjects.put(MOSS, getTextureList("textures/objects/moss/insert",3));
        deletedObjects.put(MOSS, getTextureList("textures/objects/moss/delete",3));

        stillObjects.put(NEST, getTextureList("textures/objects/nest/still",2));
        insertedObjects.put(NEST, getTextureList("textures/objects/nest/insert",4));
        deletedObjects.put(NEST, getTextureList("textures/objects/nest/delete",3));
    }

    private List<Texture> getTextureList(String name, int count) {
        return IntStream.range(0, count).mapToObj(idx -> new Texture(name + "_" + idx + ".png")).collect(Collectors.toList());
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
