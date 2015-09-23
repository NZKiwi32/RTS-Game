package com.dev.flying.kiwi.gamecore.factories;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Steven on 9/23/2015.
 */
public class PlayerFactory {
    public static Body createPlayer(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
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
        return PlayerFactory.createPlayer(world,0,0);
    }
}
