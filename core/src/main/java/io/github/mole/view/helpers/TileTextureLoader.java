package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.TileType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.mole.utils.TileType.*;

public class TileTextureLoader {
    private final Map<TileType, List<Texture>> stillTiles;
    private final Map<TileType, List<Texture>> leftArisingTiles;
    private final Map<TileType, List<Texture>> rightArisingTiles;
    private final Map<TileType, List<Texture>> upArisingTiles;
    private final Map<TileType, List<Texture>> downArisingTiles;

    public TileTextureLoader() {
        stillTiles = new EnumMap<>(TileType.class);
        stillTiles.put(DIRT, getTextureList("textures/tiles/dirt/dirt",3));
        stillTiles.put(TUNNEL, getTextureList("textures/tiles/tunnel/tunnel",3));
        stillTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));
        stillTiles.put(STONE, getTextureList("textures/tiles/stone/stone",3));

        leftArisingTiles = new EnumMap<>(TileType.class);
        leftArisingTiles.put(DIRT, getTextureList("textures/tiles/dirt/dirt_left",4));
        leftArisingTiles.put(TUNNEL, getTextureList("textures/tiles/tunnel/tunnel_left",4));
        leftArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        rightArisingTiles = new EnumMap<>(TileType.class);
        rightArisingTiles.put(DIRT, getTextureList("textures/tiles/dirt/dirt_right",4));
        rightArisingTiles.put(TUNNEL, getTextureList("textures/tiles/tunnel/tunnel_right",4));
        rightArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        upArisingTiles = new EnumMap<>(TileType.class);
        upArisingTiles.put(DIRT, getTextureList("textures/tiles/dirt/dirt_up",4));
        upArisingTiles.put(TUNNEL, getTextureList("textures/tiles/tunnel/tunnel_up",4));
        upArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        downArisingTiles = new EnumMap<>(TileType.class);
        downArisingTiles.put(DIRT, getTextureList("textures/tiles/dirt/dirt_down",4));
        downArisingTiles.put(TUNNEL, getTextureList("textures/tiles/tunnel/tunnel_down",4));
        downArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));
    }

    private List<Texture> getTextureList(String name, int count) {
        return IntStream.range(0, count).mapToObj(idx -> new Texture(name + "_" + idx + ".png")).collect(Collectors.toList());
    }

    public List<Texture> getStillMotive(TileType type) {
        return stillTiles.get(type);
    }

    public List<Texture> getArisingMotive(TileType type, MoveDirection direction) {
        return switch (direction) {
            case LEFT -> leftArisingTiles.get(type);
            case RIGHT -> rightArisingTiles.get(type);
            case UP -> upArisingTiles.get(type);
            case DOWN -> downArisingTiles.get(type);
            default -> null;
        };
    }

}
