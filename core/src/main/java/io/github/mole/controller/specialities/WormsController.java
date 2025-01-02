package io.github.mole.controller.specialities;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;

import java.util.Random;

import static io.github.mole.utils.ObjectType.WORM;
import static io.github.mole.utils.TileType.TUNNEL;

public class WormsController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    public WormsController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void handleWorms() {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        tryInsertWorm(new BoardPosition(x,y));
    }

    private void tryInsertWorm(BoardPosition position) {
        if (board.getType(position).equals(TUNNEL)) {
            if (position.equals(mole.getPosition())) return;

            if (!board.isObject(position, WORM)) {
                board.addObject(position, WORM);
                gamePresentable.insertObject(WORM, position);
            }
        }
    }
}
