package com.dev.flying.kiwi.gamecore.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.xml.soap.Text;

/** The core player actor class. States how
 * Created by Steven on 9/21/2015.
 */
public class PlayerActor extends Actor {
    private TextureRegion textureRegion;

    public PlayerActor(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(this.textureRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
