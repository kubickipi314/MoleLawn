package io.github.mole.controllerr;

import io.github.mole.Main;
import io.github.mole.controllerr.main.EnvironmentController;
import io.github.mole.controllerr.main.HumanController;
import io.github.mole.controllerr.main.MoleController;
import io.github.mole.mod.main.Time;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.utils.MoveDirection;

public class GameController implements GameControllable{
    Time time;
    Main main;
    MoleController moleController;
    HumanController humanController;
    EnvironmentController environmentController;

    public GameController(MoleController mC, HumanController hC, EnvironmentController eC) {
        this.moleController = mC;
        this.humanController = hC;
        this.environmentController = eC;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        moleController.setPresentable(gamePresentable);
        humanController.setPresentable(gamePresentable);
        environmentController.setPresentable(gamePresentable);
    }

    @Override
    public void makeMove(MoveDirection direction) {
        time.incrementTime();

        moleController.makeMove(direction);
        humanController.makeAction();
        environmentController.update();

        moleController.setDashboard();
    }

    @Override
    public void retry() {
        main.retry();
        System.out.println("Retry!");
    }

}
