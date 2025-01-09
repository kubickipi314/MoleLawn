package io.github.mole.controller.interfaces;

import io.github.mole.utils.MoveDirection;

public interface GameControllable {
    void makeMove(MoveDirection direction);

    void retry();
}
