package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mole.CONST;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.ObjectType;
import io.github.mole.view.ObjectView;

public class SingleObject {
    ObjectType type;
    BoardPosition position;
    ObjectView objectView;
    boolean isInsertAnimation;
    boolean isDeleteAnimation;
    boolean isDeleted;
    float animationTime;

    public SingleObject(ObjectType type, BoardPosition position, ObjectView objectView) {
        this.type = type;
        this.position = position;
        this.objectView = objectView;
        isInsertAnimation = false;
        isDeleteAnimation = false;
        isDeleted = false;
        animationTime = 0;
        init();
    }

    private void init() {
        objectView.setInsertMotive();
        isInsertAnimation = true;
    }

    public void delete() {
        startDeleteAnimation();
    }

    private void startDeleteAnimation() {
        objectView.setDeleteMotive();
        isDeleteAnimation = true;
    }

    public void update() {
        if (isInsertAnimation || isDeleteAnimation) {
            animationTime += Gdx.graphics.getDeltaTime();
            float animationDuration = CONST.ANIMATION_DURATION;
            float progress = Math.min(1.0f, animationTime / animationDuration);

            if (progress >= 1.0f) {
                endAnimation();
                return;
            }
            objectView.updateAnimationMotive(progress);

        } else {
            animationTime += Gdx.graphics.getDeltaTime();
            if (animationTime >= 0.9f) {
                objectView.updateMotive();
                animationTime = 0;
            }
        }
    }

    private void endAnimation() {
        animationTime = 0;
        if (isInsertAnimation) {
            isInsertAnimation = false;
            objectView.setStillMotive();
        } else {
            isDeleted = true;
        }
    }

    public void render(SpriteBatch batch) {
        objectView.draw(batch);
    }

    public BoardPosition getPosition() {
        return position;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
