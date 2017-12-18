package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


class Menu implements Screen {
    final Cave cave;
    private TextureGame textMenu;
    private MenuButtons menu;

    public Menu(final Cave cave) {
        this.cave = cave;

        textMenu = new TextureGame();
        menu = new MenuButtons();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        textMenu.draw();
        menu.draw();

    }

    private void update(float delta) {
        if (menu.getPlayPress()) {
            dispose();
            cave.setScreen(new GameScreen(cave));
        }
        if (menu.getContinuePress()) {
        }
        if (menu.getCreditPress()) {
            dispose();
            cave.setScreen(new CreditsScreen(cave));
        }
        if (menu.getQuitPress()) {
            dispose();
            Gdx.app.exit();
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
        textMenu.dispose();
        menu.dispose();
    }

    /**
     * Created by epiklp on 22.11.17.
     */

    public static class MenuButtons extends InputListener {
        private Stage stage;
        private Image playImage, creditImage, continueImage, quitImage;
        private Boolean playPress, creditPress, continuePress, quitPress;

        private Table tbr;

        public MenuButtons() {
            stage = new Stage();
            Gdx.input.setInputProcessor(stage);
            playPress = creditPress = continuePress = quitPress = false;
            playImage = new Image(Assets.manager.get(Assets.playButton));
            playImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    playPress = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    playPress = false;
                }
            });
            continueImage = new Image(Assets.manager.get(Assets.continueButton));
            continueImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    continuePress = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    continuePress = false;
                }
            });
            creditImage = new Image(Assets.manager.get(Assets.creditButton));
            creditImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    creditPress = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    creditPress = false;
                }
            });
            quitImage = new Image(Assets.manager.get(Assets.quitButton));
            quitImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    quitPress = true;
                    return true;
                }
            });

            tbr = new Table();
            tbr.bottom().left();
            tbr.row().padBottom(10);
            tbr.add(playImage).size(playImage.getWidth(), playImage.getHeight());
            tbr.row().padBottom(10);
            tbr.add(continueImage).size(continueImage.getWidth(), continueImage.getHeight());
            tbr.row().padBottom(10);
            tbr.add(creditImage).size(creditImage.getWidth(), creditImage.getHeight());
            tbr.row().padBottom(10);
            tbr.add(quitImage).size(quitImage.getWidth(), quitImage.getHeight());

            stage.addActor(tbr);

        }

        public void draw() {
            stage.act();
            stage.draw();
        }

        public void dispose() {
            stage.dispose();
        }

        public Boolean getPlayPress() {
            return playPress;
        }

        public Boolean getCreditPress() {
            return creditPress;
        }

        public Boolean getContinuePress() {
            return continuePress;
        }

        public Boolean getQuitPress() {
            return quitPress;
        }

        public void setQuitPress(Boolean quitPress) {
            this.quitPress = quitPress;
        }
    }
}
