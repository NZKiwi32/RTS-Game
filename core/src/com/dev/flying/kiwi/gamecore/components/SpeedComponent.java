package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Steven on 9/13/2015.
 */
public class SpeedComponent implements Component {
    public float speed;

    public SpeedComponent (float speed) {
        this.speed = speed;
    }
}
