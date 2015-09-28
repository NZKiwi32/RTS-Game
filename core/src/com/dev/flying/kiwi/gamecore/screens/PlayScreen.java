package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dev.flying.kiwi.gamecore.ShapeGame;
import com.dev.flying.kiwi.gamecore.collisions.ContactController;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;
import com.dev.flying.kiwi.gamecore.input.PlayerFlingController;
import com.dev.flying.kiwi.gamecore.prefabs.EnemyCreator;
import com.dev.flying.kiwi.gamecore.prefabs.PlayerCreator;
import com.dev.flying.kiwi.gamecore.prefabs.SpawnerCreator;
import com.dev.flying.kiwi.gamecore.systems.EnemyCleanupSystem;
import com.dev.flying.kiwi.gamecore.systems.EnemyMovementSystem;
import com.dev.flying.kiwi.gamecore.systems.PhysicsActorRenderSystem;

/**
 * PlayScreen
 * A Screen which contains:
 *  - Stage, Actors
 *  - Entities, Systems
 *  - Input from User
 *
 * Created by Steven on 9/21/2015.
 */
public class PlayScreen implements Screen {
    private static float TIMESTEP = 1 / 60f;
    private static int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;

    private OrthographicCamera camera;
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;

    private Entity player;
    private PooledEngine engine;
    private PhysicsActorRenderSystem physicsActorRenderSystem;
    private EnemyCleanupSystem enemyCleanupSystem;

    @Override
    public void show() {
        engine = new PooledEngine();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 25, Gdx.graphics.getHeight() / 25);

        batch = new SpriteBatch();
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();

        player = new PlayerCreator(engine,world,stage).create(0,0);

        EnemyCreator enemyCreator = new EnemyCreator(engine,world,stage);
        enemyCreator.create(-9, 9);
        enemyCreator.create(3, 9);
        enemyCreator.create(-9, -12);
        enemyCreator.create(11, 39);

        SpawnerCreator spawnerCreator = new SpawnerCreator(engine,world,stage);
        spawnerCreator.create(9,10);

        userInput();
        ashleySystems();
        world.setContactListener(new ContactController());
    }

    private void userInput() {
        GestureDetector gd = new GestureDetector(new PlayerFlingController(player.getComponent(Box2DBodyComponent.class).body));
        InputMultiplexer im = new InputMultiplexer(gd, stage);
        Gdx.input.setInputProcessor(im);
    }

    private void ashleySystems() {
        physicsActorRenderSystem = new PhysicsActorRenderSystem(batch);
        engine.addSystem(physicsActorRenderSystem);
        engine.addSystem(new EnemyMovementSystem(Vector2.Zero));
        enemyCleanupSystem = new EnemyCleanupSystem(world, engine, player.getComponent(Box2DBodyComponent.class).body);
        engine.addSystem(enemyCleanupSystem);
    }

    private void clear() {
        Gdx.gl.glClearColor(ShapeGame.BACKGROUND.r, ShapeGame.BACKGROUND.g, ShapeGame.BACKGROUND.b, ShapeGame.BACKGROUND.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void update(float delta) {
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

        engine.update(delta);

    }

    private void draw(float delta) {
        debugRenderer.render(world, camera.combined);
        batch.begin();
        physicsActorRenderSystem.drawRenderQueue();
        batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        clear();
        draw(delta);
        enemyCleanupSystem.cleanUp();
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
