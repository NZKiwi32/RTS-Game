package com.dev.flying.kiwi.rtsgame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.dev.flying.kiwi.rtsgame.components.*;

/**
 * Created by Steven on 9/14/2015.
 */
public class SpawnSystem extends IntervalSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<SpawnerComponent> vm = ComponentMapper.getFor(SpawnerComponent.class);
    ImmutableArray<Entity> entities;
    PooledEngine engine;
    public SpawnSystem(float interval) {
        super(interval);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpawnerComponent.class).get());
    }

    @Override
    protected void updateInterval() {
        Entity spawnedEntity;
        for (Entity entity : entities) {
            spawnedEntity = this.engine.createEntity();
            engine.addEntity(spawnedEntity);
            spawnedEntity
                    .add(new PositionComponent(pm.get(entity)))
                    .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("tree.png"))))
                    .add(new TreeComponent())
                    .add(new VelocityComponent(-500, 500));
        }
    }
}
