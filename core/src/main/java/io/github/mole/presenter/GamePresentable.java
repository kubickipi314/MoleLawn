package io.github.mole.presenter;

import io.github.mole.utils.*;

public interface GamePresentable {
    void setMolePosition(BoardPosition position);

    void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style);

    void setTile(BoardPosition position, TileType type);

    void changeTile(BoardPosition position, MoveDirection direction, TileType type);

    void insertObject(ObjectType type, BoardPosition position);

    void deleteObject(ObjectType type, BoardPosition position);

    void setEnergyLevel(int energyLevel);

    void moleDie(DeathType spade);
}
