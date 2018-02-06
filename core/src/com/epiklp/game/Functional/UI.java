package com.epiklp.game.Functional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.epiklp.game.Cave;


public class UI extends Stage {
    private BitmapFont bitmapFont;
    private Label text;
    private Label.LabelStyle labelStyle;
    private Image lifeTexture, magicTexture, timeTexture;
    private Image statusBelt;
    private int length;


    public UI() {
        bitmapFont = new BitmapFont();
        labelStyle = new Label.LabelStyle(bitmapFont, Color.WHITE);
        text = new Label("FPS:" + Gdx.graphics.getFramesPerSecond(), labelStyle);
        text.setPosition(0, Cave.HEIGHT /2);
        addActor(text);
        lifeTexture = new Image(Assets.manager.get(Assets.statusHp));
        lifeTexture.setPosition(219, Cave.HEIGHT - 69);
        lifeTexture.setScale(Cave.SCALE*1.5f);
        magicTexture = new Image(Assets.manager.get(Assets.statusMana));
        magicTexture.setPosition(219, Cave.HEIGHT - 105);
        magicTexture.setScale(Cave.SCALE*1.5f);
        timeTexture = new Image(Assets.manager.get(Assets.statusTime));
        timeTexture.setPosition(219, Cave.HEIGHT - 140);
        timeTexture.setScale(Cave.SCALE*1.5f);
        statusBelt = new Image(Assets.manager.get(Assets.statusBelt));
        statusBelt.setScale(Cave.SCALE*1.5f);
        statusBelt.setPosition(0, Cave.HEIGHT-statusBelt.getHeight()*2*1.5f);
        addActor(statusBelt);
        addActor(lifeTexture);
        addActor(magicTexture);
        addActor(timeTexture);
        length = (int)timeTexture.getWidth();
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
        lifeTexture.setWidth(length);
        magicTexture.setWidth(length);
        text.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
    }
}
