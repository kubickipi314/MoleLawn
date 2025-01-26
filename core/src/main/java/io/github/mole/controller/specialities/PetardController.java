package io.github.mole.controller.specialities;

import io.github.mole.controller.PositionHelper;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.DeathType;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.MoveDirection.DOWN;
import static io.github.mole.utils.ObjectType.*;
import static io.github.mole.utils.TileType.DIRT;
import static io.github.mole.utils.TileType.TUNNEL;
import static java.lang.Math.abs;

public class PetardController {
    Board board;
    Mole mole;
    PositionHelper positionHelper;
    GamePresentable gamePresentable;

    int activationCounter;
    boolean isPetard;
    boolean isExplosion;
    int holeX;
    BoardPosition petardPosition;

    public PetardController(Board board, Mole mole, PositionHelper positionHelper) {
        this.board = board;
        this.mole = mole;
        this.positionHelper = positionHelper;

        activationCounter = 15;
        isPetard = false;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void postMoveHandle() {
        if (isPetard) {
            explodePetard();
        } else if (isExplosion) {
            endExplostion();
        } else if (activationCounter == 0) {
            tryThrowPetard();
            activationCounter = 25;
        } else {
            activationCounter--;
        }
    }

    private void explodePetard() {
        board.removeObject(petardPosition, PETARD);
        gamePresentable.deleteObject(PETARD, petardPosition);

        int petardX = petardPosition.x();
        int petardY = petardPosition.y();
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                if (petardX + x < 0 || x + petardX >= board.getWidth()) return;
                BoardPosition position = new BoardPosition(petardX + x, petardY + y);
                tryExplode(position);
            }
        }
        isPetard = false;
        isExplosion = true;
    }

    private void endExplostion() {
        int petardX = petardPosition.x();
        int petardY = petardPosition.y();
        for (int x = 1; x >= -1; --x) {
            for (int y = 1; y >= -1; --y) {
                if (petardX + x < 0 || x + petardX >= board.getWidth()) return;
                BoardPosition position = new BoardPosition(petardX + x, petardY + y);
                gamePresentable.deleteObject(EXPLOSION, position);
                tryExplodeBury(position);
            }
        }
        isExplosion = false;
    }

    void tryExplodeBury(BoardPosition position) {
        if (board.getType(position).equals(TUNNEL) && !mole.getPosition().equals(position)) {
            BoardPosition upper = new BoardPosition(position.x(), position.y() - 1);
            if (board.getType(upper).equals(DIRT)) {
                board.setType(position, DIRT);
                gamePresentable.changeTile(position, DOWN, DIRT);

                if (board.isAnyObject(position)) {
                    if (board.isObject(position, WORM)) {
                        board.removeObject(position, WORM);
                        gamePresentable.deleteObject(WORM, position);
                    }
                }
            }
        }
    }

    private void tryExplode(BoardPosition position) {
        if (board.getType(position).equals(TileType.TUNNEL)) {
            if (board.isAnyObject(position)) {
                if (board.isObject(position, WORM)) {
                    board.removeObject(position, WORM);
                    gamePresentable.deleteObject(WORM, position);
                }
            }
            gamePresentable.insertObject(EXPLOSION, position);

            if (mole.getPosition().equals(position)) {
                gamePresentable.moleDie(DeathType.EXPLOSION);
            }
        }
    }

    private void tryThrowPetard() {
        tryGetPosition();
        if (isPetard) {
            throwPetard();
        }
    }

    private void tryGetPosition() {
        if (mole.getY() <= 1) return;
        holeX = getHoleX();
        if (holeX == -1) return;

        if (board.isObject(new BoardPosition(holeX, 1), SPADE)) return;
        if (board.isObject(new BoardPosition(holeX, 0), BOOT)) return;

        int petarY = getPetardY();

        petardPosition = new BoardPosition(holeX, petarY);

        isPetard = true;
    }

    int getHoleX() {
        int holeX = -1;
        int distanceToMole = board.getWidth();

        for (int x = 0; x < board.getWidth(); ++x) {
            if (board.getType(x, 1).equals(TileType.TUNNEL)) {
                if (board.getType(x, 2).equals(TileType.TUNNEL)) {
                    if (abs(mole.getX() - x) < distanceToMole) {
                        holeX = x;
                        distanceToMole = abs(mole.getX() - x);
                    }
                }
            }
        }
        return holeX;
    }

    int getPetardY() {
        int petardY = 1;
        for (int y = 2; y <= 4; ++y) {
            if (!board.getType(holeX, y).equals(TileType.TUNNEL)) break;
            petardY = y;
        }
        return petardY;
    }

    private void throwPetard() {
        gamePresentable.insertObject(PETARD, petardPosition);
        board.addObject(petardPosition, PETARD);
    }
}
