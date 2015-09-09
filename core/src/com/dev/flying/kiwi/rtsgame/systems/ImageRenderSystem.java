package com.dev.flying.kiwi.rtsgame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.dev.flying.kiwi.rtsgame.components.ImageDrawableComponent;
import com.dev.flying.kiwi.rtsgame.components.PositionComponent;

import java.awt.*;

/**
 * Render System
 * System which draws DrawableComponents to the android view.
 * Created by Steven on 7/30/2015.
 */
public class ImageRenderSystem extends IteratingSystem {
    ComponentMapper<ImageDrawableComponent> drawableMapper;
    ComponentMapper<PositionComponent> positionMapper;
    private Array<Entity> renderQueue;

    private Batch batch;

    public ImageRenderSystem(Batch batch) {
        super(Family.all(ImageDrawableComponent.class, PositionComponent.class).get());

        this.renderQueue = new Array<>();
        this.drawableMapper = ComponentMapper.getFor(ImageDrawableComponent.class);
        this.positionMapper = ComponentMapper.getFor(PositionComponent.class);

        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    protected void clearCanvas() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void drawRenderQueue() {
        batch.begin();
        for (Entity e : this.renderQueue) {
            batch.draw(drawableMapper.get(e).texture, positionMapper.get(e).x, positionMapper.get(e).y);
        }
        batch.end();

        renderQueue.clear();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.clearCanvas();
        this.drawRenderQueue();
    }

}
