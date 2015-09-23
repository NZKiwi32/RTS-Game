package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * A Screen which contains the stage and all actors. This controls the main part of the game.
 * Created by Steven on 9/21/2015.
 */
public class PlayScreen implements Screen {
    private Color background;
    private boolean loaded; // Is the level finished loading

    private Stage stage;


    @Override
    public void show() {
        background = new Color(0.3f, 0.7f, 0.95f, 1f);

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Gdx.input.setInputProcessor(stage);

        createPlayer();

        loaded = true;
    }


    private void createPlayer() {

    }

    private void update(float delta) {
        if(loaded) {
            stage.act(delta);
        }
    }


    private void draw(float delta) {
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
