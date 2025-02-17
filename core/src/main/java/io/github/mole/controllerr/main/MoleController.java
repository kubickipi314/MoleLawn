package io.github.mole.controllerr.main;

import io.github.mole.controller.specialities.DiggingController;
import io.github.mole.controllerr.environment.Registry;
import io.github.mole.mod.main.Board;
import io.github.mole.mod.main.Mole;
import io.github.mole.presenter.GamePresentable;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;

import static io.github.mole.utils.MoveDirection.NONE;
import static io.github.mole.utils.MoveStyle.DIGGING;
import static io.github.mole.utils.MoveStyle.FREE;
import static io.github.mole.utils.ObjectType.BOOT;
import static io.github.mole.utils.ObjectType.MOSS;
import static io.github.mole.utils.TileType.DIRT;
import static io.github.mole.utils.TileType.STONE;

public class MoleController {
    GamePresentable gamePresentable;
    Board board;
    Mole mole;
    Registry registry;

    public MoleController(Board board, Mole mole, Registry registry) {
        this.board = board;
        this.mole = mole;
        this.registry = registry;
    }

    public void setPresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
        gamePresentable.setMolePosition(mole.getPosition());
    }

    public void makeMove(MoveDirection direction) {
        mole.changeEnergyLevel(-0.1f);
        int destinationX = mole.getX();
        int destinationY = mole.getY();

        switch (direction) {
            case LEFT:
                destinationX--;
                break;
            case RIGHT:
                destinationX++;
                break;
            case UP:
                destinationY--;
                break;
            case DOWN:
                destinationY++;
                break;
        }

        if (!direction.equals(NONE)){
            mole.changeEnergyLevel(-0.1f);
        }

        MoveStyle moveStyle;
        if (!direction.equals(NONE) && moveSuccess(destinationX, destinationY)) {
            mole.changePosition(destinationX, destinationY);

            if (board.getType(mole.getPosition()).equals(DIRT)) {
                moveStyle = DIGGING;
                DiggingController diggingController = registry.get(DiggingController.class);
                diggingController.handleDigging(direction);
            } else {
                moveStyle = FREE;
            }
        } else {
            moveStyle = DIGGING;
        }

        if (direction.equals(NONE)){
            handleKeepingStorage();
        }

        gamePresentable.moveMole(mole.getPosition(), direction, moveStyle);
    }

    private boolean moveSuccess(int destinationX, int destinationY) {
        if (destinationX < 0 || destinationX >= board.getWidth()) return false;
        if (destinationY < 0 || destinationY >= board.getHeight()) return false;

        BoardPosition destination = new BoardPosition(destinationX, destinationY);
        if (board.isObject(destination,BOOT)) return false;
        return !board.getType(destination).equals(STONE);
    }

    private void handleKeepingStorage(){
        BoardPosition position = mole.getPosition();
        if (mole.isEmptyStorage()){
            if (board.isObject(position, MOSS)){
                board.removeObject(position, MOSS);
                gamePresentable.deleteObject(MOSS, position);
                if (position.y() == 0) {
                    mole.putStorage(MOSS);
                    gamePresentable.putStorage(MOSS);
                }
            }
        }
        else if (mole.getStorage().equals(MOSS)){
            if (!board.isObject(position, MOSS)){
                board.addObject(position, MOSS);
                gamePresentable.insertObject(MOSS, position);
                mole.emptyStorage();
                gamePresentable.removeStorage();
            }
        }
    }


    public void setDashboard() {
        gamePresentable.setEnergyLevel(mole.getEnergyLevel());
        gamePresentable.setAirLevel(mole.getAirLevel());
    }
}

