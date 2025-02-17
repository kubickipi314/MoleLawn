package io.github.mole.mod.main;

import io.github.mole.utils.ObjectType;

public class Human {
    float angerLevel;
    boolean isAttack;
    ObjectType attackType;

    public Human() {
        angerLevel = 0;
    }

    public void setAngerLevel(float level) {
        angerLevel = level;
    }
    public float getAngerLevel() {
        return angerLevel;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }
    public boolean isAttack() {
        return isAttack;
    }

    public void setAttackType(ObjectType type) {
        attackType = type;
    }

    public ObjectType getAttackType() {
        return attackType;
    }
}
