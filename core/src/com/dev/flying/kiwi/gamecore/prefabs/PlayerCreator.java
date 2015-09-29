package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dev.flying.kiwi.gamecore.actors.ShapeActor;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory;
import com.dev.flying.kiwi.gamecore.components.ActorComponent;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;
import com.dev.flying.kiwi.gamecore.components.PlayerComponent;
/**
 * PlayerCreator
 *
 * Allows creation of a player entity, containing a Box2D body and associated fixtures.
 *
 * Created by Steven on 9/28/2015.
 */
public class PlayerCreator extends GameObjectCreator {
    public PlayerCreator(PooledEngine engine, World world, Stage stage) {
        super(engine, world, stage);
    }

    @Override
    public Entity create(float x, float y) {
        Entity player = engine.createEntity();
        ShapeActor playerActor = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX, new Vector2(2,2));
        playerActor.setName("Player");
        Body body = PlayerCreator.createPlayer(world);
        body.setUserData(player);

        engine.addEntity(
                player
                        .add(new Box2DBodyComponent(body))
                        .add(new ActorComponent(playerActor))
                        .add(new PlayerComponent())
        );
        stage.addActor(playerActor);
        return player;
    }

    private static Body createPlayer(World world) {
        return createPlayer(world, 0, 0);
    }

    private static Body createPlayer(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        Body player = world.createBody(bodyDef);

        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(1f);

        // fixture definition
        fixtureDef.shape = ballShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = 0;

        player.createFixture(fixtureDef);

        ballShape.dispose();

        return player;
    }
}
