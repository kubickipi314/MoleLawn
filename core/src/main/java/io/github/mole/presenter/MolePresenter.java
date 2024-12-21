package io.github.mole.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.MoleView;

public class MolePresenter {
    MoleView moleView;
    int posX;
    int posY;

    public MolePresenter(){
        posX = 3;
        posY = 3;
        moleView = new MoleView(calculatePosition());
    }

    public void render(SpriteBatch batch) {
        moleView.draw(batch);
    }

    public void moveMole(MoveDirection moveDirection) {
        switch (moveDirection) {
            case LEFT:
                posX--;
                break;
            case RIGHT:
                posX++;
                break;
            case UP:
                posY--;
                break;
            case DOWN:
                posY++;
                break;
        }
        moleView.setPosition(calculatePosition());
    }

    private Vector2 calculatePosition(){
        return new Vector2(posX*50 + 50 , posY*50 + 50);
    }
}
