package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.TileType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static io.github.mole.utils.TileType.*;

public class TileTextureLoader {
    private final Map<TileType, List<Texture>> stillTiles;
    private final Map<TileType, List<Texture>> leftArisingTiles;
    private final Map<TileType, List<Texture>> rightArisingTiles;
    private final Map<TileType, List<Texture>> upArisingTiles;
    private final Map<TileType, List<Texture>> downArisingTiles;

    public TileTextureLoader() {
        stillTiles = new EnumMap<>(TileType.class);

        stillTiles.put(DIRT, List.of(new Texture("textures/tiles/dirt/dirt_0.png"),
            new Texture("textures/tiles/dirt/dirt_1.png"),
            new Texture("textures/tiles/dirt/dirt_2.png")));
        stillTiles.put(TUNNEL, List.of(new Texture("textures/tiles/tunnel/tunnel_0.png"),
            new Texture("textures/tiles/tunnel/tunnel_1.png"),
            new Texture("textures/tiles/tunnel/tunnel_2.png")));
        stillTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        leftArisingTiles = new EnumMap<>(TileType.class);
        leftArisingTiles.put(DIRT, List.of(new Texture("textures/tiles/dirt/dirt_left_0.png"),
            new Texture("textures/tiles/dirt/dirt_left_1.png"),
            new Texture("textures/tiles/dirt/dirt_left_2.png"),
            new Texture("textures/tiles/dirt/dirt_left_3.png")));
        leftArisingTiles.put(TUNNEL, List.of(new Texture("textures/tiles/tunnel/tunnel_left_0.png"),
            new Texture("textures/tiles/tunnel/tunnel_left_1.png"),
            new Texture("textures/tiles/tunnel/tunnel_left_2.png"),
            new Texture("textures/tiles/tunnel/tunnel_left_3.png")));
        leftArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        rightArisingTiles = new EnumMap<>(TileType.class);
        rightArisingTiles.put(DIRT, List.of(new Texture("textures/tiles/dirt/dirt_right_0.png"),
            new Texture("textures/tiles/dirt/dirt_right_1.png"),
            new Texture("textures/tiles/dirt/dirt_right_2.png"),
            new Texture("textures/tiles/dirt/dirt_right_3.png")));
        rightArisingTiles.put(TUNNEL, List.of(new Texture("textures/tiles/tunnel/tunnel_right_0.png"),
            new Texture("textures/tiles/tunnel/tunnel_right_1.png"),
            new Texture("textures/tiles/tunnel/tunnel_right_2.png"),
            new Texture("textures/tiles/tunnel/tunnel_right_3.png")));
        rightArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        upArisingTiles = new EnumMap<>(TileType.class);
        upArisingTiles.put(DIRT, List.of(new Texture("textures/tiles/dirt/dirt_up_0.png"),
            new Texture("textures/tiles/dirt/dirt_up_1.png"),
            new Texture("textures/tiles/dirt/dirt_up_2.png"),
            new Texture("textures/tiles/dirt/dirt_up_3.png")));
        upArisingTiles.put(TUNNEL, List.of(new Texture("textures/tiles/tunnel/tunnel_up_0.png"),
            new Texture("textures/tiles/tunnel/tunnel_up_1.png"),
            new Texture("textures/tiles/tunnel/tunnel_up_2.png"),
            new Texture("textures/tiles/tunnel/tunnel_up_3.png")));
        upArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));

        downArisingTiles = new EnumMap<>(TileType.class);
        downArisingTiles.put(DIRT, List.of(new Texture("textures/tiles/dirt/dirt_down_0.png"),
            new Texture("textures/tiles/dirt/dirt_down_1.png"),
            new Texture("textures/tiles/dirt/dirt_down_2.png"),
            new Texture("textures/tiles/dirt/dirt_down_3.png")));
        downArisingTiles.put(TUNNEL, List.of(new Texture("textures/tiles/tunnel/tunnel_down_0.png"),
            new Texture("textures/tiles/tunnel/tunnel_down_1.png"),
            new Texture("textures/tiles/tunnel/tunnel_down_2.png"),
            new Texture("textures/tiles/tunnel/tunnel_down_3.png")));
        downArisingTiles.put(AIR, List.of(new Texture("textures/tiles/grass.png")));
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
