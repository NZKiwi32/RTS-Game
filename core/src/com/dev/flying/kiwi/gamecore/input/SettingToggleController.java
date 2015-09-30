package com.dev.flying.kiwi.gamecore.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.dev.flying.kiwi.gamecore.Settings;

/**
 * SettingToggleController
 *
 * Handles the keyboard control of global settings
 *
 * Created by Steven on 9/28/2015.
 */
public class SettingToggleController extends InputAdapter {
    private Settings settings;

    public SettingToggleController(Settings settings) {
        this.settings = settings;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.F7) {
            settings.debugRender = !settings.debugRender;
        }
        return true;
    }
}
