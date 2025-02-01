package io.github.mole.model;

import io.github.mole.CONST;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.ObjectType;

public class Mole {
    int moleX;
    int moleY;
    float energyLevel;
    float airLevel;

    boolean emptyStorage;
    ObjectType storage;

    public Mole() {
        moleX = CONST.MOLE_POSITION_X;
        moleY = CONST.MOLE_POSITION_Y;
        energyLevel = CONST.ENERGY_LEVEL;
        airLevel = 1.0f;
        emptyStorage = true;
    }

    public BoardPosition getPosition() {
        return new BoardPosition(moleX, moleY);
    }

    public int getX() {
        return moleX;
    }

    public int getY() {
        return moleY;
    }

    public float getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(float energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void setAirLevel(float airLevel) {
        this.airLevel = airLevel;
    }

    public float getAirLevel() {
        return airLevel;
    }

    public void changePosition(int newX, int newY) {
        moleX = newX;
        moleY = newY;
    }

    public void changeEnergyLevel(float energyChange) {
        energyLevel += energyChange;
        energyLevel = Math.max(energyLevel, 0);
        energyLevel = Math.min(energyLevel, CONST.ENERGY_LEVEL);
    }

    public boolean isEmptyStorage(){
        return emptyStorage;
    }

    public void putStorage(ObjectType objectType){
        storage = objectType;
        emptyStorage = false;
    }

    public void emptyStorage(){
        emptyStorage = true;
    }

    public ObjectType getStorage(){
        return storage;
    }
}
