package io.github.mole.presenter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.helpers.TileTextureLoader;
import io.github.mole.view.TileView;
import io.github.mole.CONST;

import static io.github.mole.presenter.TileType.*;

public class BoardPresenter {
    TileTextureLoader loader;
    TileView[][] board;
    private final int height = CONST.BOARD_HEIGHT;
    private final int width = CONST.BOARD_WIDTH;
    public BoardPresenter(){
        loader = new TileTextureLoader();

        board = new TileView[height][width];
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j] = new TileView(loader, new Vector2(j*50 + 50, (height-i)*50));
                if (i == 0) board[i][j].setTexture(GRASS);
                else board[i][j].setTexture(DIRT);
            }
        }
        board[1][2].setTexture(TUNNEL);
        board[2][2].setTexture(TUNNEL);
        board[3][2].setTexture(TUNNEL);
        board[3][1].setTexture(TUNNEL);
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j].draw(batch);
            }
        }
    }

    public void changeTile(BoardPosition destination, TileType tileType) {
        if (tileType == DIRT)
            board[CONST.BOARD_HEIGHT-destination.y()-1][destination.x()].setTexture(DIRT);
        if (tileType == TUNNEL)
            board[CONST.BOARD_HEIGHT-destination.y()-1][destination.x()].setTexture(TUNNEL);
    }
}
