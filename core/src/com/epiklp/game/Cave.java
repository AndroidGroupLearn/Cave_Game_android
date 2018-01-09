package com.epiklp.game;

import com.badlogic.gdx.Game;


public class Cave extends Game {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int PPM = 32;
    public static final float SCALE = 2;

    @Override
    public void create() {
        Assets.load();
        Assets.manager.finishLoading();
        //z tego bedzie mozna zrobic pasek ładowania gdy bedzie wincej assetów
        //    while(!Assets.manager.update())
        //        System.out.println(Assets.manager.getProgress()*100 + "%");
        //this.setScreen(new Menu(this));
        this.setScreen(new logo(this));
        //this.setScreen(new GameScreen(this));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
