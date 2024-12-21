package io.github.mole.presenter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.mole.view.TileView;
import io.github.mole.CONST;

public class BoardPresenter {
    TileView[][] board;
    private final int height = CONST.BOARD_HEIGHT;
    private final int width = CONST.BOARD_WIDTH;

    public BoardPresenter(){
        Texture dirt = new Texture("textures/tiles/grass.png");
        Texture tunnel = new Texture("textures/tiles/tunnel.png");
        Texture grass = new Texture("textures/tiles/grass.png");
        board = new TileView[height][width];
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j] = new TileView(new Vector2(j*50 + 50, (height-i)*50));
                if (i == 0) board[i][j].setTexture(grass);
            }
        }
        board[1][2].setTexture(tunnel);
        board[2][2].setTexture(tunnel);
        board[3][2].setTexture(tunnel);
        board[3][1].setTexture(tunnel);
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                board[i][j].draw(batch);
            }
        }
    }
}
