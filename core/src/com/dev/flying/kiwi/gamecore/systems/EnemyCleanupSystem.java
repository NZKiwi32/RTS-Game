package com.dev.flying.kiwi.gamecore.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;
import com.dev.flying.kiwi.gamecore.components.PhysicsBodyComponent;
import com.dev.flying.kiwi.gamecore.components.RemoveComponent;
import com.dev.flying.kiwi.gamecore.factories.GameObjectFactory;

/**
 * This system accumulates a list of Entities with PhysicsBodyComponents and RemoveComponents to delete. It then deletes them outside of world.step().
 * Created by Steven on 9/27/2015.
 */
public class EnemyCleanupSystem extends IteratingSystem{
    private final ComponentMapper<PhysicsBodyComponent> physicsMapper;
    private Engine engine;
    private World world;
    private ObjectSet<Entity> removables;
    private Body player;

    public EnemyCleanupSystem(World world, Engine engine, Body player) {
        super(Family.all(PhysicsBodyComponent.class, RemoveComponent.class, EnemyComponent.class).get());
        this.physicsMapper = ComponentMapper.getFor(PhysicsBodyComponent.class);
        this.world = world;
        this.player = player;
        this.engine = engine;
        removables = new ObjectSet<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        removables.add(entity);
    }

    public void cleanUp() {
        CircleShape cs;
        FixtureDef fd;
        Entity e;
        Body contactBody;
        Vector2 localPos; // Player local position of the contact body

        if(!world.isLocked()) {
            ObjectSet.ObjectSetIterator<Entity> rIterator = removables.iterator();
            while(rIterator.hasNext()) {
                // Get next, and remove from iterator
                e = rIterator.next();rIterator.remove();

                contactBody = physicsMapper.get(e).body;
                // Get local space position for new fixture to be attached
                localPos = this.player.getLocalPoint(contactBody.getWorldCenter());

                // For each fixture (probably only 1) create a new fixture the same but on the player
                Array<Fixture> fixtures = contactBody.getFixtureList();
                for(Fixture f: fixtures) {
                    cs = (CircleShape) f.getShape();
                    cs.setPosition(localPos);
                    fd = GameObjectFactory.getEnemyFixtureDef(f.getShape());
                    this.player.createFixture(fd);

                    // Remove it from world, and engine.
                    world.destroyBody(contactBody);
                }


            }

        }
    }


}
