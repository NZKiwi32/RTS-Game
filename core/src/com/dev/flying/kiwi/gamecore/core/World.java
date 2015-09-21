package com.dev.flying.kiwi.gamecore.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dev.flying.kiwi.gamecore.components.*;

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
                .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("hex/player_hex.png"))))
                .add(new PositionComponent(Gdx.graphics.getWidth()/2 - 32f, Gdx.graphics.getHeight()/2 - 32f)) // TODO Calc width/height
                .add(new VelocityComponent(0,0))
                .add(new RotationComponent(0));

        return player;
    }

    /**
     * Create the base entities in the world.
     */
    protected void createEntities() {
        generatePlayerShape();
        //generateTownBarracks();
        //generateForests();
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
                    .add(treePositions[i]);
        }
    }

    private Entity generateTownBarracks() {
        Entity humanBarracks = this.engine.createEntity();

        engine.addEntity(humanBarracks);

        humanBarracks
                .add(new ImageDrawableComponent(new Texture(Gdx.files.internal("human-barracks.png"))))
                .add(new PositionComponent(550, 50))
                .add(new SpawnerComponent(500,75));

        return humanBarracks;

    }
}
