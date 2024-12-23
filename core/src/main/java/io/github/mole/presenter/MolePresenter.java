package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.MoveStyle;
import io.github.mole.view.MoleView;

import static io.github.mole.presenter.utils.MoveDirection.*;

public class MolePresenter implements MolePresenterInterface{
    MoleView moleView;
    int positionX;
    int positionY;
    Vector2 startPosition;
    Vector2 endPosition;

    MoveDirection actualDirection;

    boolean isMoving;
    float movementTime;
    float updatePoint;

    public MolePresenter(){
        positionX = 2;
        positionY = 3;
        moleView = new MoleView(calculatePosition(positionX, positionY));
        moleView.setNormalMotive(NONE);
        isMoving = false;
        actualDirection = NONE;
    }

    public void render(SpriteBatch batch) {
        moleView.draw(batch);
    }

    @Override
    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        actualDirection = direction;
        moleView.setDiggingMotive(actualDirection);
        startMoveAnimation(destination);
    }

    public void startMoveAnimation(BoardPosition destination){
        startPosition = calculatePosition(positionX, positionY);
        endPosition = calculatePosition(destination.x(), destination.y());

        positionX = destination.x();
        positionY = destination.y();
        isMoving = true;
        movementTime = 0;
        updatePoint = 0.2f;
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

        if (movementTime >= updatePoint){
            moleView.updateMotive();
            updatePoint += 0.2f;
        }

        if (progress >= 1.0f) {
            isMoving = false;
            movementTime = 0;
            moleView.setNormalMotive(actualDirection);
        }
    }

    private void updateState(){
        movementTime += Gdx.graphics.getDeltaTime();
        if (movementTime >= 1.0f) {
            moleView.updateMotive();
            movementTime = 0;
        }
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
