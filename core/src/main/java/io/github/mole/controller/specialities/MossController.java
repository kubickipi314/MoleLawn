package io.github.mole.controller.specialities;

import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.utils.BoardPosition;

import java.util.List;
import java.util.Random;

import static io.github.mole.utils.ObjectType.MOSS;
import static io.github.mole.utils.ObjectType.WORM;
import static io.github.mole.utils.TileType.TUNNEL;

public class MossController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;

    List<Integer> mossPositions = List.of(12,13,14);
    int counter;

    public MossController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;

        counter = 0;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void postMoveHandle() {
        counter++;
        if (counter == 10) {
            counter = 0;
            tryInsertMoss();
        }
    }

    private void tryInsertMoss(){
        Random random = new Random();
        int mossX = mossPositions.get(random.nextInt(mossPositions.size()));
        BoardPosition mossPosition = new BoardPosition(mossX, 0);
        if (!board.isAnyObject(mossPosition)){
            board.addObject(mossPosition, MOSS);
            gamePresentable.insertObject(MOSS, mossPosition);
        }
    }

    public void tryDeleteMoss(BoardPosition position){
        if (board.isObject(position, MOSS)){
            board.removeObject(position, MOSS);
            gamePresentable.deleteObject(MOSS, position);
        }
    }

}
