package io.github.mole.view.helpers;

import com.badlogic.gdx.graphics.Texture;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EndingTextureLoader {
    List<Texture> diedFromTextures;
    List<Texture> retryTextures;

    public EndingTextureLoader() {
        //diedFromTextures = getTextureList("textures/ending/died_from", 6);
        retryTextures = getTextureList("textures/ending/retry", 17);

    }

    private List<Texture> getTextureList(String name, int count) {
        return IntStream.range(0, count).mapToObj(idx -> new Texture(name + "_" + idx + ".png")).collect(Collectors.toList());
    }

    public List<Texture> getDiedFrom() {
        return diedFromTextures;
    }

    public List<Texture> getRetry() {
        return retryTextures;
    }
}

