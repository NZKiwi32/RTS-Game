package com.dev.flying.kiwi.rtsgame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dev.flying.kiwi.rtsgame.components.PlayerControlledComponent;
import com.dev.flying.kiwi.rtsgame.components.SpeedComponent;
import com.dev.flying.kiwi.rtsgame.components.VelocityComponent;
import com.dev.flying.kiwi.rtsgame.core.KeyboardInput;

/**
 * A System that iterates PlayerControlledComponent entities and applys the action for a input.
 * Created by Steven on 9/10/2015.
 */
public class InputSystem extends IteratingSystem {
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<SpeedComponent> sm = ComponentMapper.getFor(SpeedComponent.class);
    private KeyboardInput keyboardInput;

    public InputSystem(KeyboardInput keyboardInput) {
        super(Family.all(PlayerControlledComponent.class, VelocityComponent.class, SpeedComponent.class).get());
        this.keyboardInput = keyboardInput;
        Gdx.input.setInputProcessor(keyboardInput);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(keyboardInput.isActivated(Input.Keys.LEFT)) vm.get(entity).x = -1*sm.get(entity).speed * Gdx.graphics.getDeltaTime();
        if(keyboardInput.isActivated(Input.Keys.RIGHT)) vm.get(entity).x = sm.get(entity).speed * Gdx.graphics.getDeltaTime();

        if(keyboardInput.isActivated(Input.Keys.UP)) vm.get(entity).y = sm.get(entity).speed * Gdx.graphics.getDeltaTime();
        if(keyboardInput.isActivated(Input.Keys.DOWN)) vm.get(entity).y = -1*sm.get(entity).speed * Gdx.graphics.getDeltaTime();

        // Set zero velocity when no action
        if(!keyboardInput.isActivated(Input.Keys.LEFT) && !keyboardInput.isActivated(Input.Keys.RIGHT)) vm.get(entity).x = 0;
        if(!keyboardInput.isActivated(Input.Keys.DOWN) && !keyboardInput.isActivated(Input.Keys.UP)) vm.get(entity).y = 0;
    }
}
