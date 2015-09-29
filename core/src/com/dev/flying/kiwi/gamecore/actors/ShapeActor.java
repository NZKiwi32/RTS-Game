package com.dev.flying.kiwi.gamecore.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * ShapeActor
 * The core player actor class
 * Created by Steven on 9/21/2015.
 */
public class ShapeActor extends Actor {
    private TextureRegion textureRegion;

    public ShapeActor(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(this.textureRegion, getX() - getWidth()/2, getY() - getHeight()/2, getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
