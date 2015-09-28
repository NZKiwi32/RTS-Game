package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;

/**
 * PositionComponent
 * A component for 2D Position
 * Created by Steven on 7/29/2015.
 */
public class PositionComponent implements Component {
    public float x;
    public float y;

    public PositionComponent(PositionComponent p) {
        this.x = p.x;
        this.y = p.y;
    }
    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return PositionComponent.class.getSimpleName() + "=(" + this.x + "," + this.y + ")";
    }
}
