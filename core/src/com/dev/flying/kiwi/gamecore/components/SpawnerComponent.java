package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;

/**
 * A component for 2D PositionComponent
 * Created by Steven on 7/29/2015.
 */
public class SpawnerComponent implements Component {
    public float x;
    public float y;

    public SpawnerComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return SpawnerComponent.class.getSimpleName() + "=(" + this.x + "," + this.y + ")";
    }
}

