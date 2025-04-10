package io.github.mole.presenter.specialities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.CONST;
import io.github.mole.presenter.helpers.CoordinatesCalculator;
import io.github.mole.view.ShaderView;
import io.github.mole.view.helpers.TileTextureLoader;
import io.github.mole.utils.BoardPosition;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.TileType;
import io.github.mole.view.TileView;

import java.util.ArrayList;
import java.util.List;

import static io.github.mole.CONST.ONE;
import static io.github.mole.CONST.ZERO;

public class BoardPresenter implements PresenterSpeciality {
    CoordinatesCalculator calculator;
    TileTextureLoader loader;
    TileView[][] board;
    ShaderView[][] shaders;
    private final int height = CONST.BOARD_HEIGHT;
    private final int width = CONST.BOARD_WIDTH;
    boolean isMoving;
    float movementTime;
    float updateTime;
    float progress;
    List<TileView> animatedTiles;

    public BoardPresenter() {
        calculator = new CoordinatesCalculator();
        loader = new TileTextureLoader();
        animatedTiles = new ArrayList<>();

        board = new TileView[height][width];
        shaders = new ShaderView[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Vector2 position = calculator.getCoordinates(x, y);
                board[y][x] = new TileView(loader, position);
                shaders[y][x] = new ShaderView(position);
            }
        }

        isMoving = false;
        movementTime = 0;
        updateTime = 0;
    }

    public void startAnimation(){
        isMoving = true;
        movementTime = 0;
    }

    public void update() {
        if (isMoving) {
            updateAnimatedTiles();
            updateShaders();
        }
        updateRandomTile();
    }

    private void updateRandomTile() {
        updateTime += Gdx.graphics.getDeltaTime();
        if (updateTime >= 0.1f) {
            int i = (int) (Math.random() * CONST.BOARD_HEIGHT);
            int j = (int) (Math.random() * CONST.BOARD_WIDTH);
            board[i][j].updateStillMotive();
            updateTime = 0;
        }
    }

    private void updateAnimatedTiles() {
        movementTime += Gdx.graphics.getDeltaTime();
        float animationDuration = CONST.ANIMATION_DURATION;
        progress = Math.min(1.0f, movementTime / animationDuration);

        if (progress >= 1.0f)
            endAnimation();

        for (var tile : animatedTiles) tile.updateArisingMotive(progress);
    }

    private void updateShaders() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                shaders[y][x].updateShader(progress);
            }
        }
    }

    private void endAnimation() {
        for (var tile : animatedTiles) tile.setStillMotive();
        animatedTiles.clear();
        isMoving = false;
        movementTime = 0;
    }
    public void changeTile(BoardPosition destination, MoveDirection direction, TileType type) {
        TileView tile = board[destination.y()][destination.x()];
        tile.setArisingMotive(direction, type);
        animatedTiles.add(tile);
    }

    public void setTile(BoardPosition boardPosition, TileType type) {
        int x = boardPosition.x();
        int y = boardPosition.y();
        board[y][x].setStillMotive(type);
    }

    public void updateMask(float[][] airMask){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                shaders[y][x].setMask(airMask[y][x]);
            }
        }
    }

    public void render(SpriteBatch batch, int stageNumber) {
        if (stageNumber == ONE) {
            for (int y = 1; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    board[y][x].draw(batch);
                }
            }
        }
        if (stageNumber == ZERO) {
            for (int y = 1; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    shaders[y][x].draw(batch);
                }
            }
        }
    }

}
