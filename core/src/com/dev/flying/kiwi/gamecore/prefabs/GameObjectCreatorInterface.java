package com.dev.flying.kiwi.gamecore.prefabs;

import com.badlogic.ashley.core.Entity;

/**
 * GameObjectCreatorInterface
 *
 * Interface for standardising how to create game objects.
 * Advisable to extend {@link GameObjectCreator} rather than using interface directly.
 *
 * Created by Steven on 9/28/2015.
 */
public interface GameObjectCreatorInterface {
    /**
     * Creates a game object at the given co-ordinates.
     * Override to do your specific implementation, default does nothing.
     * @param x x co-ordinate
     * @param y y co-ordinate
     * @return the entity after creation
     */
    Entity create(float x, float y);
}
