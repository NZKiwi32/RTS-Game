package com.dev.flying.kiwi.gamecore.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * ActorComponent
 * Component for holding a @link
 * Created by Steven on 9/23/2015.
 */
public class ActorComponent implements Component {
    public Actor actor;

    public ActorComponent(Actor actor) {
        this.actor = actor;
    }
}
