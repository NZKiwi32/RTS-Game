package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.dev.flying.kiwi.gamecore.actors.ShapeActor;

/**
 * ActorComponent
 * Component for holding a @link
 * Created by Steven on 9/23/2015.
 */
public class ActorComponent implements Component {
    public ShapeActor actor;

    public ActorComponent(ShapeActor actor) {
        this.actor = actor;
    }
}
