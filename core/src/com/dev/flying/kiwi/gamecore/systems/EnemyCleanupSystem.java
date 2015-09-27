package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ObjectSet;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;
import com.dev.flying.kiwi.gamecore.components.PhysicsBodyComponent;
import com.dev.flying.kiwi.gamecore.components.RemoveComponent;

/**
 * This system accumulates a list of Entities with PhysicsBodyComponents and RemoveComponents to delete. It then deletes them outside of world.step().
 * Created by Steven on 9/27/2015.
 */
public class EnemyCleanupSystem extends IteratingSystem{
    private final ComponentMapper<PhysicsBodyComponent> physicsMapper;
    private World world;
    private Engine engine;
    private ObjectSet<Entity> removables;

    public EnemyCleanupSystem(World world, Engine engine) {
        super(Family.all(PhysicsBodyComponent.class, RemoveComponent.class, EnemyComponent.class).get());
        this.physicsMapper = ComponentMapper.getFor(PhysicsBodyComponent.class);
        this.world = world;
        this.engine = engine;
        removables = new ObjectSet<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        removables.add(entity);
    }

    public void cleanUp() {
        if(!world.isLocked()) {
            ObjectSet.ObjectSetIterator<Entity> rIterator = removables.iterator();
            while(rIterator.hasNext()) {
                // Get next, and remove from iterator
                Entity e = rIterator.next();
                rIterator.remove();

                // Remove it from world, and engine.
                world.destroyBody(physicsMapper.get(e).body);
                engine.removeEntity(e);
            }

        }
    }


}
