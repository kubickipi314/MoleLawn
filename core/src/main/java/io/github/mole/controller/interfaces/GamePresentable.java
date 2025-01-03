package io.github.mole.controller.interfaces;

import io.github.mole.utils.*;

public interface GamePresentable {
    void setMolePosition(BoardPosition position);

    void setTile(BoardPosition position, TileType type);


    void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style);

    void changeTile(BoardPosition position, MoveDirection direction, TileType type);

    void insertObject(ObjectType type, BoardPosition position);

    void deleteObject(ObjectType type, BoardPosition position);
}
