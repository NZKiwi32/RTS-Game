package com.dev.flying.kiwi.rtsgame.components;

import com.badlogic.ashley.core.Component;

/**
 * A component for 2D PositionComponent
 * Created by Steven on 7/29/2015.
 */
public class PositionComponent implements Component {
    public float x;
    public float y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return PositionComponent.class.getSimpleName() + "=(" + this.x + "," + this.y + ")";
    }
}
