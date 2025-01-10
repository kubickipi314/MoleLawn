package io.github.mole.presenter.helpers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.CONST;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.ObjectType;

public class CoordinatesCalculator {
    final static float tileSize = 50;

    public Vector2 getCoordinates(BoardPosition position) {
        float x = position.x() * tileSize;
        float y = (CONST.BOARD_HEIGHT - position.y() - 1) * tileSize;
        return new Vector2(x, y);
    }

    public Vector2 getCoordinates(int posX, int posY) {
        float x = posX * tileSize;
        float y = (CONST.BOARD_HEIGHT - posY - 1) * tileSize;
        return new Vector2(x, y);
    }

    public Vector2 getTileSize() {
        return new Vector2(tileSize, tileSize);
    }

    public Vector2 getMoleSize() {
        return getTileSize();
    }

    public Vector2 getObjectSize(ObjectType type) {
        return switch (type) {
            case HILL, CANAL -> new Vector2(3 * tileSize, tileSize);
            case BOOT -> new Vector2(4 * tileSize, 2 * tileSize);
            case SPADE -> new Vector2(tileSize, 3 * tileSize);
            default -> new Vector2(tileSize, tileSize);
        };
    }

    public Vector2 getObjectCoordinates(ObjectType type, BoardPosition boardPosition) {
        return switch (type) {
            case HILL, CANAL -> getCoordinates(boardPosition.x() - 1, 0);
            case BOOT -> getCoordinates(boardPosition.x() - 2, 0);
            default -> getCoordinates(boardPosition);
        };
    }

    public Vector2 getHealthSize() {
        return new Vector2(tileSize * 3, tileSize);
    }

    public float getEnergyX(Vector3 cameraPosition, float viewWidth, int offset){
        return cameraPosition.x - viewWidth/2 + offset*tileSize/8; }

    public float getEnergyY(Vector3 cameraPosition, float viewHeight){
        return cameraPosition.y - viewHeight/2;
    }

    public float getBubbleX(Vector3 cameraPosition, float viewWidth, int offset) {
        return cameraPosition.x + viewWidth/2 - tileSize*3/4 - offset*tileSize/2;
    }

}
