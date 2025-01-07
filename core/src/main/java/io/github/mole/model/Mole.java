package io.github.mole.model;

import io.github.mole.CONST;
import io.github.mole.utils.BoardPosition;

public class Mole {
    int moleX;
    int moleY;
    int energyLevel;

    public Mole(){
        moleX = CONST.MOLE_POSITION_X;
        moleY = CONST.MOLE_POSITION_Y;
        energyLevel = CONST.ENERGY_LEVEL;
    }

    public BoardPosition getPosition(){
        return new BoardPosition(moleX, moleY);
    }
    public int getX(){
        return moleX;
    }

    public int getY(){
        return moleY;
    }

    public int getEnergyLevel(){
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void changePosition(int newX, int newY){
        moleX = newX;
        moleY = newY;
    }
}
