package com.dev.flying.kiwi.gamecore.factories;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Creates a player Box2d body
 * Created by Steven on 9/23/2015.
 */
public class GameObjectFactory {
    public static Body createPlayer(World world, float x, float y) {
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
        fixtureDef.restitution = .75f;

        player.createFixture(fixtureDef);

        ballShape.dispose();

        return player;
    }

    public static Body createPlayer(World world) {
        return GameObjectFactory.createPlayer(world, 0, 0);
    }
    public static Body createEnemy(World world) {
        return GameObjectFactory.createEnemy(world, 0, 0);
    }

    public static Body createEnemy(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(0.75f);

        // fixture definition
        fixtureDef.shape = ballShape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 1;


        body.createFixture(fixtureDef);

        ballShape.dispose();

        return body;
    }

}
