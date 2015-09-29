package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.dev.flying.kiwi.gamecore.components.PositionComponent;
import com.dev.flying.kiwi.gamecore.components.SpawnerComponent;
import com.dev.flying.kiwi.gamecore.prefabs.GameObjectCreatorInterface;

/**
 * SpawnSystem
 *
 * On a certain interval and does:
 * - Randomly selects a Spawner entity from the Family
 * - Spawns units calling toSpawn.create {@link GameObjectCreatorInterface}.
 *
 * Created by Steven on 9/14/2015.
 */
public class SpawnSystem extends IntervalSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    ImmutableArray<Entity> entities;
    PooledEngine engine;

    GameObjectCreatorInterface toSpawn;
    int maxEnemies = 1;

    public SpawnSystem(float interval, GameObjectCreatorInterface toSpawn) {
        super(interval);
        this.toSpawn = toSpawn;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpawnerComponent.class).get());
    }

    private Entity randomSpawner() {
        return entities.get(MathUtils.random(entities.size()-1));
    }

    @Override
    protected void updateInterval() {
        if(entities.size() > 0) {
            Entity spawner = randomSpawner();

            toSpawn.create(pm.get(spawner).x, pm.get(spawner).y);
        }
    }
}
