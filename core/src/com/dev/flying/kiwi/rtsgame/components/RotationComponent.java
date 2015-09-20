package com.dev.flying.kiwi.rtsgame.components;

import com.badlogic.ashley.core.Component;

/** Sometimes you know, a item is rotated.
 * Created by Steven on 9/20/2015.
 */
public class RotationComponent implements Component{
    public float angle;

    public RotationComponent(float degrees) {
        angle = degrees;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "=(" + this.angle + ")";
    }
}
