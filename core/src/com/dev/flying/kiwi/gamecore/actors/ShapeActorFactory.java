package com.dev.flying.kiwi.gamecore.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * ShapeActorFactory
 * This class is a factory for creating random shaped Actors.
 * The returned item will be a Actor without position, the callee is responsible for placing the actor.
 * Created by Steven on 9/22/2015.
 */
public class ShapeActorFactory {
    public enum Shapes {
        CIRCLE ("hex/glow-circle.png"),
        RECT ("hex/player_rect.png"),
        HEX ("hex/player_hex.png"),
        PENTAGON ("hex/player_pent.png");

        private String value;

        Shapes(String texture) {
            this.value = texture;
        }

        private static final List<Shapes> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Shapes randomShape()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    /**
     * Generates a specific actor given a shape type.
     * @param s The shape in which to use a the texture
     * @return The Actor
     */
    public static ShapeActor generateSpecificShape(Shapes s, Vector2 size) {
        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal(s.value)));
        ShapeActor shape = new ShapeActor(tr);
        shape.setRotation(0);
        shape.setWidth(size.x);
        shape.setHeight(size.y);
        shape.setColor(Color.WHITE);
        shape.setBounds(0, 0, shape.getWidth(), shape.getHeight());
        shape.setOrigin(shape.getWidth() / 2, shape.getHeight() / 2);
        return shape;
    }

    /**
     * A random shaped actor
     * @return an Actor
     */
    public static ShapeActor generateShape(Vector2 size) {
       return generateSpecificShape(Shapes.randomShape(), size);
    }
}
