package io.github.mole.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.TileTextureLoader;
import io.github.mole.presenter.utils.BoardPosition;
import io.github.mole.presenter.utils.MoveDirection;
import io.github.mole.presenter.utils.TileType;
import io.github.mole.view.TileView;
import io.github.mole.CONST;

import java.util.ArrayList;
import java.util.List;

import static io.github.mole.presenter.utils.TileType.*;

public class BoardPresenter {
    TileTextureLoader loader;
    TileView[][] board;
    private final int height = CONST.BOARD_HEIGHT;
    private final int width = CONST.BOARD_WIDTH;
    boolean isMoving;
    float movementTime;
    List<TileView> animatedTiles;
    public BoardPresenter(){
        loader = new TileTextureLoader();
        animatedTiles = new ArrayList<>();

        board = new TileView[height][width];
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j] = new TileView(loader, new Vector2(j*50 + 50, (height-i)*50));
                if (i == 0) board[i][j].setStillMotive(GRASS);
                else board[i][j].setStillMotive(DIRT);
            }
        }
        board[1][2].setStillMotive(TUNNEL);
        board[2][2].setStillMotive(TUNNEL);
        board[3][2].setStillMotive(TUNNEL);
        board[3][1].setStillMotive(TUNNEL);

        isMoving = false;
        movementTime = 0;
    }

    public void startAnimation(){
        isMoving = true;
        movementTime = 0;
    }

    public void update(){
        if (isMoving){
            updateAnimatedTiles();
        }
        //updateRandomTile();
    }

    private void updateAnimatedTiles(){
        movementTime+= Gdx.graphics.getDeltaTime();
        float animationDuration = 0.5f;
        float progress = Math.min(1.0f, movementTime / animationDuration);

        if (progress >= 1.0f)
            endAnimation();

        for (var tile : animatedTiles) tile.updateArisingMotive(progress);
    }

    private void endAnimation(){
        for (var tile : animatedTiles) tile.setStillMotive();
        animatedTiles.clear();
        isMoving = false;
        movementTime = 0;
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j].draw(batch);
            }
        }
    }

    public void changeTile(BoardPosition destination, MoveDirection direction, TileType type) {
        TileView tile = board[CONST.BOARD_HEIGHT-destination.y()-1][destination.x()];
        if (tile.getMotive().equals(DIRT)) {
            tile.setArisingMotive(direction, type);
            animatedTiles.add(tile);
        }
    }
}
