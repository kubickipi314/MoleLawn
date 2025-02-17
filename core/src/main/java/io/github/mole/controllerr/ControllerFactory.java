package io.github.mole.controllerr;

import io.github.mole.Main;
import io.github.mole.controllerr.attacks.BootAttackController;
import io.github.mole.controllerr.attacks.PetardAttackController;
import io.github.mole.controllerr.attacks.SpadeAttackController;
import io.github.mole.controllerr.attacks.WaterAttackController;
import io.github.mole.controllerr.environment.*;
import io.github.mole.controllerr.main.EnvironmentController;
import io.github.mole.controllerr.main.HumanController;
import io.github.mole.controllerr.main.MoleController;
import io.github.mole.mod.GameModel;
import io.github.mole.mod.main.Board;
import io.github.mole.mod.main.Human;
import io.github.mole.mod.main.Mole;
import io.github.mole.mod.main.Time;

public class ControllerFactory {
    private ControllerFactory() {}
    public static GameController getController(Main main, GameModel model) {

        Board board = model.getBoard();
        Mole mole = model.getMole();
        Human human = model.getHuman();
        Time time = model.getTime();


        Registry registry= new Registry();
        registry.register(DirtController.class, new DirtController(board, registry));
        registry.register(HillsController.class, new HillsController(board, model.getHills(), registry));
        registry.register(MossController.class, new MossController(board, model.getMoss()));
        registry.register(PetardsController.class, new PetardsController(board, model.getPetards(), registry));
        registry.register(WaterController.class, new WaterController(board, model.getWater()));
        registry.register(WormsController.class, new WormsController(board, model.getWorms()));

        EnvironmentController envController = new EnvironmentController(registry);


        HumanController humanController = new HumanController(time, human);

        humanController.setSpadeAttack(new SpadeAttackController(board, model.getSpadeAttack(), registry));
        humanController.setBootAttack(new BootAttackController(board, model.getBootAttack(), registry));
        humanController.setPetardAttack(new PetardAttackController(board, model.getPetardAttack(), registry));
        humanController.setWaterAttack(new WaterAttackController(board, model.getWaterAttack(), registry));


        MoleController moleController = new MoleController(mole);

        GameController gameController = new GameController(moleController, humanController, envController);
        gameController.setTime(time);



        return gameController;
    }
}
