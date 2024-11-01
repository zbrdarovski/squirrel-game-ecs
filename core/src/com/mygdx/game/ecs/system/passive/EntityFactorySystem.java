package com.mygdx.game.ecs.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;

import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.assets.RegionNames;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.ecs.component.ParticleComponent;
import com.mygdx.game.ecs.component.BlanketComponent;
import com.mygdx.game.ecs.component.SquirrelComponent;
import com.mygdx.game.ecs.component.HazelnutComponent;
import com.mygdx.game.ecs.component.BoundsComponent;
import com.mygdx.game.ecs.component.CleanUpComponent;
import com.mygdx.game.ecs.component.DimensionComponent;
import com.mygdx.game.ecs.component.MovementComponent;
import com.mygdx.game.ecs.component.PositionComponent;
import com.mygdx.game.ecs.component.BasketComponent;
import com.mygdx.game.ecs.component.TextureComponent;
import com.mygdx.game.ecs.component.WorldWrapComponent;
import com.mygdx.game.ecs.component.ZOrderComponent;

public class EntityFactorySystem extends EntitySystem {

    private static final int BACKGROUND_Z_ORDER=1;
    private static final int SQUIRREL_Z_ORDER = 2;
    private static final int HAZELNUT_Z_ORDER = 3;
    private static final int BASKET_Z_ORDER = 4;
    private static final int BLANKET_Z_ORDER = 5;

    private final AssetManager assetManager;

    private PooledEngine engine;
    private TextureAtlas gamePlayAtlas;

    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        setProcessing(false);
        init();
    }

    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createBackground(){
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = 0;
        position.y = 0;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WIDTH;
        dimension.height = GameConfig.HEIGHT;

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BACKGROUND_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(movement);
        entity.add(texture);
        entity.add(zOrder);
        engine.addEntity(entity);
    }

    public void createBasket() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = GameConfig.WIDTH / 2f - GameConfig.BASKET_WIDTH / 2f;
        position.y = 20;

        ParticleComponent particle = engine.createComponent(ParticleComponent.class);
        particle.particleEffect.load(Gdx.files.internal("desktop/assets-raw/gameplay/basket.pe"), Gdx.files.internal("desktop/assets-raw/gameplay/"));

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.BASKET_WIDTH;
        dimension.height = GameConfig.BASKET_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        BasketComponent basketComponent = engine.createComponent(BasketComponent.class);

        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BASKET);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BASKET_Z_ORDER;


        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(particle);
        entity.add(basketComponent);
        entity.add(worldWrap);
        entity.add(texture);
        entity.add(zOrder);

        engine.addEntity(entity);
    }

    public void createSquirrel() {
        PositionComponent position = engine.createComponent(PositionComponent.class);

        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.SQUIRREL_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.ySpeed = -GameConfig.SQUIRREL_SPEED_X_MIN * MathUtils.random(1f, 2f);
        movementComponent.rSpeed = MathUtils.random(-1f, 1f);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SQUIRREL_WIDTH;
        dimension.height = GameConfig.SQUIRREL_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        SquirrelComponent squirrelComponent = engine.createComponent(SquirrelComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.SQUIRREL);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = SQUIRREL_Z_ORDER;

         WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(squirrelComponent);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(worldWrap);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createHazelnut() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.HAZELNUT_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.ySpeed = -GameConfig.HAZELNUT_SPAWN_TIME * MathUtils.random(1f, 2f);
        movementComponent.rSpeed = MathUtils.random(-1f, 1f);

        HazelnutComponent hazelnutComponent = engine.createComponent(HazelnutComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.HAZELNUT_WIDTH;
        dimension.height = GameConfig.HAZELNUT_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.HAZELNUT);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = HAZELNUT_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(hazelnutComponent);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createPowerUp(){
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.BLANKET_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.ySpeed = -GameConfig.BLANKET_SPAWN_TIME * MathUtils.random(1f, 2f);

        BlanketComponent powerComponent = engine.createComponent(BlanketComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.BLANKET_WIDTH;
        dimension.height = GameConfig.BLANKET_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BLANKET);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BLANKET_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(powerComponent);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }
}


