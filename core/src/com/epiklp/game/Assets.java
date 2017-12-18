package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by epiklp on 29.11.17.
 */

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> player = new AssetDescriptor<Texture>("character/1.png", Texture.class);
    public static final AssetDescriptor<Texture> FireBall = new AssetDescriptor<Texture>("character/firebal.png", Texture.class);

    public static final AssetDescriptor<Texture> flameDemon = new AssetDescriptor<Texture>("enemies/FlameDemon.png", Texture.class);
    public static final AssetDescriptor<Texture>[] layer = new AssetDescriptor[9];
    public static BitmapFont character;

    public static final AssetDescriptor<Texture> leftButton = new AssetDescriptor<Texture>("button/Left.png", Texture.class);
    public static final AssetDescriptor<Texture> rightButton = new AssetDescriptor<Texture>("button/Right.png", Texture.class);
    public static final AssetDescriptor<Texture> attackButton = new AssetDescriptor<Texture>("button/Attack.png", Texture.class);
    public static final AssetDescriptor<Texture> upButton = new AssetDescriptor<Texture>("button/Up.png", Texture.class);
    public static final AssetDescriptor<Texture> homeButton = new AssetDescriptor<Texture>("button/home2.png", Texture.class);


    public static final AssetDescriptor<Texture> pauseWindow = new AssetDescriptor<Texture>("PauseMenu/window.png", Texture.class);
    public static final AssetDescriptor<Texture> restartButton = new AssetDescriptor<Texture>("PauseMenu/button_restart.png", Texture.class);
    public static final AssetDescriptor<Texture> resumeButton = new AssetDescriptor<Texture>("PauseMenu/button_resume.png", Texture.class);

    public static final AssetDescriptor<Texture> backButton = new AssetDescriptor<Texture>("MenuButtons/button_back.png", Texture.class);
    public static final AssetDescriptor<Texture> playButton = new AssetDescriptor<Texture>("MenuButtons/button_play.png", Texture.class);
    public static final AssetDescriptor<Texture> quitButton = new AssetDescriptor<Texture>("MenuButtons/button_quit.png", Texture.class);
    public static final AssetDescriptor<Texture> continueButton = new AssetDescriptor<Texture>("MenuButtons/button_continue.png", Texture.class);
    public static final AssetDescriptor<Texture> creditButton = new AssetDescriptor<Texture>("MenuButtons/button_credit.png", Texture.class);


    public static void load() {
        manager.load(player);
        manager.load(flameDemon);
        manager.load(FireBall);

        manager.load(restartButton);
        manager.load(resumeButton);

        manager.load(leftButton);
        manager.load(rightButton);
        manager.load(upButton);
        manager.load(attackButton);
        manager.load(homeButton);

        manager.load(pauseWindow);
        manager.load(backButton);
        manager.load(quitButton);
        manager.load(playButton);
        manager.load(continueButton);
        manager.load(creditButton);


        String tmp = "";
        for (int i = 0; i < 9; i++) {
            tmp = "layer/" + (i + 1) + ".png";
            layer[i] = new AssetDescriptor<Texture>(tmp, Texture.class);
            manager.load(layer[i]);
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        BitmapFont ala = generator.generateFont(parameter);
        character = ala;

    }

    public static void dispose() {
        manager.dispose();
    }
}
