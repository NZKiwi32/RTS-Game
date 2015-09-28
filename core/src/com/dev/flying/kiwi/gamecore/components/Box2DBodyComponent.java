package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Box2DBodyComponent
 * Holds the Box2d Body for an entity
 * Created by Steven on 9/23/2015.
 */
public class Box2DBodyComponent implements Component {
    public Body body;

    public Box2DBodyComponent(Body body) {
        this.body = body;
    }
}
