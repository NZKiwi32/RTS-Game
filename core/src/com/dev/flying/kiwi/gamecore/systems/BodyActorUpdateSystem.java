package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.dev.flying.kiwi.gamecore.actors.ShapeActor;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;

/**
 * This system renders sprites at a position based on the world, for a given actor.
 * Created by Steven on 9/23/2015.
 */
public class BodyActorUpdateSystem extends IteratingSystem {
    private ComponentMapper<Box2DBodyComponent> physicsMapper;
    private ComponentMapper<ActorComponent> actorMapper;

    private Array<Entity> entitiesToUpdate;

    public BodyActorUpdateSystem() {
        super(Family.all(Box2DBodyComponent.class, ActorComponent.class).get());
        this.physicsMapper = ComponentMapper.getFor(Box2DBodyComponent.class);
        this.actorMapper = ComponentMapper.getFor(ActorComponent.class);
        this.entitiesToUpdate = new Array<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entitiesToUpdate.add(entity);
    }

    public void drawRenderQueue(Batch batch) {
        ShapeActor actor;
        Body body;

        for (Entity e : this.entitiesToUpdate) {
            actor = actorMapper.get(e).actor;
            body = physicsMapper.get(e).body;

            actor.setPosition(body.getPosition().x, body.getPosition().y);
            actor.setRotation(MathUtils.radiansToDegrees * body.getAngle());

            actor.draw(batch, 1);
        }

        entitiesToUpdate.clear();
    }


}
