package io.github.mole.mod.main;

import io.github.mole.CONST;
import io.github.mole.model.Tile;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.ObjectType;
import io.github.mole.utils.TileType;

public class Board {
    Tile[][] board;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;
    public Board(TileType[][] map) {
        board = new Tile[CONST.BOARD_HEIGHT][CONST.BOARD_WIDTH];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Tile(map[y][x]);
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
