package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Asmei on 2017-11-27.
 */

public class EndScreen implements Screen {
    final Cave cave;

    private TextureGame textMenu;
    private Stage stage;
    private BitmapFont bitmapFont;
    private Label text;
    private Label.LabelStyle labelStyle;
    private Image backImage;
    private Boolean backPress;

    public EndScreen(Cave cave) {
        this.cave = cave;


        textMenu = new TextureGame();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        bitmapFont = new BitmapFont();
        labelStyle = new Label.LabelStyle(Assets.character, Color.WHITE);
        text = new Label("GAME OVER!", labelStyle);
        text.setPosition(0, Cave.HEIGHT / 2);
        stage.addActor(text);
        backPress = false;
        backImage = new Image(new Texture("MenuButtons/button_quit.png"));
        backImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backPress = true;
                return true;
            }
        });
        backImage.setPosition(Cave.WIDTH - backImage.getWidth(), 0);
        stage.addActor(backImage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (backPress == true) {
            cave.setScreen(new Menu(cave));
            dispose();
        }
        textMenu.draw();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textMenu.dispose();
        stage.dispose();
    }
}
