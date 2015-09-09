package com.dev.flying.kiwi.rtsgame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dev.flying.kiwi.rtsgame.components.PositionComponent;
import com.dev.flying.kiwi.rtsgame.components.VelocityComponent;

/**
 * A system for moving objects which have a position and velocity
 * Created by Steven on 7/29/2015.
 */
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }
}
