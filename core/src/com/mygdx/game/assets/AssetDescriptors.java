package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> FONT_64 =
            new AssetDescriptor<BitmapFont>(AssetPaths.FONTS_SIMPLE_64_FNT, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> GAME_PLAY =
            new AssetDescriptor<TextureAtlas>(AssetPaths.GAMEPLAY_GAMEPLAY_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Sound> PICK_SOUND =
            new AssetDescriptor<Sound>(AssetPaths.SOUNDS_HAZELNUT_WAV, Sound.class);

    private AssetDescriptors() {
    }
}
