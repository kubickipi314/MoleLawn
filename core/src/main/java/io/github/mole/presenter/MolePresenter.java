package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.MoleView;

import static io.github.mole.presenter.MoveDirection.*;

public class MolePresenter implements MolePresenterInterface{
    MoleView moleView;
    int positionX;
    int positionY;
    Vector2 startPosition;
    Vector2 endPosition;

    boolean isMoving;
    float movementTime;

    public MolePresenter(){
        positionX = 2;
        positionY = 3;
        moleView = new MoleView(calculatePosition(positionX, positionY));
        moleView.setTexture(NONE);
        isMoving = false;
    }

    public void render(SpriteBatch batch) {
        moleView.draw(batch);
    }

    @Override
    public void moveMole(BoardPosition destination, MoveStyle style) {
        MoveDirection direction = getDirection(destination);
        moleView.setTexture(direction);

        startMoveAnimation(destination);
    }

    private MoveDirection getDirection(BoardPosition destination){
        if (destination.x() == positionX - 1) return LEFT;
        if (destination.x() == positionX + 1) return RIGHT;
        if (destination.y() == positionY - 1) return DOWN;
        if (destination.y() == positionY + 1) return UP;
        return NONE;
    }

    public void startMoveAnimation(BoardPosition destination){
        startPosition = calculatePosition(positionX, positionY);
        endPosition = calculatePosition(destination.x(), destination.y());

        positionX = destination.x();
        positionY = destination.y();
        isMoving = true;
        movementTime = 0;
    }

    public void update(){
        if (isMoving){
            updateMoveAnimation();
        }
        else {
            updateState();
        }
    }

    public void updateMoveAnimation(){
        movementTime += Gdx.graphics.getDeltaTime();
        float animationDuration = 0.5f;
        float progress = Math.min(1.0f, movementTime / animationDuration);

        float currentX = startPosition.x + (endPosition.x - startPosition.x) * progress;
        float currentY = startPosition.y + (endPosition.y - startPosition.y) * progress;
        Vector2 currentPosition = new Vector2(currentX, currentY);
        moleView.setPosition(currentPosition);

        if (progress >= 1.0f) {
            isMoving = false;

        }
    }

    private void updateState(){
    }

    private Vector2 calculatePosition(int posX, int posY){
        return new Vector2(posX*50 + 50 , posY*50 + 50);
    }

    public BoardPosition getMolePosition(){
        return new BoardPosition(positionX,positionY);
    }

    public boolean isActive(){
        return isMoving;
    }

}
