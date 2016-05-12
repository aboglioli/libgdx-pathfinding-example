package com.kiriost.game.gameobject;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by kiriost on 17/04/16.
 */
public class Status {
    private ObjectMap<String, Boolean> status;

    public Status() {
        status = new ObjectMap<String, Boolean>();
    }

    public boolean get(String name) {
        if (!status.containsKey(name)) {
            return false;
        }
        return status.get(name).booleanValue();
    }

    public void set(String name, boolean status) {
        this.status.put(name, status);
    }
}
