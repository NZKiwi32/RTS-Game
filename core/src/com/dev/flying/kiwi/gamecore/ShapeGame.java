package com.dev.flying.kiwi.gamecore;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dev.flying.kiwi.gamecore.components.SpawnerComponent;
import com.dev.flying.kiwi.gamecore.screens.SplashScreen;
import com.dev.flying.kiwi.gamecore.screens.PlayScreen;

public class ShapeGame extends Game {
	public static final String TITLE = "ShapeGame", VERSION = "0.0.0.0";

	@Override
	public void create () {
		SplashScreen splashScreen = new SplashScreen(new Texture(Gdx.files.internal("GrassSeamless.jpg")));
		splashScreen.setScreenToDelay(new PlayScreen(), 0.3f);
		setScreen(splashScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
