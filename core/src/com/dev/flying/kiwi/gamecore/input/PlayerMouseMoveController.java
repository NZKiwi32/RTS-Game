package com.dev.flying.kiwi.gamecore.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;

/**
 * PlayerMouseMoveController
 *
 * Handles the rotation of the player based off Mouse/Touch drag location
 *
 * Created by Steven on 9/28/2015.
 */
public class PlayerMouseMoveController extends InputAdapter {
    private int mouseX;
    private int mouseY;

    private Body playerBody;
    private Entity player;

    public PlayerMouseMoveController(Entity player) {
        this.player = player;
        this.playerBody = player.getComponent(Box2DBodyComponent.class).body;
    }

    protected void processUpdate() {
        // Inverted mouse and player position as well as X and Y axis
        Vector2 mouse = new Vector2(mouseY,mouseX);
        playerBody.setTransform(playerBody.getPosition(), mouse.angleRad()*5);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseX = screenX;
        mouseY = screenY;
        processUpdate();
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseX = screenX;
        mouseY = screenY;
        processUpdate();
        return false;
    }
}
