package com.mygdx.game.common;

import com.badlogic.ashley.core.ComponentMapper;

import com.mygdx.game.ecs.component.*;

public final class Mappers {

    public static final ComponentMapper<SquirrelComponent> SQUIRREL =
            ComponentMapper.getFor(SquirrelComponent.class);

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<BlanketComponent> POWER =
            ComponentMapper.getFor(BlanketComponent.class);

    public static final ComponentMapper<ParticleComponent> PARTICLE =
            ComponentMapper.getFor(ParticleComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<HazelnutComponent> HAZELNUT =
            ComponentMapper.getFor(HazelnutComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ZOrderComponent> Z_ORDER =
            ComponentMapper.getFor(ZOrderComponent.class);
}
