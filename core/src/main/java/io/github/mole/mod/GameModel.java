package io.github.mole.mod;

import io.github.mole.controller.MapLoader;
import io.github.mole.mod.attacks.BootAttack;
import io.github.mole.mod.attacks.PetardAttack;
import io.github.mole.mod.attacks.SpadeAttack;
import io.github.mole.mod.attacks.WaterAttack;
import io.github.mole.mod.environment.*;
import io.github.mole.mod.main.Board;
import io.github.mole.mod.main.Human;
import io.github.mole.mod.main.Mole;
import io.github.mole.mod.main.Time;

public class GameModel {
    Time time;
    Board board;
    Mole mole;
    Human human;

    Hills hills;
    Moss moss;
    Petards petards;
    Water water;
    Worms worms;

    BootAttack bootAttack;
    PetardAttack petardAttack;
    SpadeAttack spadeAttack;
    WaterAttack waterAttack;

    public GameModel() {
        MapLoader mapLoader = new MapLoader();
        time = new Time();
        board = new Board(mapLoader.loadMap("maps/map1.png"));
        mole = new Mole();
        human = new Human();

        hills = new Hills();
        moss = new Moss();
        petards = new Petards();
        water = new Water();
        worms = new Worms();
    }

    public Time getTime() {
        return time;
    }

    public Board getBoard() {
        return board;
    }

    public Mole getMole() {
        return mole;
    }

    public Human getHuman() {
        return human;
    }

    public Hills getHills() {
        return hills;
    }

    public Moss getMoss() {
        return moss;
    }

    public Petards getPetards() {
        return petards;
    }

    public Water getWater() {
        return water;
    }

    public Worms getWorms() {
        return worms;
    }

    public BootAttack getBootAttack() {
        return bootAttack;
    }

    public PetardAttack getPetardAttack() {
        return petardAttack;
    }

    public SpadeAttack getSpadeAttack() {
        return spadeAttack;
    }

    public WaterAttack getWaterAttack() {
        return waterAttack;
    }
}
