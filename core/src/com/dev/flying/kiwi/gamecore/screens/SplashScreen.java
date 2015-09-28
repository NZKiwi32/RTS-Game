package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * SplashScreen
 *
 * The introduction splash screen advertising game and company name.
 * Automatically changes to new screen after an interval of time
 *
 * Created by Steven on 9/21/2015.
 */
public class SplashScreen implements Screen {
    private Texture texture;
    private SpriteBatch batch;
    private Sprite splash;
    private Screen nextScreen;
    private float delay;

    public SplashScreen (Texture texture) {
        this.texture = texture;
    }

    public void setScreenToDelay(Screen nextScreen, float delay) {
        this.nextScreen = nextScreen;
        this.delay = delay;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        splash = new Sprite(this.texture);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(nextScreen);
            }
        }, delay);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        splash.getTexture().dispose();
    }
}
