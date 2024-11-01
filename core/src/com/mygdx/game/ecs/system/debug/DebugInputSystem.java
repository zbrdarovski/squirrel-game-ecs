package com.mygdx.game.ecs.system.debug;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by goran on 11/02/2017.
 */

public class DebugInputSystem extends EntitySystem {

    private boolean debugGrid = false;
    private boolean debugRender = false;
    private EntitySystem gridRenderSystem;
    private EntitySystem debugRenderSystem;

    // == constructors ==
    public DebugInputSystem() {
    }

    // == public methods ==
    @Override
    public void addedToEngine(Engine engine) {
        gridRenderSystem = engine.getSystem(GridRenderSystem.class);
        debugRenderSystem = engine.getSystem(DebugRenderSystem.class);
        toggleSystems();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            debugGrid = !debugGrid;
            toggleSystems();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            debugRender = !debugRender;
            toggleSystems();
        }
    }

    // == private methods ==
    private void toggleSystems() {
        gridRenderSystem.setProcessing(debugGrid);
        debugRenderSystem.setProcessing(debugRender);
    }
}
