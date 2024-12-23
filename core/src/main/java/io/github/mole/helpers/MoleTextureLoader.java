package io.github.mole.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.presenter.utils.MoveDirection;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static io.github.mole.presenter.utils.MoveDirection.*;

public class MoleTextureLoader {
    private final Map<MoveDirection, List<Texture>> normal;

    private final EnumMap<MoveDirection, List<Texture>> digging;

    public MoleTextureLoader() {
        normal = new EnumMap<>(MoveDirection.class);
        normal.put(LEFT, List.of(new Texture("textures/mole/normal/normal_left_0.png"),
            new Texture("textures/mole/normal/normal_left_1.png")));
        normal.put(RIGHT, List.of(new Texture("textures/mole/normal/normal_right_0.png"),
            new Texture("textures/mole/normal/normal_right_1.png")));
        normal.put(UP, List.of(new Texture("textures/mole/normal/normal_up_0.png"),
            new Texture("textures/mole/normal/normal_up_1.png")));
        normal.put(DOWN, List.of(new Texture("textures/mole/normal/normal_down_0.png"),
            new Texture("textures/mole/normal/normal_down_1.png")));
        normal.put(NONE, List.of(new Texture("textures/mole/normal/normal_none_0.png"),
            new Texture("textures/mole/normal/normal_none_1.png")));

        digging = new EnumMap<>(MoveDirection.class);
        digging.put(LEFT, List.of(new Texture("textures/mole/dig/dig_left_0.png"),
            new Texture("textures/mole/dig/dig_left_1.png")));
        digging.put(RIGHT, List.of(new Texture("textures/mole/dig/dig_right_0.png"),
            new Texture("textures/mole/dig/dig_right_1.png")));
        digging.put(UP, List.of(new Texture("textures/mole/dig/dig_up_0.png"),
            new Texture("textures/mole/dig/dig_up_1.png")));
        digging.put(DOWN, List.of(new Texture("textures/mole/dig/dig_down_0.png"),
            new Texture("textures/mole/dig/dig_down_1.png")));
        digging.put(NONE, List.of(new Texture("textures/mole/dig/dig_none_0.png"),
            new Texture("textures/mole/dig/dig_none_1.png")));
    }

    public List<Texture> getDiggingMotive(MoveDirection direction) {
        return digging.get(direction);
    }

    public List<Texture> getNormalMotive(MoveDirection direction) {
        return normal.get(direction);
    }
}
