package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;

/**
 * This system renders sprites at a position based on the world, for a given actor.
 * Created by Steven on 9/23/2015.
 */
public class PhysicsActorRenderSystem extends IteratingSystem {
    private ComponentMapper<Box2DBodyComponent> physicsMapper;
    private ComponentMapper<ActorComponent> actorMapper;

    private Array<Entity> renderQueue;
    private Batch batch;

    public PhysicsActorRenderSystem(Batch batch) {
        super(Family.all(Box2DBodyComponent.class, ActorComponent.class).get());
        this.physicsMapper = ComponentMapper.getFor(Box2DBodyComponent.class);
        this.actorMapper = ComponentMapper.getFor(ActorComponent.class);
        this.renderQueue = new Array<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public void drawRenderQueue() {
        TextureRegion tr;
//        for (Entity e : this.renderQueue) {
//            tr = new TextureRegion(drawableMapper.get(e).texture, 0, 0, 64,64);
//            batch.draw(tr, positionMapper.get(e).x, positionMapper.get(e).y, positionMapper.get(e).x, positionMapper.get(e).y, 32f, 32f, 1.0f, 1.0f, 10f);
//            //batch.draw(drawableMapper.get(e).texture, positionMapper.get(e).x, positionMapper.get(e).y);
//        }
        renderQueue.clear();
    }


}
