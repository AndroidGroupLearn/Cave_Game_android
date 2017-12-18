package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class UI extends Stage {
    private BitmapFont bitmapFont;
    private Label text;
    private Label.LabelStyle labelStyle;
    private Image lifeTexture, magicTexture;


    public UI() {
        bitmapFont = new BitmapFont();
        labelStyle = new Label.LabelStyle(bitmapFont, Color.WHITE);
        text = new Label("FPS:" + Gdx.graphics.getFramesPerSecond(), labelStyle);
        text.setPosition(0, Cave.HEIGHT - 100);
        addActor(text);
        lifeTexture = new Image(CreateTexture(100, 32, 1, 0, 0, 1));
        lifeTexture.setPosition(0, Cave.HEIGHT - 32);
        magicTexture = new Image(CreateTexture(100, 32, 0, 0, 1, 1));
        magicTexture.setPosition(0, Cave.HEIGHT - 64);
        addActor(lifeTexture);
        addActor(magicTexture);
    }

    private Texture CreateTexture(int width, int hight, int r, int g, int b, int a) {
        Texture tmp;
        Pixmap pixmap = new Pixmap(width * 4, hight, Pixmap.Format.RGB888);
        pixmap.setColor(r, g, b, 1);
        pixmap.fillRectangle(0, 0, width * 4, hight);
        tmp = new Texture(pixmap);
        pixmap.dispose();
        return tmp;
    }


    public void update(int life, int magic) {
        if (life <= 0) life = 1;
        if (magic <= 0) magic = 1;
        lifeTexture.setX((life*4)-400);
        magicTexture.setX((magic*4)-400);
        text.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
    }
}
