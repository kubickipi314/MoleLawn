package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.CONST;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;
import io.github.mole.view.MoleView;

import static io.github.mole.CONST.ONE;
import static io.github.mole.utils.MoveDirection.NONE;

public class MolePresenter {
    CoordinatesCalculator calculator;
    MoleView moleView;
    int positionX;
    int positionY;

    float currentX;
    float currentY;

    Vector2 startPosition;
    Vector2 endPosition;

    MoveDirection actualDirection;

    boolean isMoving;
    boolean moveEnded;
    float movementTime;
    float updatePoint;

    public MolePresenter() {
        calculator = new CoordinatesCalculator();
        moleView = new MoleView();
        moleView.setNormalMotive(NONE);
        isMoving = false;
        moveEnded = false;
        actualDirection = NONE;
    }

    public void setMolePosition(BoardPosition position) {
        positionX = position.x();
        positionY = position.y();
        Vector2 currentPosition = calculator.getCoordinates(positionX, positionY);
        moleView.setPosition(currentPosition);
        currentX = currentPosition.x;
        currentY = currentPosition.y;
    }

    public void moveMole(BoardPosition destination, MoveDirection direction, MoveStyle style) {
        actualDirection = direction;
        moleView.setDiggingMotive(actualDirection, style);
        startMoveAnimation(destination);
    }

    public void startMoveAnimation(BoardPosition destination) {
        startPosition = calculator.getCoordinates(positionX, positionY);
        endPosition = calculator.getCoordinates(destination.x(), destination.y());

        positionX = destination.x();
        positionY = destination.y();
        isMoving = true;
        movementTime = 0;
        updatePoint = 0.2f;
    }

    public void update() {
        if (isMoving) {
            updateMoveAnimation();
        } else {
            updateState();
        }
    }

    public void updateMoveAnimation() {
        movementTime += Gdx.graphics.getDeltaTime();
        float animationDuration = CONST.ANIMATION_DURATION;
        float progress = Math.min(1.0f, movementTime / animationDuration);

        currentX = startPosition.x + (endPosition.x - startPosition.x) * progress;
        currentY = startPosition.y + (endPosition.y - startPosition.y) * progress;
        Vector2 currentPosition = new Vector2(currentX, currentY);

        moleView.setPosition(currentPosition);

        if (movementTime >= updatePoint) {
            moleView.updateMotive();
            updatePoint += 0.2f;
        }

        if (progress >= 1.0f) {
            isMoving = false;
            movementTime = 0;
            moveEnded = true;
            //moleView.setNormalMotive(actualDirection);
        }
    }

    private void updateState() {
        if (moveEnded){
            moleView.setNormalMotive(actualDirection);
            moveEnded = false;
        }
        movementTime += Gdx.graphics.getDeltaTime();
        if (movementTime >= 0.9f) {
            moleView.updateMotive();
            movementTime = 0;
        }
    }
    public boolean isActive() {
        return isMoving;
    }

    public float getMoleX(){

        return currentX + calculator.getTileSize().x/2;
    }

    public float getMoleY(){
        return currentY + calculator.getTileSize().x/2;
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ONE) moleView.draw(batch);
    }
}
