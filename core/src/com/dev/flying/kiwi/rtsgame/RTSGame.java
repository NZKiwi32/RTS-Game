package com.dev.flying.kiwi.rtsgame;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dev.flying.kiwi.rtsgame.core.World;
import com.dev.flying.kiwi.rtsgame.systems.ImageRenderSystem;
import com.dev.flying.kiwi.rtsgame.systems.MovementSystem;

public class RTSGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private PooledEngine engine;
	private World world;

	@Override
	public void create () {
		// Gdx initialisation
		batch = new SpriteBatch();

		// Ashley initialisation
		this.engine = new PooledEngine();

		this.engine.addSystem(new ImageRenderSystem(batch));
		this.engine.addSystem(new MovementSystem());
		this.world = new World(this.engine);
		this.world.create();
	}

	@Override
	public void render() {
		engine.update(Gdx.graphics.getDeltaTime());
	}
}
