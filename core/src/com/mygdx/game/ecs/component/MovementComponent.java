package com.mygdx.game.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MovementComponent implements Component, Pool.Poolable {
    public float xSpeed;
    public float ySpeed;
    public float rSpeed;

    @Override
    public void reset() {
        xSpeed = 0;
        ySpeed = 0;
        rSpeed = 0;
    }
}
