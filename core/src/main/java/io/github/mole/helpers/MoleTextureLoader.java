package io.github.mole.helpers;

import com.badlogic.gdx.graphics.Texture;
import io.github.mole.presenter.MoveDirection;

import java.util.EnumMap;
import java.util.Map;

import static io.github.mole.presenter.MoveDirection.*;

public class MoleTextureLoader {
    private final Map<MoveDirection, Texture> moves;
    public MoleTextureLoader() {
        moves = new EnumMap<>(MoveDirection.class);
        moves.put(LEFT, new Texture("textures/mole/normal_left.png"));
        moves.put(RIGHT, new Texture("textures/mole/normal_right.png"));
        moves.put(UP, new Texture("textures/mole/normal_up.png"));
        moves.put(DOWN, new Texture("textures/mole/normal_down.png"));
        moves.put(NONE, new Texture("textures/mole/normal_none.png"));
    }

    public Texture getTextures(MoveDirection direction) {
        return moves.get(direction);
    }
}
