package io.github.mole.controllerr;

import io.github.mole.utils.MoveDirection;

public interface GameControllable {
    void makeMove(MoveDirection direction);

    void retry();
}

