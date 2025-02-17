package io.github.mole.controllerr.main;

import io.github.mole.controllerr.environment.AirController;
import io.github.mole.controllerr.environment.Registry;
import io.github.mole.mod.main.Board;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.TileType;

public class EnvironmentController {
    Board board;
    Registry registry;
    GamePresentable gamePresentable;

    public EnvironmentController(Board board, Registry registry) {
        this.board = board;
        this.registry = registry;
    }

    public void update() {
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                TileType type = board.getType(x, y);
                BoardPosition position = new BoardPosition(x, y);
                gamePresentable.setTile(position, type);
            }
        }
        AirController airController = registry.get(AirController.class);
        gamePresentable.setAirMask(airController.getAirMask());
    }
}
