package com.mygdx.game.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.Mappers;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.ecs.component.ParticleComponent;
import com.mygdx.game.ecs.component.PositionComponent;
import com.mygdx.game.ecs.component.TextureComponent;
import com.mygdx.game.ecs.component.ZOrderComparator;

public class ParticleSystem extends SortedIteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            ParticleComponent.class,
            TextureComponent.class
    ).get();

    private final SpriteBatch batch;
    private final Viewport viewport;

    public ParticleSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY, ZOrderComparator.INSTANCE);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        super.update(deltaTime);

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        ParticleComponent particle = Mappers.PARTICLE.get(entity);

        particle.particleEffect.setPosition(position.x-50f + GameConfig.BASKET_WIDTH, position.y+40f);
        particle.particleEffect.getEmitters().first().getAngle().setHigh(0);
        particle.particleEffect.getEmitters().first().getAngle().setLow(0);
        particle.particleEffect.draw(batch, deltaTime);
        if (particle.particleEffect.isComplete()) {
            particle.particleEffect.reset();
        }
    }
}
