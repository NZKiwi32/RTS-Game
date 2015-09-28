package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dev.flying.kiwi.gamecore.components.PositionComponent;
import com.dev.flying.kiwi.gamecore.components.SpawnerComponent;

/**
 * PlayerCreator
 *
 * Allows creation of a player entity, containing a Box2D body and associated fixtures.
 *
 * Created by Steven on 9/28/2015.
 */
public class SpawnerCreator extends GameObjectCreator {
    public SpawnerCreator(PooledEngine engine, World world, Stage stage) {
        super(engine, world, stage);
    }

    @Override
    public Entity create(float x, float y) {
        Entity spawner = engine.createEntity();
        engine.addEntity(
                spawner
                    .add(new SpawnerComponent())
                    .add(new PositionComponent(x, y))
        );
        return spawner;
    }
}
