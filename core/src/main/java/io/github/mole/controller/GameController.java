package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.controller.interfaces.GameControllable;
import io.github.mole.controller.interfaces.GamePresentable;
import io.github.mole.model.Tile;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.TileType.AIR;
import static io.github.mole.utils.TileType.DIRT;

public class GameController implements GameControllable {
    GamePresentable gamePresentable;
    Tile[][] board;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    int positionX;
    int positionY;


    public GameController() {
        board = new Tile[CONST.BOARD_HEIGHT][CONST.BOARD_WIDTH];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileType type = DIRT;
                if (i == 0) type = AIR;
                board[i][j] = new Tile(type);
            }
        }

        positionX = 2;
        positionY = height - 1;
    }

    public void setGamePresentable(GamePresentable gamePresentable) {
        this.gamePresentable = gamePresentable;
    }

    public void initializePresentable() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileType type = board[i][j].getType();
                BoardPosition position = new BoardPosition(j, i);
                gamePresentable.setTile(position, type);
            }
        }
        BoardPosition molePosition = new BoardPosition(positionX, positionY);
        gamePresentable.setMolePosition(molePosition);
    }

    public void makeMove(MoveDirection direction) {

        int destinationX = positionX;
        int destinationY = positionY;

        switch(direction){
            case LEFT:
                destinationX--;
            case RIGHT:
                destinationX++;
            case UP:
                destinationY++;
            case DOWN:
                destinationY--;
        }
    }

}
