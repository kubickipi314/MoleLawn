package io.github.mole.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.github.mole.presenter.utils.MoveDirection;

public class GameInputProcessor implements InputProcessor {

    private final GameController gameController;

    public GameInputProcessor(GameController controller) {
        this.gameController = controller;
    }

    @Override
    public boolean keyDown(int keycode) {
        MoveDirection direction = getMoveDirection(keycode);
        gameController.makeMove(direction);
        return true;
    }

    private MoveDirection getMoveDirection(int keycode){
        return switch(keycode){
            case Input.Keys.RIGHT -> MoveDirection.RIGHT;
            case Input.Keys.LEFT -> MoveDirection.LEFT;
            case Input.Keys.UP -> MoveDirection.UP;
            case Input.Keys.DOWN -> MoveDirection.DOWN;
            case Input.Keys.SPACE -> MoveDirection.NONE;
            default -> MoveDirection.NONE;
        };
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
