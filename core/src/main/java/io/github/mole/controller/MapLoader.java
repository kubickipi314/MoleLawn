package io.github.mole.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import io.github.mole.utils.TileType;

import static io.github.mole.utils.TileType.*;

public class MapLoader {
    public MapLoader() {
    }

    public TileType[][] loadMap(String filePath) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filePath));
        int width = pixmap.getWidth();
        int height = pixmap.getHeight();

        TileType[][] colorArray = new TileType[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixmap.getPixel(x, y);
                switch (pixel) {
                    case 0x597450FF: colorArray[y][x] = AIR; break;
                    case 0x463228FF: colorArray[y][x] = DIRT; break;
                    case 0x645046FF: colorArray[y][x] = TUNNEL; break;
                    case 0x666666FF: colorArray[y][x] = STONE; break;
                }
            }
        }
        pixmap.dispose();
        return colorArray;
    }
}
