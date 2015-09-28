package com.dev.flying.kiwi.gamecore.collisions;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.dev.flying.kiwi.gamecore.components.CollidedEnemyComponent;
import com.dev.flying.kiwi.gamecore.components.EnemyComponent;
import com.dev.flying.kiwi.gamecore.components.PlayerComponent;

/**
 * ContactController
 * This class handles collisions within the Box2D World
 *
 * Created by Steven on 9/28/2015.
 */
public class ContactController implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture dataA = contact.getFixtureA();
        Fixture dataB = contact.getFixtureB();

        if(validateFixture(dataA) && validateFixture(dataB) ) {
            processFixture(dataA);
            processFixture(dataB);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    boolean validateFixture(Fixture f) {
        return f != null && f.getBody().getUserData() != null && f.getBody().getUserData() instanceof Entity;
    }

    void processFixture(Fixture data) {
        Entity entity = (Entity) data.getBody().getUserData();

        if(entity != null) {
            if (entity.getComponent(PlayerComponent.class) != null) {
                // entity is the player, so do nothing

            } else if( entity.getComponent(EnemyComponent.class) != null) {
                entity.add(new CollidedEnemyComponent());

            }
        }
    }
}
