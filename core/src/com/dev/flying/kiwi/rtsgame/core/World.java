package com.dev.flying.kiwi.rtsgame.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dev.flying.kiwi.rtsgame.components.*;

import java.util.ArrayList;

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
                .add(new VelocityComponent(0,0))
                .add(new PlayerControlledComponent())
                .add(new SpeedComponent(10000));

        return player;
    }

    /**
     * Create the base entities in the world.
     */
    protected void createEntities() {
        // generatePlayerShape();
        generateTownBarracks();
        generateForests();
    }

    private void generateForests() {
        PositionComponent[] treePositions = new PositionComponent[3];
        treePositions[0] = new PositionComponent(125,250);
        treePositions[1] = new PositionComponent(65,230);
        treePositions[2] = new PositionComponent(195,320);

        for (int i = 0; i<treePositions.length; i++) {
            Entity tree = this.engine.createEntity();
            engine.addEntity(tree);
            tree
                    .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("tree.png"))))
                    .add(treePositions[i])
                    .add(new TreeComponent());
        }
    }

    private Entity generateTownBarracks() {
        Entity humanBarracks = this.engine.createEntity();

        engine.addEntity(humanBarracks);

        humanBarracks
                .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("human-barracks.png"))))
                .add(new PositionComponent(550,50));

        return humanBarracks;

    }
}
