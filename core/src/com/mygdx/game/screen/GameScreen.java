package com.mygdx.game.screen;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StopTheSquirrels;
import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.common.GameManager;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.ecs.system.BoundsSystem;
import com.mygdx.game.ecs.system.CleanUpSystem;
import com.mygdx.game.ecs.system.CollisionSystem;
import com.mygdx.game.ecs.system.HudRenderSystem;
import com.mygdx.game.ecs.system.MovementSystem;
import com.mygdx.game.ecs.system.BlanketSpawnSystem;
import com.mygdx.game.ecs.system.RenderSystem;
import com.mygdx.game.ecs.system.BasketInputSystem;
import com.mygdx.game.ecs.system.HazelnutSpawnSystem;
import com.mygdx.game.ecs.system.SquirrelSpawnSystem;
import com.mygdx.game.ecs.system.WorldWrapSystem;
import com.mygdx.game.ecs.system.debug.DebugCameraSystem;
import com.mygdx.game.ecs.system.debug.DebugInputSystem;
import com.mygdx.game.ecs.system.debug.DebugRenderSystem;
import com.mygdx.game.ecs.system.debug.GridRenderSystem;
import com.mygdx.game.ecs.system.passive.EntityFactorySystem;
import com.mygdx.game.ecs.system.ParticleSystem;
import com.mygdx.game.ecs.system.passive.SoundSystem;
import com.mygdx.game.ecs.system.passive.StartUpSystem;

public class GameScreen extends ScreenAdapter {

    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), Logger.DEBUG);

    private final AssetManager assetManager;
    private final SpriteBatch batch;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private final boolean debug = true;

    public GameScreen(StopTheSquirrels game) {
        assetManager = game.getAssetManager();
        batch = game.getBatch();
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WIDTH, GameConfig.HEIGHT, camera);
        hudViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer = new ShapeRenderer();
        BitmapFont font = assetManager.get(AssetDescriptors.FONT_64);

        engine = new PooledEngine();

        engine.addSystem(new EntityFactorySystem(assetManager));
        engine.addSystem(new SoundSystem(assetManager));
        engine.addSystem(new StartUpSystem());

        engine.addSystem(new BasketInputSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new SquirrelSpawnSystem());
        engine.addSystem(new HazelnutSpawnSystem());
        engine.addSystem(new BlanketSpawnSystem());
        engine.addSystem(new CleanUpSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new ParticleSystem(batch, viewport));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, font));

        if (debug) {
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugRenderSystem(viewport, renderer));
            engine.addSystem(new DebugCameraSystem(
                    GameConfig.WIDTH / 2, GameConfig.HEIGHT / 2,
                    camera
            ));
        }
        engine.addSystem(new DebugInputSystem());

        GameManager.INSTANCE.resetResult();
        logAllSystemsInEngine();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) GameManager.INSTANCE.resetResult();

        ScreenUtils.clear(Color.BLACK);

        if (GameManager.INSTANCE.isGameOver()) {
            engine.update(0);
        } else {
            engine.update(delta);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }

    public void logAllSystemsInEngine() {
        for (EntitySystem system : engine.getSystems()) {
            log.debug(system.getClass().getSimpleName());
        }
    }
}
