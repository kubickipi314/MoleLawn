package io.github.mole.model;

import io.github.mole.CONST;
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
                board[y][x] = new Tile(type);
            }
        }
    }
    public float getAirLevel(int x, int y) {
        return board[y][x].getAirLevel();
    }

    public void setAirLevel(int x, int y, float airLevel) {
        board[y][x].setAirLevel(airLevel);
    }

    public TileType getType(int x, int y){
        return board[y][x].getType();
    }

    public TileType getType(BoardPosition position){
        return board[position.y()][position.x()].getType();
    }


    public void setType(BoardPosition position, TileType type){
        board[position.y()][position.x()].setType(type);
    }

    public boolean isObject(BoardPosition position, ObjectType type){
        return board[position.y()][position.x()].isObject(type);
    }

    public boolean isAnyObject(BoardPosition position){
        return board[position.y()][position.x()].isAnyObject();
    }

    public void addObject(BoardPosition position, ObjectType type){
        board[position.y()][position.x()].addObject(type);
    }
    public void removeObject(BoardPosition position, ObjectType type){
        board[position.y()][position.x()].removeObject(type);
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
