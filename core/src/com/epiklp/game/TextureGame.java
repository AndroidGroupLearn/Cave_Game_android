package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by epiklp on 18.11.17.
 * <p>
 * ParallaxBackground is a bit unoptimalized - it use a 9 texture.
 */

public class TextureGame {
    private Stage stage;
    private ParallaxBackground parallaxBackground;
    private Array<Texture> textures;

    public TextureGame() {
        stage = new Stage(new ExtendViewport(Cave.WIDTH, Cave.HEIGHT));
        Gdx.input.setInputProcessor(stage);
        textures = new Array<Texture>();
        for (int i = 0; i < 9; i++) {
            textures.add(Assets.manager.get(Assets.layer[i]));
        }
        parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Cave.WIDTH, Cave.HEIGHT);
        parallaxBackground.setSpeed(1);
        stage.addActor(parallaxBackground);
    }

    public void draw() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        textures.clear();
        stage.dispose();
    }
}
