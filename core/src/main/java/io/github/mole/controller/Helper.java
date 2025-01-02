package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.MoveDirection.*;
import static io.github.mole.utils.MoveDirection.NONE;

public class Helper {

    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;
    public BoardPosition getLeftPosition(int moleX, int moleY, MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(moleX, moleY - 1);
            case RIGHT -> new BoardPosition(moleX, moleY + 1);
            case UP -> new BoardPosition(moleX - 1, moleY);
            case DOWN -> new BoardPosition(moleX + 1, moleY);
            default -> null;
        };
    }

    public BoardPosition getRightPosition(int moleX, int moleY, MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(moleX, moleY + 1);
            case RIGHT -> new BoardPosition(moleX, moleY - 1);
            case UP -> new BoardPosition(moleX + 1, moleY);
            case DOWN -> new BoardPosition(moleX - 1, moleY);
            default -> null;
        };
    }

    public BoardPosition getFrontPosition(int moleX, int moleY, MoveDirection direction) {
        return switch (direction) {
            case LEFT -> new BoardPosition(moleX - 1, moleY);
            case RIGHT -> new BoardPosition(moleX + 1, moleY);
            case UP -> new BoardPosition(moleX, moleY + 1);
            case DOWN -> new BoardPosition(moleX, moleY - 1);
            default -> null;
        };
    }

    public BoardPosition getUpperPosition(int moleX, int moleY) {
        return new BoardPosition(moleX, moleY + 1);
    }

    public MoveDirection getLeftDirection(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
            case UP -> LEFT;
            case NONE -> NONE;
        };
    }

    public MoveDirection getRightDirection(MoveDirection direction) {
        return switch (direction) {
            case LEFT -> UP;
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case NONE -> NONE;
        };
    }

    public boolean isPositionOnBoard(BoardPosition position){
        if (position.x() < 0 || position.x() >= width) return false;
        if (position.y() < 0 || position.y() >= height) return false;
        return true;
    }
}
