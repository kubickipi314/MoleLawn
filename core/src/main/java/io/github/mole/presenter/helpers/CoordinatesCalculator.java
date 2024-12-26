package io.github.mole.presenter.helpers;

import com.badlogic.gdx.math.Vector2;
import io.github.mole.CONST;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.ObjectType;

public class CoordinatesCalculator {
    final static float tileSize = 50;
    public Vector2 getCoordinates(BoardPosition position){
        float x = position.x() * tileSize + tileSize;
        float y = (CONST.BOARD_HEIGHT - position.y()) * tileSize;
        return new Vector2(x,y);
    }

    public Vector2 getCoordinates(int posX, int posY){
        float x = posX * tileSize + tileSize;
        float y = (CONST.BOARD_HEIGHT - posY) * tileSize;
        return new Vector2(x,y);
    }

    public Vector2 getTileSize(){
        return new Vector2(tileSize, tileSize);
    }

    public Vector2 getMoleSize(){
        return getTileSize();
    }

    public Vector2 getObjectSize(ObjectType type){
        return switch (type){
            case HILL, CANAL -> new Vector2(3*tileSize, tileSize);
            case SPADE -> new Vector2(tileSize, 3*tileSize);
            default -> new Vector2(tileSize, tileSize);
        };
    }

    public Vector2 getObjectCoordinates(ObjectType type, BoardPosition boardPosition) {
        return switch (type){
            case HILL, CANAL -> getCoordinates(boardPosition.x() - 1, boardPosition.y());
            default -> getCoordinates(boardPosition);
        };
    }
}
