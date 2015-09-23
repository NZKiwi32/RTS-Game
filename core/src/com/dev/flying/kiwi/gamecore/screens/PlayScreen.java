package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dev.flying.kiwi.gamecore.factories.PlayerFactory;

/**
 * A Screen which contains the stage and all actors. This controls the main part of the game.
 * Created by Steven on 9/21/2015.
 */
public class PlayScreen implements Screen {
    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;

    private Color background;

    private OrthographicCamera camera;
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    @Override
    public void show() {
        background = new Color(0.3f, 0.2f, 0.25f, 1f);

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 25, Gdx.graphics.getHeight() / 25);

        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();
        PlayerFactory.createPlayer(world);
    }



    private void update(float delta) {
    }


    private void draw(float delta) {
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 25;
        camera.viewportHeight = height / 25;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        stage.dispose();
    }
}
