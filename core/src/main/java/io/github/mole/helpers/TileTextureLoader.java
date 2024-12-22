package io.github.mole.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.presenter.TileType;

import java.util.EnumMap;
import java.util.Map;

import static io.github.mole.presenter.TileType.*;

public class TileTextureLoader {
    private final Map<TileType, Texture> tiles;

    public TileTextureLoader() {
        tiles = new EnumMap<>(TileType.class);
        tiles.put(DIRT, new Texture("textures/tiles/dirt.png"));
        tiles.put(TUNNEL, new Texture("textures/tiles/tunnel.png"));
        tiles.put(GRASS, new Texture("textures/tiles/grass.png"));
    }

    public Texture getTileTexture(TileType type) {
        return tiles.get(type);
    }

}
