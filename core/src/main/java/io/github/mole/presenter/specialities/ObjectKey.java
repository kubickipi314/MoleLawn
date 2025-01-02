package io.github.mole.presenter.specialities;

import io.github.mole.utils.ObjectType;

import java.util.Objects;

public class ObjectKey {
    int positionX;
    int positionY;
    ObjectType type;
    public ObjectKey(int positionX, int positionY, ObjectType type){
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ObjectKey key = (ObjectKey) object;
        return positionX == key.positionX && positionY == key.positionY && type == key.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY, type);
    }

}
