package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;

/**
 * EnemyCreator
 *
 * Allows creation of a Enemy entity, containing a Box2D body and associated fixtures.
 * Allows obtaining a fixtureDef for an enemy
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
        Body body = EnemyCreator.createEnemy(world, x, y);
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

    private static Body createEnemy(World world) {
        return createEnemy(world, 0, 0);
    }

    private static Body createEnemy(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();


        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(0.75f);
        FixtureDef fixtureDef = getEnemyFixtureDef(ballShape);
        // ballShape.dispose();

        body.createFixture(fixtureDef);



        return body;
    }

    public static FixtureDef getEnemyFixtureDef(Shape s) {
        FixtureDef fixtureDef = new FixtureDef();

        // fixture definition
        fixtureDef.shape = s;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;

        return fixtureDef;
    }
}
