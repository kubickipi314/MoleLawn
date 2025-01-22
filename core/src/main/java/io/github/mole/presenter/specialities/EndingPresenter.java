package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.mole.presenter.GameInputable;
import io.github.mole.presenter.GamePresenter;
import io.github.mole.utils.DeathType;
import io.github.mole.view.helpers.EndingTextureLoader;

import java.util.ArrayList;
import java.util.List;

public class EndingPresenter implements PresenterSpeciality {

    GameInputable gamePresenter;
    EndingTextureLoader loader;
    DeathType deathType;

    Sprite diedFromSprite;
    Sprite deathTypeSprite;
    Sprite retrySprite;

    boolean isEnding;
    int endingPhase;
    float animationTime;

    float deathTypeX;
    float deathTypeY;

    List<Texture> diedFromTextures;
    List<Texture> retryTextures;

    List<Sprite> printedSprites;
    int actualFrame;

    public EndingPresenter(GameInputable gamePresenter, DeathType deathType) {
        this.gamePresenter = gamePresenter;
        this.deathType = deathType;

        loader = new EndingTextureLoader();

        diedFromTextures = List.of(new Texture("textures/ending/died_from_7.png"),
            new Texture("textures/ending/died_from_6.png"),
            new Texture("textures/ending/died_from_5.png"),
            new Texture("textures/ending/died_from_4.png"),
            new Texture("textures/ending/died_from_3.png"),
            new Texture("textures/ending/died_from_2.png"),
            new Texture("textures/ending/died_from_1.png"),
            new Texture("textures/ending/died_from.png"));
        retryTextures = loader.getRetry();

        diedFromSprite = new Sprite(new Texture("textures/ending/died_from_7.png"));
        diedFromSprite.setSize(180, 90);

        retrySprite = new Sprite(new Texture("textures/ending/retry_0.png"));
        retrySprite.setSize(80, 80);

        if (deathType.equals(DeathType.SPADE)) {
            deathTypeSprite = new Sprite(new Texture("textures/ending/spade.png"));
            deathTypeSprite.setSize(180, 90);
        }
        if (deathType.equals(DeathType.BOOT)) {
            deathTypeSprite = new Sprite(new Texture("textures/ending/boot.png"));
            deathTypeSprite.setSize(180, 90);
        }


        printedSprites = new ArrayList<>();

        isEnding = false;
    }

    public void showEnding() {
        isEnding = true;
        endingPhase = 0;
        animationTime = 0;
    }

    public void update() {
        if (isEnding) {
            if (endingPhase == 0) {
                animationTime += Gdx.graphics.getDeltaTime();
                if (animationTime >= 0.5f) {
                    animationTime = 0;
                    endingPhase = 1;
                    actualFrame = 0;
                    printedSprites.add(diedFromSprite);
                }
            } else if (endingPhase == 1) {
                animationTime += Gdx.graphics.getDeltaTime();
                float animationDuration = 1.0f;
                float progress = animationTime / animationDuration;

                if (actualFrame != (int) (diedFromTextures.size() * progress)) {
                    actualFrame++;
                    if (actualFrame == diedFromTextures.size()) {
                        animationTime = 0;
                        endingPhase = 2;
                        printedSprites.add(deathTypeSprite);
                    } else {
                        diedFromSprite.setTexture(diedFromTextures.get((int) (diedFromTextures.size() * progress)));
                    }
                }

                if (progress >= 1) {
                    animationTime = 0;
                    endingPhase = 2;
                    printedSprites.add(deathTypeSprite);
                    deathTypeSprite.setPosition(deathTypeX, deathTypeY - 200);
                }
            } else if (endingPhase == 2) {
                animationTime += Gdx.graphics.getDeltaTime();
                float animationDuration = 1.0f;
                float progress = Math.min(1.0f, animationTime / animationDuration);

                if (progress >= 1) {
                    animationTime = 0;
                    endingPhase = 3;
                    printedSprites.add(retrySprite);
                    actualFrame = -1;
                }

                deathTypeSprite.setPosition(deathTypeX, deathTypeY - 200 + 200 * progress);

            } else if (endingPhase == 3) {
                animationTime += Gdx.graphics.getDeltaTime();
                float animationDuration = 1.0f;
                float progress = animationTime / animationDuration;

                if (actualFrame != (int) (retryTextures.size() * progress)) {
                    actualFrame++;
                    if (actualFrame == retryTextures.size()) {
                        isEnding = false;
                    } else {
                        retrySprite.setTexture(retryTextures.get((int) (retryTextures.size() * progress)));
                    }
                }
            }
        } else {
            handleInput();
        }
    }

    void handleInput(){
        Vector2 mousePosition = getMousePosition();
        if (buttonContains(mousePosition)) {
            retrySprite.setTexture(retryTextures.get(15));
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                gamePresenter.retry();
            }
        }
        else {
            retrySprite.setTexture(retryTextures.get(16));
        }
    }

    public boolean buttonContains(Vector2 mousePosition) {
        float mouseX = mousePosition.x;
        float mouseY = mousePosition.y;
        return retrySprite.getBoundingRectangle().contains(mouseX, mouseY);
    }

    private Vector2 getMousePosition() {
        int windowHeight = Gdx.graphics.getHeight();
        Vector2 mouseWorldCoords = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        float mouseX = mouseWorldCoords.x;
        float mouseY = (windowHeight - mouseWorldCoords.y);
        return new Vector2(mouseX, mouseY);
    }


    public void render(SpriteBatch batch, int one) {
        for (var sprite : printedSprites) {
            sprite.draw(batch);
        }
    }

    public void setPosition(Vector3 cameraPosition, float viewWidth, float viewHeight) {
        float endingY = cameraPosition.y - 100;
        float endingX = cameraPosition.x - 220;
        diedFromSprite.setPosition(endingX, endingY);
        deathTypeSprite.setPosition(endingX + 185, endingY);
        retrySprite.setPosition(endingX + 365, endingY);

        deathTypeX = endingX + 185;
        deathTypeY = endingY;
    }
}
