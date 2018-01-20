package com.epiklp.game.Functional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by epiklp on 29.11.17.
 */

public class Assets {

    //Asset
    public static final AssetManager manager = new AssetManager();
    public static final AssetDescriptor<Texture> logo = new AssetDescriptor<Texture>(Gdx.files.internal("Logo.png"), Texture.class);

    //player
    /*******************************************/
    public static final AssetDescriptor<Texture> player = new AssetDescriptor<Texture>(Gdx.files.internal("character/1.png"), Texture.class);
    public static final AssetDescriptor<Texture> FireBall = new AssetDescriptor<Texture>(Gdx.files.internal("character/firebal.png"), Texture.class);
    /*******************************************/

    //Enemy
    /*******************************************/
    public static final AssetDescriptor<Texture> flameDemon = new AssetDescriptor<Texture>(Gdx.files.internal("enemies/FlameDemon.png"), Texture.class);
    /*******************************************/


    //Buttons
    /*******************************************/
    public static final AssetDescriptor<Texture> leftButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/Left.png"), Texture.class);
    public static final AssetDescriptor<Texture> rightButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/Right.png"), Texture.class);
    public static final AssetDescriptor<Texture> attackButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/Attack.png"), Texture.class);
    public static final AssetDescriptor<Texture> upButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/Up.png"), Texture.class);
    public static final AssetDescriptor<Texture> homeButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/home2.png"), Texture.class);
    public static final AssetDescriptor<Texture> goButton = new AssetDescriptor<Texture>(Gdx.files.internal("button/go.png"), Texture.class);
    public static final AssetDescriptor<Texture> pauseWindow = new AssetDescriptor<Texture>(Gdx.files.internal("PauseMenu/window.png"), Texture.class);
    public static final AssetDescriptor<Texture> restartButton = new AssetDescriptor<Texture>(Gdx.files.internal("PauseMenu/button_restart.png"), Texture.class);
    public static final AssetDescriptor<Texture> resumeButton = new AssetDescriptor<Texture>(Gdx.files.internal("PauseMenu/button_resume.png"), Texture.class);
    public static final AssetDescriptor<Texture> backButton = new AssetDescriptor<Texture>(Gdx.files.internal("MenuButtons/button_back.png"), Texture.class);
    public static final AssetDescriptor<Texture> playButton = new AssetDescriptor<Texture>(Gdx.files.internal("MenuButtons/button_play.png"), Texture.class);
    public static final AssetDescriptor<Texture> quitButton = new AssetDescriptor<Texture>(Gdx.files.internal("MenuButtons/button_quit.png"), Texture.class);
    public static final AssetDescriptor<Texture> continueButton = new AssetDescriptor<Texture>(Gdx.files.internal("MenuButtons/button_continue.png"), Texture.class);
    public static final AssetDescriptor<Texture> creditButton = new AssetDescriptor<Texture>(Gdx.files.internal("MenuButtons/button_credit.png"), Texture.class);
    /*******************************************/

    //menu
    /*******************************************/
    public static final AssetDescriptor<Texture> menuMap = new AssetDescriptor<Texture>(Gdx.files.internal("Map/menu_map.png"), Texture.class);
    public static final AssetDescriptor<Music> menuMusic = new AssetDescriptor<Music>(Gdx.files.internal("sounds/menu.wav"), Music.class);
    public static final AssetDescriptor<Texture> menuLayer = new AssetDescriptor<Texture>(Gdx.files.internal("menu/all.png"), Texture.class);
    public static final AssetDescriptor<Texture> shopLayer = new AssetDescriptor<Texture>(Gdx.files.internal("menu/shop.png"), Texture.class);
    public static final AssetDescriptor<Texture> creditLayer = new AssetDescriptor<Texture>(Gdx.files.internal("menu/credit.png"), Texture.class);
    public static final AssetDescriptor<Texture> cave1Layer = new AssetDescriptor<Texture>(Gdx.files.internal("menu/cave1.png"), Texture.class);
    public static final AssetDescriptor<Texture> cave2Layer = new AssetDescriptor<Texture>(Gdx.files.internal("menu/cave2.png"), Texture.class);
    /*******************************************/

    public static BitmapFont Font; //Font

    public static final AssetDescriptor<Texture>[] layer = new AssetDescriptor[9]; // GameOver

    public static void load() {

        manager.load(logo);


        manager.load(player);
        manager.load(flameDemon);
        manager.load(FireBall);

        //Buttons
        manager.load(restartButton);
        manager.load(resumeButton);
        manager.load(leftButton);
        manager.load(rightButton);
        manager.load(upButton);
        manager.load(attackButton);
        manager.load(homeButton);
        manager.load(goButton);
        manager.load(pauseWindow);
        manager.load(backButton);
        manager.load(quitButton);
        manager.load(playButton);
        manager.load(continueButton);
        manager.load(creditButton);

        //Menu
        manager.load(menuLayer);
        manager.load(shopLayer);
        manager.load(creditLayer);
        manager.load(cave1Layer);
        manager.load(cave2Layer);
        manager.load(menuMap);
        manager.load(menuMusic);


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
        BitmapFont character = generator.generateFont(parameter);
        Font = character;

    }

    public static void dispose() {
        manager.dispose();
    }
}
