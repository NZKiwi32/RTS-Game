package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Steven on 9/23/2015.
 */
public class PhysicsBodyComponent implements Component {
    public Body body;

    public PhysicsBodyComponent(Body body) {
        this.body = body;
    }
}
