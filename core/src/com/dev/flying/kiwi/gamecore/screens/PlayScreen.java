package com.dev.flying.kiwi.gamecore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dev.flying.kiwi.gamecore.actors.InvisibleActor;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory;
import com.dev.flying.kiwi.gamecore.actors.ShapeActorFactory.Shapes;

/** A Screen which contains the stage and all actors. This controls the main part of the game.
 * Created by Steven on 9/21/2015.
 */
public class PlayScreen implements Screen {
    private Color background;
    private boolean loaded; // Is the level finished loading

    private Stage stage;
    private Group player;
    private ObjectSet<Actor> shapes;

    // Level components // TODO abstract later
    private Array<ShapeActorFactory.Shapes> levelShapes;
    private Array<InvisibleActor> spawners;
    private int maxShapes; // Maximum number of shapes on the screen, if exceeded don't spawn more.

    @Override
    public void show() {
        background = new Color(0.3f, 0.7f, 0.95f, 1f);

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Gdx.input.setInputProcessor(stage);

        createPlayer();
        createSpawners();
        createLevel();
        shapes = new ObjectSet<>();

        loaded = true;
    }

    private void createLevel() {
        levelShapes = new Array<>();
        levelShapes.add(Shapes.CIRCLE);
        levelShapes.add(Shapes.HEX);
        levelShapes.add(Shapes.PENTAGON);
        maxShapes = 1; // 1 for now, as 2+ can have shapes on top of each other
    }

    private void createSpawners() {
        spawners = new Array<>();
        spawners.add(new InvisibleActor(-50, -50));
        spawners.add(new InvisibleActor(300, -50));
        spawners.add(new InvisibleActor(400, 100));
        spawners.add(new InvisibleActor(900, 800));
        spawners.add(new InvisibleActor(400, 100));
        for (InvisibleActor spawner : spawners) {
            stage.addActor(spawner);
        }
    }

    private void createPlayer() {
        player = new Group();

        Actor initialShape = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX);
        initialShape.setPosition(0, 0);
        initialShape.setOrigin(initialShape.getWidth() / 2, initialShape.getHeight() / 2);
        initialShape.setName("shape1");
//
//        Actor initialShape2 = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX);
//        initialShape2.setPosition(-100, 0);
//        initialShape2.setOrigin(initialShape2.getWidth() / 2, initialShape2.getHeight() / 2);
//        initialShape2.setName("shape2");
//
//        Actor initialShape3 = ShapeActorFactory.generateSpecificShape(ShapeActorFactory.Shapes.HEX);
//        initialShape3.setPosition(100, 0);
//        initialShape3.setOrigin(initialShape3.getWidth() / 2, initialShape3.getHeight() / 2);
//        initialShape3.setName("shape3");

        player.addActor(initialShape);
//        player.addActor(initialShape2);
//        player.addActor(initialShape3);

        player.setPosition(Gdx.graphics.getWidth() / 2 - initialShape.getWidth() / 2, Gdx.graphics.getHeight() / 2 - initialShape.getHeight() / 2);
        player.setOrigin(initialShape.getWidth() / 2, initialShape.getHeight() / 2);


        // TODO bind to whole screen
        player.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO improve spin input controls
                player.rotateBy(15f);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        stage.addActor(player);
    }

    private void update(float delta) {
        if(loaded) {
            spawnShapes();
            stage.act(delta);
        }
    }

    private void spawnShapes() {
        if(shapes.size >= maxShapes) return; // don't spawn if we are at the maximum.

        // Select a spawner at random
        InvisibleActor spawner = spawners.get(MathUtils.random(spawners.size-1));
        Actor shape = ShapeActorFactory.generateShape();
        shape.setPosition(spawner.getX()- shape.getWidth() / 2, spawner.getY() - shape.getHeight()/2);
        shape.setOrigin(shape.getWidth() / 2, shape.getHeight() / 2);
        shapes.add(shape);
        stage.addActor(shape);

        shape.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 3f));
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
