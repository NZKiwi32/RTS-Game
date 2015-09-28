package com.dev.flying.kiwi.gamecore.collisions;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.dev.flying.kiwi.gamecore.components.CollidedEnemyComponent;
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

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture dataA = contact.getFixtureA();
        Fixture dataB = contact.getFixtureB();

        if(validateFixture(dataA) && validateFixture(dataB) ) {
            Entity entityA = (Entity) dataA.getBody().getUserData();
            Entity entityB = (Entity) dataB.getBody().getUserData();

            boolean isAPlayer = isPlayer(entityA);
            boolean isBPlayer = isPlayer(entityB);

            if(isAPlayer || isBPlayer) { // Must has player in one of them else do nothing.

                // Add a CollidedEnemyComponent to the not-player entity
                if(!isAPlayer) {
                    entityA.add(new CollidedEnemyComponent());
                }
                if(!isBPlayer) {
                    entityB.add(new CollidedEnemyComponent());
                }
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    boolean validateFixture(Fixture f) {
        return f != null && f.getBody().getUserData() != null && f.getBody().getUserData() instanceof Entity;
    }

    /**
     * Returns if Entity is a player entity
     * @param a Entity A
     * @return boolean true if the entity is player, else false
     */
    private boolean isPlayer(Entity a) {
        return a.getComponent(PlayerComponent.class) != null;
    }
}
