package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;
import com.dev.flying.kiwi.gamecore.factories.GameObjectFactory;

/**
 * EnemyCreator
 *
 * Allows creation of a Enemy entity, containing a Box2D body and associated fixtures.
 *
 * Created by Steven on 9/28/2015.
 */
public class EnemyCreator extends GameObjectCreator {
    public EnemyCreator(PooledEngine engine, World world, Stage stage) {
        super(engine, world, stage);
    }

    @Override
    public Entity create(float x, float y) {
        Entity enemy = engine.createEntity();
        Actor actor = ShapeActorFactory.generateShape();
        Body body = GameObjectFactory.createEnemy(world, x, y);
        body.setUserData(enemy);

        engine.addEntity(
                enemy
                        .add(new Box2DBodyComponent(body))
                        .add(new ActorComponent(actor))
                        .add(new EnemyComponent())
        );
        stage.addActor(actor);

        return enemy;
    }
}
