package io.github.mole.model;

import io.github.mole.utils.ObjectType;
import io.github.mole.utils.TileType;

import java.util.EnumMap;

public class Tile {
    TileType type;
    EnumMap<ObjectType,Boolean> objects;

    public Tile(TileType type) {
        this.type = type;
        objects = new EnumMap<>(ObjectType.class);
        for (var val : ObjectType.values()){
            objects.put(val, false);
        }
    }

    public void addObject(ObjectType object){
        objects.put(object, true);
    }

    public void removeObject(ObjectType object){
        objects.put(object, false);
    }

    public boolean isObject(ObjectType object){
        return objects.get(object);
    }

    public void setType(TileType type){
        this.type = type;
    }
    public TileType getType(){
        return type;
    }
}
