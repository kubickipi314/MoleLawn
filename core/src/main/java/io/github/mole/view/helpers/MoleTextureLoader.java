package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.mole.utils.MoveDirection.*;

public class MoleTextureLoader {
    private final Map<MoveDirection, List<Texture>> normal;
    private final EnumMap<MoveDirection, List<Texture>> digging;
    private final EnumMap<MoveDirection, List<Texture>> free;
    private final EnumMap<MoveDirection, List<Texture>> dying;

    public MoleTextureLoader() {
        normal = new EnumMap<>(MoveDirection.class);
        normal.put(LEFT, getTextureList("textures/mole/normal/left",2));
        normal.put(RIGHT, getTextureList("textures/mole/normal/right",2));
        normal.put(UP, getTextureList("textures/mole/normal/up",2));
        normal.put(DOWN, getTextureList("textures/mole/normal/down",2));
        normal.put(NONE, getTextureList("textures/mole/normal/none",2));

        digging = new EnumMap<>(MoveDirection.class);
        digging.put(LEFT, getTextureList("textures/mole/dig/left",2));
        digging.put(RIGHT, getTextureList("textures/mole/dig/right",2));
        digging.put(UP, getTextureList("textures/mole/dig/up",2));
        digging.put(DOWN, getTextureList("textures/mole/dig/down",2));
        digging.put(NONE, getTextureList("textures/mole/dig/none",2));

        free = new EnumMap<>(MoveDirection.class);
        free.put(LEFT, getTextureList("textures/mole/free/left",2));
        free.put(RIGHT, getTextureList("textures/mole/free/right",2));
        free.put(UP, getTextureList("textures/mole/dig/up",2));
        free.put(DOWN, getTextureList("textures/mole/dig/down",2));
        free.put(NONE, getTextureList("textures/mole/dig/none",2));

        dying = new EnumMap<>(MoveDirection.class);
        dying.put(LEFT, getTextureList("textures/mole/dying/left",2));
        dying.put(RIGHT, getTextureList("textures/mole/dying/right",2));
        dying.put(UP, getTextureList("textures/mole/dying/up",1));
        dying.put(DOWN, getTextureList("textures/mole/dying/down",1));
        dying.put(NONE, getTextureList("textures/mole/dying/none",1));
    }

    private List<Texture> getTextureList(String name, int count) {
        return IntStream.range(0, count).mapToObj(idx -> new Texture(name + "_" + idx + ".png")).collect(Collectors.toList());
    }

    public List<Texture> getMovingMotive(MoveDirection direction, MoveStyle style) {
        return switch(style) {
            case DIGGING -> digging.get(direction);
            case FREE -> free.get(direction);
            default -> null;
        };
    }

    public List<Texture> getNormalMotive(MoveDirection direction) {
        return normal.get(direction);
    }

    public List<Texture> getDeadMotive(MoveDirection direction) {
        return dying.get(direction);
    }
}
