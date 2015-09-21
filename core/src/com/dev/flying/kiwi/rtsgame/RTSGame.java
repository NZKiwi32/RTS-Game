package com.dev.flying.kiwi.rtsgame;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dev.flying.kiwi.rtsgame.core.KeyboardInput;
import com.dev.flying.kiwi.rtsgame.core.World;
import com.dev.flying.kiwi.rtsgame.systems.ImageRenderSystem;
import com.dev.flying.kiwi.rtsgame.systems.InputSystem;
import com.dev.flying.kiwi.rtsgame.systems.MovementSystem;
import com.dev.flying.kiwi.rtsgame.systems.SpawnSystem;

public class RTSGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture background;

	private PooledEngine engine;
	private World world;
	private KeyboardInput keyboardInput;

	@Override
	public void create () {
		// Gdx initialisation
		batch = new SpriteBatch();
		keyboardInput = new KeyboardInput();

		background = new Texture(Gdx.files.internal("GrassSeamless.jpg"));
        background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		// Ashley initialisation
		this.engine = new PooledEngine();

		this.engine.addSystem(new ImageRenderSystem(batch));
		this.engine.addSystem(new MovementSystem());
		this.engine.addSystem(new InputSystem(keyboardInput));
        this.engine.addSystem(new SpawnSystem(2));
		this.world = new World(this.engine);
		this.world.create();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.25f,0.75f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // batch.draw(background, 0, 0, 1024, 768);
		engine.update(Gdx.graphics.getDeltaTime());

        batch.end();
	}
}
