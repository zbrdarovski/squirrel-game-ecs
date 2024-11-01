package com.mygdx.game.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.common.GameManager;
import com.mygdx.game.common.Mappers;
import com.mygdx.game.ecs.component.BlanketComponent;
import com.mygdx.game.ecs.component.SquirrelComponent;
import com.mygdx.game.ecs.component.HazelnutComponent;
import com.mygdx.game.ecs.component.BoundsComponent;
import com.mygdx.game.ecs.component.BasketComponent;
import com.mygdx.game.ecs.system.passive.SoundSystem;

public class CollisionSystem extends EntitySystem {

    private static final Family BASKET_FAMILY = Family.all(BasketComponent.class, BoundsComponent.class).get();
    private static final Family SQUIRREL_FAMILY = Family.all(SquirrelComponent.class, BoundsComponent.class).get();
    private static final Family HAZELNUT_FAMILY = Family.all(HazelnutComponent.class, BoundsComponent.class).get();
    private static final Family BLANKET_FAMILY = Family.all(BlanketComponent.class, BoundsComponent.class).get();

    private SoundSystem soundSystem;

    public CollisionSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        soundSystem = engine.getSystem(SoundSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;

        ImmutableArray<Entity> basket = getEngine().getEntitiesFor(BASKET_FAMILY);
        ImmutableArray<Entity> squirrel = getEngine().getEntitiesFor(SQUIRREL_FAMILY);
        ImmutableArray<Entity> hazelnut = getEngine().getEntitiesFor(HAZELNUT_FAMILY);
        ImmutableArray<Entity> blanket = getEngine().getEntitiesFor(BLANKET_FAMILY);

        for (Entity basketEntity : basket) {
            BoundsComponent basketBounds = Mappers.BOUNDS.get(basketEntity);

            for (Entity squirrelEntity : squirrel) {
                SquirrelComponent squirrelComponent = Mappers.SQUIRREL.get(squirrelEntity);

                if (squirrelComponent.hit) {
                    continue;
                }

                BoundsComponent squirrelBounds = Mappers.BOUNDS.get(squirrelEntity);

                if (Intersector.overlaps(basketBounds.rectangle, squirrelBounds.rectangle)) {
                    if(!GameManager.INSTANCE.getShield()) {
                        GameManager.INSTANCE.damage();
                        soundSystem.pick();
                    }
                }
            }

            for (Entity hazelnutEntity : hazelnut) {
                HazelnutComponent hazelnutComponent = Mappers.HAZELNUT.get(hazelnutEntity);

                if (hazelnutComponent.hit) {
                    continue;
                }

                BoundsComponent hazelnutBounds = Mappers.BOUNDS.get(hazelnutEntity);

                if (Intersector.overlaps(basketBounds.rectangle, hazelnutBounds.rectangle)) {
                    hazelnutComponent.hit = true;
                    GameManager.INSTANCE.incResult();
                    soundSystem.pick();
                    getEngine().removeEntity(hazelnutEntity);
                }
            }

            for (Entity blanketEntity : blanket) {
                BlanketComponent blanketComponent = Mappers.POWER.get(blanketEntity);

                if (blanketComponent.hit) {
                    continue;
                }

                BoundsComponent blanketBounds = Mappers.BOUNDS.get(blanketEntity);

                if (Intersector.overlaps(basketBounds.rectangle, blanketBounds.rectangle)) {
                    blanketComponent.hit = true;
                    GameManager.INSTANCE.setTimer(TimeUtils.millis());
                    GameManager.INSTANCE.setShield();
                    soundSystem.pick();
                    getEngine().removeEntity(blanketEntity);
                }
            }
        }
    }
}
