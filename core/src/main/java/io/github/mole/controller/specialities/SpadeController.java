package io.github.mole.controller.specialities;

import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;

import static io.github.mole.utils.ObjectType.SPADE;

public class SpadeController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;
    boolean activeByHill;
    boolean spaceIn;
    BoardPosition spadePosition;

    public SpadeController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
        activeByHill = false;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void handleSpade() {
        if (activeByHill) {
            spadePosition = mole.getPosition();
            board.addObject(spadePosition, SPADE);
            gamePresentable.insertObject(SPADE, spadePosition);

            spaceIn = true;
            activeByHill = false;
        }
        else if (spaceIn) {
            board.removeObject(spadePosition, SPADE);
            gamePresentable.deleteObject(SPADE, spadePosition);
        }
    }

    public void activateByHill() {
        activeByHill = true;
    }

    public void activateByCanal() {

    }
}
