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
import com.dev.flying.kiwi.gamecore.components.PlayerComponent;
import com.dev.flying.kiwi.gamecore.factories.GameObjectFactory;

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
        Actor playerActor = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX);
        playerActor.setName("Player");
        Body body = GameObjectFactory.createPlayer(world);
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
}
