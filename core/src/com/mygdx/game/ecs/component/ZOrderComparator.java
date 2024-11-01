package com.mygdx.game.ecs.component;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.common.Mappers;

import java.util.Comparator;

import com.mygdx.game.common.Mappers;

public class ZOrderComparator implements Comparator<Entity> {

    public static final ZOrderComparator INSTANCE = new ZOrderComparator();

    private ZOrderComparator() {
    }

    @Override
    public int compare(Entity entity1, Entity entity2) {
        return Float.compare(
                Mappers.Z_ORDER.get(entity1).z,
                Mappers.Z_ORDER.get(entity2).z
        );
    }
}
