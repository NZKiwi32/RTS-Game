package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * GameObjectCreator
 *
 * Base class to extend from that handles obtaining the engine, world and stage for classes which inherit.
 *
 * Created by Steven on 9/28/2015.
 */
public abstract class GameObjectCreator implements GameObjectCreatorInterface {
    protected PooledEngine engine;
    protected World world;
    protected Stage stage;

    public GameObjectCreator (PooledEngine engine, World world, Stage stage) {
        this.engine = engine;
        this.world = world;
        this.stage = stage;
    }

    /**
     * Creates a game object at the given co-ordinates.
     * Override to do your specific implementation, default does nothing.
     * @param x x co-ordinate
     * @param y y co-ordinate
     * @return the entity after creation
     */
    @Override
    public Entity create(float x, float y) {
        return null;
    }
}
