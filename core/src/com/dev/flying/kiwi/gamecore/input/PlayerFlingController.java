package com.dev.flying.kiwi.gamecore.input;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerFlingController extends GestureDetector.GestureAdapter {
    private Body body;

    /**
     * @param body the body for the gesture to apply to.
     */
    public PlayerFlingController(Body body) {
        this.body = body;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        int direction = velocityY > 0 ? 1 : -1;
        float angle = MathUtils.degreesToRadians*direction*72f;
        body.setTransform(body.getPosition(), body.getAngle()+angle);
        return false;
    }
}