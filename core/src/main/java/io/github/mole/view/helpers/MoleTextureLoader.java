package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.utils.MoveDirection;
import io.github.mole.utils.MoveStyle;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static io.github.mole.utils.MoveDirection.*;

public class MoleTextureLoader {
    private final Map<MoveDirection, List<Texture>> normal;
    private final EnumMap<MoveDirection, List<Texture>> digging;
    private final EnumMap<MoveDirection, List<Texture>> free;
    private final EnumMap<MoveDirection, List<Texture>> dying;

    public MoleTextureLoader() {
        normal = new EnumMap<>(MoveDirection.class);
        normal.put(LEFT, List.of(new Texture("textures/mole/normal/left_0.png"),
            new Texture("textures/mole/normal/left_1.png")));
        normal.put(RIGHT, List.of(new Texture("textures/mole/normal/right_0.png"),
            new Texture("textures/mole/normal/right_1.png")));
        normal.put(UP, List.of(new Texture("textures/mole/normal/up_0.png"),
            new Texture("textures/mole/normal/up_1.png")));
        normal.put(DOWN, List.of(new Texture("textures/mole/normal/down_0.png"),
            new Texture("textures/mole/normal/down_1.png")));
        normal.put(NONE, List.of(new Texture("textures/mole/normal/none_0.png"),
            new Texture("textures/mole/normal/none_1.png")));

        digging = new EnumMap<>(MoveDirection.class);
        digging.put(LEFT, List.of(new Texture("textures/mole/dig/left_0.png"),
            new Texture("textures/mole/dig/left_1.png")));
        digging.put(RIGHT, List.of(new Texture("textures/mole/dig/right_0.png"),
            new Texture("textures/mole/dig/right_1.png")));
        digging.put(UP, List.of(new Texture("textures/mole/dig/up_0.png"),
            new Texture("textures/mole/dig/up_1.png")));
        digging.put(DOWN, List.of(new Texture("textures/mole/dig/down_0.png"),
            new Texture("textures/mole/dig/down_1.png")));
        digging.put(NONE, List.of(new Texture("textures/mole/dig/none_0.png"),
            new Texture("textures/mole/dig/none_1.png")));

        free = new EnumMap<>(MoveDirection.class);
        free.put(LEFT, List.of(new Texture("textures/mole/dig/left_0.png"),
            new Texture("textures/mole/dig/left_1.png")));
        free.put(RIGHT, List.of(new Texture("textures/mole/dig/right_0.png"),
            new Texture("textures/mole/dig/right_1.png")));
        free.put(UP, List.of(new Texture("textures/mole/dig/up_0.png"),
            new Texture("textures/mole/dig/up_1.png")));
        free.put(DOWN, List.of(new Texture("textures/mole/dig/down_0.png"),
            new Texture("textures/mole/dig/down_1.png")));
        free.put(NONE, List.of(new Texture("textures/mole/dig/none_0.png"),
            new Texture("textures/mole/dig/none_1.png")));

        dying = new EnumMap<>(MoveDirection.class);
        dying.put(LEFT, List.of(new Texture("textures/mole/dying/left_0.png"),
            new Texture("textures/mole/dying/left_1.png")));
        dying.put(RIGHT, List.of(new Texture("textures/mole/dying/right_0.png"),
            new Texture("textures/mole/dying/right_1.png")));
        dying.put(UP, List.of(new Texture("textures/mole/dying/up_0.png"),
            new Texture("textures/mole/dying/up_0.png")));
        dying.put(DOWN, List.of(new Texture("textures/mole/dying/down_0.png"),
            new Texture("textures/mole/dying/down_0.png")));
        dying.put(NONE, List.of(new Texture("textures/mole/dying/none_0.png"),
            new Texture("textures/mole/dying/none_0.png")));
    }

    public List<Texture> getMovingMotive(MoveDirection direction, MoveStyle style) {
        return switch(style) {
            case DIGGING -> digging.get(direction);
            case FREE -> free.get(direction);
            case DYING -> dying.get(direction);
        };
    }

    public List<Texture> getNormalMotive(MoveDirection direction) {
        return normal.get(direction);
    }

    public List<Texture> getDeadMotive(MoveDirection direction) {
        return dying.get(direction);
    }
}
