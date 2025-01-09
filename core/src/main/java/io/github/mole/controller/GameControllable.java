package io.github.mole.controller;

import io.github.mole.utils.MoveDirection;

public interface GameControllable {
    void makeMove(MoveDirection direction);

    void retry();
}
