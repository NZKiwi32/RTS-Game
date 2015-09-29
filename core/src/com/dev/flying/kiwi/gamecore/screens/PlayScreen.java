package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dev.flying.kiwi.gamecore.ShapeGame;
import com.dev.flying.kiwi.gamecore.collisions.ContactController;
import com.dev.flying.kiwi.gamecore.components.Box2DBodyComponent;
import com.dev.flying.kiwi.gamecore.input.PlayerMouseMoveController;
import com.dev.flying.kiwi.gamecore.prefabs.EnemyCreator;
import com.dev.flying.kiwi.gamecore.prefabs.PlayerCreator;
import com.dev.flying.kiwi.gamecore.prefabs.SpawnerCreator;
import com.dev.flying.kiwi.gamecore.systems.BodyActorUpdateSystem;
import com.dev.flying.kiwi.gamecore.systems.EnemyCleanupSystem;
import com.dev.flying.kiwi.gamecore.systems.EnemyMovementSystem;
import com.dev.flying.kiwi.gamecore.systems.SpawnSystem;

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
    private BodyActorUpdateSystem bodyActorUpdateSystem;
    private EnemyCleanupSystem enemyCleanupSystem;

    @Override
    public void show() {
        engine = new PooledEngine();

        /** TODO Stage should own camera and  batch call {@link Stage#getCamera()} {@link Stage#getBatch()}  */
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 25, Gdx.graphics.getHeight() / 25);

        batch = new SpriteBatch();
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();

        player = new PlayerCreator(engine,world,stage).create(0,0);

        EnemyCreator enemyCreator = new EnemyCreator(engine,world,stage);
        SpawnerCreator spawnerCreator = new SpawnerCreator(engine,world,stage);
        spawnerCreator.create(15,15);
        spawnerCreator.create(20,20);
        spawnerCreator.create(-14,-20);
        spawnerCreator.create(24,-28);

        userInput();
        ashleySystems();

        SpawnSystem spawnSystem = new SpawnSystem(1.5f, enemyCreator);
        engine.addSystem(spawnSystem);
        world.setContactListener(new ContactController());
    }

    private void userInput() {
        InputMultiplexer im = new InputMultiplexer(new PlayerMouseMoveController(player), stage);
        Gdx.input.setInputProcessor(im);
    }

    private void ashleySystems() {
        // Renders sprites to the Box2D Body/Fixture locations
        bodyActorUpdateSystem = new BodyActorUpdateSystem();
        engine.addSystem(bodyActorUpdateSystem);

        // Updates the location of enemies
        engine.addSystem(new EnemyMovementSystem(Vector2.Zero));

        // Switches enemies to be a part of the player on collision
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
        batch.setProjectionMatrix(camera.combined);

        // TODO Use stage to call {@link stage#draw()}
        batch.begin();
        bodyActorUpdateSystem.drawRenderQueue(batch);
        batch.end();

        debugRenderer.render(world, camera.combined);
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
