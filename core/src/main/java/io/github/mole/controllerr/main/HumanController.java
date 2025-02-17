package io.github.mole.controllerr.main;

import io.github.mole.controllerr.attacks.BootAttackController;
import io.github.mole.controllerr.attacks.PetardAttackController;
import io.github.mole.controllerr.attacks.SpadeAttackController;
import io.github.mole.controllerr.attacks.WaterAttackController;
import io.github.mole.mod.main.Human;
import io.github.mole.mod.main.Time;
import io.github.mole.presenter.GamePresentable;

public class HumanController {
    Time time;
    Human human;

    SpadeAttackController spadeAttack;
    BootAttackController bootAttack;
    PetardAttackController petardAttack;
    WaterAttackController waterAttack;

    public HumanController(Time time, Human human) {
        this.time = time;
        this.human = human;
    }

    public void setSpadeAttack(SpadeAttackController spadeAttack) {
        this.spadeAttack = spadeAttack;
    }
    public void setBootAttack(BootAttackController bootAttack) {
        this.bootAttack = bootAttack;
    }

    public void setPetardAttack(PetardAttackController petardAttack) {
        this.petardAttack = petardAttack;
    }

    public void setWaterAttack(WaterAttackController waterAttack) {
        this.waterAttack = waterAttack;
    }

    public void makeAction() {

    }

    public void setPresentable(GamePresentable gamePresentable) {
    }
}
