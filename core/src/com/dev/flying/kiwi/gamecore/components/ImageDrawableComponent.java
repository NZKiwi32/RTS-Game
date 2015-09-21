package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/** Image Drawable Component
 * Holds a android resource for a bitmap image to be drawn.
 * Created by Steven on 8/4/2015.
 */
public class ImageDrawableComponent implements Component {
    public Texture texture;

    public ImageDrawableComponent(Texture texture) {
        this.texture = texture;
    }
}
