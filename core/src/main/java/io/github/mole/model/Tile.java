package io.github.mole.model;

import io.github.mole.utils.ObjectType;
import io.github.mole.utils.TileType;

import java.util.EnumMap;

public class Tile {
    TileType type;
    int objectsNumber;
    EnumMap<ObjectType,Boolean> objects;

    public Tile(TileType type) {
        this.type = type;
        objects = new EnumMap<>(ObjectType.class);
        for (var val : ObjectType.values()){
            objects.put(val, false);
        }
        objectsNumber = 0;
    }

    public void addObject(ObjectType object){
        objects.put(object, true);
        objectsNumber++;
    }

    public void removeObject(ObjectType object){
        objects.put(object, false);
        objectsNumber--;
    }

    public boolean isObject(ObjectType object){
        return objects.get(object);
    }
    public boolean isAnyObject(){
        return objectsNumber!=0;
    }

    public void setType(TileType type){
        this.type = type;
    }
    public TileType getType(){
        return type;
    }
}
