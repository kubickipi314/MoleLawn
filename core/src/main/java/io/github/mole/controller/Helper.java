package io.github.mole.controller;

import io.github.mole.CONST;
import io.github.mole.model.Board;
import io.github.mole.model.Mole;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;

import static io.github.mole.utils.MoveDirection.*;

public class Helper {

    Board board;
    Mole mole;
    int height = CONST.BOARD_HEIGHT;
    int width = CONST.BOARD_WIDTH;

    public Helper(Board board, Mole mole) {
        this.board = board;
        this.mole = mole;
    }

    private BoardPosition getPosition(int[] OffsetsX, int[] OffsetsY, MoveDirection direction) {
        int moleX = mole.getX();
        int moleY = mole.getY();
        return switch (direction) {
            case LEFT -> new BoardPosition(moleX + OffsetsX[0], moleY + OffsetsY[0]);
            case RIGHT -> new BoardPosition(moleX + OffsetsX[1], moleY + OffsetsY[1]);
            case UP -> new BoardPosition(moleX + OffsetsX[2], moleY + OffsetsY[2]);
            case DOWN -> new BoardPosition(moleX + OffsetsX[3], moleY + OffsetsY[3]);
            default -> null;
        };
    }

    public BoardPosition getLeftPosition(MoveDirection direction) {
        int[] OffsetsX = {0, 0, -1, 1};
        int[] OffsetsY = {1, -1, 0, 0};
        return getPosition(OffsetsX, OffsetsY, direction);
    }

    public BoardPosition getRightPosition(MoveDirection direction) {
        int[] OffsetsX = {0, 0, 1, -1};
        int[] OffsetsY = {-1, 1, 0, 0};
        return getPosition(OffsetsX, OffsetsY, direction);
    }

    public BoardPosition getFrontPosition(MoveDirection direction) {
        int[] OffsetsX = {-1, 1, 0, 0};
        int[] OffsetsY = {0, 0, -1, 1};
        return getPosition(OffsetsX, OffsetsY, direction);
    }

    public BoardPosition getBottomPosition() {
        return new BoardPosition(mole.getX(), Math.min((mole.getY() + 1), height - 1));
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

    public boolean isPositionOnBoard(BoardPosition position) {
        if (position.x() < 0 || position.x() >= width) return false;
        if (position.y() < 0 || position.y() >= height) return false;
        return true;
    }
}
