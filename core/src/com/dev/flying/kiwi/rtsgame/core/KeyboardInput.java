package com.dev.flying.kiwi.rtsgame.core;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.IntSet;

/**
 * Created by Steven on 9/12/2015.
 */
public class KeyboardInput extends InputAdapter {
    private IntSet activeKeys;

    public KeyboardInput() {
        activeKeys = new IntSet();

    }

    public boolean keyDown (int keycode) {
        activeKeys.add(keycode);
        return true;
    }

    public boolean keyUp (int keycode) {
        activeKeys.remove(keycode);
        return true;
    }

    public void clear() {
        activeKeys.clear();
    }

    public boolean isActivated(int key) {
        return this.activeKeys.contains(key);
    }
}
