package com.mygdx.game.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DimensionComponent implements Component, Pool.Poolable {
    public float width = 1;
    public float height = 1;

    @Override
    public void reset() {
        width = 1;
        height = 1;
    }
}
