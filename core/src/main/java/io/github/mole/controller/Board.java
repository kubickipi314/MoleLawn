package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.model.Tile;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.ObjectType;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.TileType.AIR;
import static io.github.mole.utils.TileType.DIRT;

public class Board {
    Tile[][] board;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;
    public Board() {
        board = new Tile[CONST.BOARD_HEIGHT][CONST.BOARD_WIDTH];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileType type = DIRT;
                if (y == 0) type = AIR;
                board[height - 1 - y][x] = new Tile(type);
            }
        }
    }

    public TileType getType(int x, int y){
        return board[height - 1 - y][x].getType();
    }

    public TileType getType(BoardPosition position){
        return board[height - 1 - position.y()][position.x()].getType();
    }

    public void setType(int x, int y, TileType type){
        board[height - 1 - y][x].setType(type);
    }

    public void setType(BoardPosition position, TileType type){
        board[height - 1 - position.y()][position.x()].setType(type);
    }

    public boolean isObject(int x, int y, ObjectType type){
        return board[height - 1 - y][x].isObject(type);
    }

    public boolean isObject(BoardPosition position, ObjectType type){
        return board[height - 1 - position.y()][position.x()].isObject(type);
    }

    public void addObject(int x, int y, ObjectType type){
        board[height - 1 - y][x].addObject(type);
    }

    public void addObject(BoardPosition position, ObjectType type){
        board[height - 1 - position.y()][position.x()].addObject(type);
    }

    public void removeObject(int x, int y, ObjectType type){
        board[height - 1 - y][x].removeObject(type);
    }

    public void removeObject(BoardPosition position, ObjectType type){
        board[height - 1 - position.y()][position.x()].removeObject(type);
    }
}
