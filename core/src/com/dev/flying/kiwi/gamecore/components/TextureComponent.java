package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Texture Component
 * For associating a texture with an entity
 * Created by Steven on 8/4/2015.
 */
public class TextureComponent implements Component {
    public Texture texture;

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }
}
