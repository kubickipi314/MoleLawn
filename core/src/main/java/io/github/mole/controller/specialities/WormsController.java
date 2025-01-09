package io.github.mole.controller.specialities;

import io.github.mole.CONST;
import io.github.mole.presenter.GamePresentable;
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

    public WormsController(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void postMoveHandle() {
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        tryInsertWorm(new BoardPosition(x,y));
    }

    private void tryInsertWorm(BoardPosition position) {
        if (board.getType(position).equals(TUNNEL)) {
            if (position.equals(mole.getPosition())) return;
            if (board.isAnyObject(position)) return;

            if (!board.isObject(position, WORM)) {
                board.addObject(position, WORM);
                gamePresentable.insertObject(WORM, position);
            }
        }
    }

    public void eatWorm(){
        board.removeObject(mole.getPosition(), WORM);
        gamePresentable.deleteObject(WORM, mole.getPosition());
        int moleEnergy = Math.min(mole.getEnergyLevel() + 4, CONST.ENERGY_LEVEL);
        mole.setEnergyLevel(moleEnergy);
        gamePresentable.setEnergyLevel(moleEnergy);
    }

    public void destroyWorm(BoardPosition position){
        board.removeObject(position, WORM);
        gamePresentable.deleteObject(WORM, position);
    }

}
