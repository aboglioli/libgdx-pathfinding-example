package com.kiriost.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by kiriost on 14/04/16.
 */
public class ModifierKey extends InputAdapter {
    public static boolean L_SHIFT = false;
    public static boolean LEFT = false;
    public static boolean RIGHT = false;
    public static boolean UP = false;
    public static boolean DOWN = false;
    public static boolean L_CONTROL = false;
    public static boolean F12 = false;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SHIFT_LEFT:
                L_SHIFT = true;
                break;
            case Input.Keys.LEFT:
                LEFT = true;
                break;
            case Input.Keys.RIGHT:
                RIGHT = true;
                break;
            case Input.Keys.UP:
                UP = true;
                break;
            case Input.Keys.DOWN:
                DOWN = true;
                break;
            case Input.Keys.CONTROL_LEFT:
                L_CONTROL = true;
                break;
            case Input.Keys.F12:
                F12 = !F12;
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.SHIFT_LEFT:
                L_SHIFT = false;
                break;
            case Input.Keys.LEFT:
                LEFT = false;
                break;
            case Input.Keys.RIGHT:
                RIGHT = false;
                break;
            case Input.Keys.UP:
                UP = false;
                break;
            case Input.Keys.DOWN:
                DOWN = false;
                break;
            case Input.Keys.CONTROL_LEFT:
                L_CONTROL = false;
                break;
            default:
                return false;
        }
        return true;
    }
}
