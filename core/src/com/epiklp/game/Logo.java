package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by epiklp on 08.01.18.
 */

public class Logo implements Screen {

    final Cave cave;
    private float time = 0;
    private Image image;
    private Stage stage;

    public Logo(final Cave cave) {
        this.cave = cave;
        stage = new Stage();
        image = new Image(Assets.manager.get(Assets.logo));
        image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(image);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        time += delta;
        if(time > 5)
        {
            dispose();
            cave.setScreen(new NewMenu(cave));
        }
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
        stage.dispose();
    }
}
