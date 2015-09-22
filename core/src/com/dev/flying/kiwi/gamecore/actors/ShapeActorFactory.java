package com.dev.flying.kiwi.gamecore.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Steven on 9/22/2015.
 */
public class ShapeActorFactory {
    public enum Shapes {
        CIRCLE ("hex/player_circle.png"),
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

    public static Actor generateSpecificShape(Shapes s) {
        Actor shape = new ShapeActor(new TextureRegion(new Texture(Gdx.files.internal(s.value))));
        shape.setRotation(0);
        shape.setWidth(64);
        shape.setHeight(64);
        shape.setBounds(0, 0, shape.getWidth(), shape.getHeight());
        shape.setOrigin(shape.getWidth() / 2, shape.getHeight() / 2);
        return shape;
    }

    public static Actor generateShape() {
       return generateSpecificShape(Shapes.randomShape());
    }
}
