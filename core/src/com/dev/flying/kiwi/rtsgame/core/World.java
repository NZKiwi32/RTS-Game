package com.dev.flying.kiwi.rtsgame.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dev.flying.kiwi.rtsgame.components.ImageDrawableComponent;
import com.dev.flying.kiwi.rtsgame.components.PositionComponent;
import com.dev.flying.kiwi.rtsgame.components.VelocityComponent;

/**
 * World
 * This class which constructs entities in the world.
 * Created by Steven on 8/2/2015.
 */
public class World {
    private PooledEngine engine;

    public World(PooledEngine engine) {
        this.engine = engine;
    }

    /**
     * Create all the objects in the world.
     */
    public void create() {
        createEntities();
    }

    protected Entity generatePlayerShape() {
        Entity player = this.engine.createEntity();

        engine.addEntity(player);

        player
                .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("player.jpg"))))
                .add(new PositionComponent(10, 10))
                .add(new VelocityComponent(10,10));

//
//        img = new Texture(Gdx.files.internal("player.jpg"));

        return player;
    }

    /**
     * Create the base entities in the world.
     */
    protected void createEntities() {
        generatePlayerShape();
    }
}
