package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;
import com.dev.flying.kiwi.gamecore.components.PhysicsBodyComponent;

/**
 * Created by Steven on 9/27/2015.
 */
public class EnemyMovementSystem extends IteratingSystem {
    private ComponentMapper<PhysicsBodyComponent> physicsMapper;

    private final Vector2 target;

    // Current Variables reused per loop
    private Body body;
    private Vector2 dir;

    public EnemyMovementSystem(Vector2 target) {
        super(Family.all(PhysicsBodyComponent.class, ActorComponent.class, EnemyComponent.class).get());
        this.physicsMapper = ComponentMapper.getFor(PhysicsBodyComponent.class);
        this.target = target;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        body = physicsMapper.get(entity).body;
        dir = target.sub(body.getPosition()).nor().scl(body.getPosition().dst(target) / 100) ;

        body.applyLinearImpulse(dir, body.getPosition(), true);
    }
}