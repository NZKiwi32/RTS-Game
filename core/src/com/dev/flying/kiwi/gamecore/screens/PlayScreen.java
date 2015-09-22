package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory;

/** A Screen which contains the stage and all actors. This controls the main part of the game.
 * Created by Steven on 9/21/2015.
 */
public class PlayScreen implements Screen {
    private Color background;

    private Stage stage;
    private Actor player;
    private Array<Actor> incoming;

    @Override
    public void show() {
        background = new Color(0.3f, 0.7f, 0.95f, 1f);

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Gdx.input.setInputProcessor(stage);

        createPlayer();
    }

    private void createPlayer() {
        player = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX);
        player.setPosition(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2, Gdx.graphics.getHeight() / 2 - player.getHeight() / 2);

        // TODO bind to whole screen
        player.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO improve spin input controls
                player.rotateBy(30f);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        stage.addActor(player);
    }

    private void update(float delta) {
        stage.act(delta);
        // player.rotateBy(3f);
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
