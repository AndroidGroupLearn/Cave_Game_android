package com.epiklp.game.Functional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.epiklp.game.Cave;
import com.epiklp.game.Functional.Assets;


public class Controller extends Stage {
    private final float BUTTON_SIZE = Cave.WIDTH / 10;
    private boolean upPressed, atackPressed, leftPressed, rightPressed, homePresed;
    private Table tabRight, tabLeft;
    private Image buttonRight, buttonLeft, buttonUp, buttonAttack, buttonHome;

    public Controller(boolean atack) {
        //Buttons on Left
        /*******************************************/
        tabLeft = new Table();
        tabLeft.bottom().left();

        buttonRight = new Image(Assets.manager.get(Assets.rightButton));
        buttonRight.setSize(BUTTON_SIZE, BUTTON_SIZE);
        buttonRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        buttonLeft = new Image(Assets.manager.get(Assets.leftButton));
        buttonLeft.setSize(BUTTON_SIZE, BUTTON_SIZE);
        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });
        tabLeft.add(buttonLeft).size(buttonLeft.getWidth(), buttonLeft.getHeight());
        tabLeft.add(buttonRight).size(buttonRight.getWidth(), buttonRight.getHeight());
        addActor(tabLeft);
        /*******************************************/

        //Buttons on Right
        /*******************************************/
        tabRight = new Table();
        if(atack == true)
            tabRight.setPosition(Gdx.graphics.getWidth()- 64, (Cave.WIDTH / 10));
        else
            tabRight.setPosition(Gdx.graphics.getWidth()- 64, buttonLeft.getHeight()/2);

        buttonUp = new Image(Assets.manager.get(Assets.upButton));
        buttonUp.setSize(BUTTON_SIZE, BUTTON_SIZE);
        buttonUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });
        if(atack == true) {
            buttonAttack = new Image(Assets.manager.get(Assets.attackButton));
            buttonAttack.setSize(BUTTON_SIZE, BUTTON_SIZE);
            buttonAttack.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    atackPressed = true;
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    atackPressed = false;
                }
            });
            tabRight.add(buttonAttack).size(buttonAttack.getWidth(), buttonAttack.getHeight());
            tabRight.row().padBottom(10);
        }
        tabRight.add(buttonUp).size(buttonUp.getWidth(), buttonUp.getHeight());
        addActor(tabRight);
        /*******************************************/


        //Pause/Menu
        /*******************************************/
        buttonHome = new Image(Assets.manager.get(Assets.homeButton));
        buttonHome.setPosition(Gdx.graphics.getWidth() - 74, Gdx.graphics.getHeight()-74);
        buttonHome.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                homePresed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                homePresed = false;
            }
        });
        addActor(buttonHome);
        /*******************************************/
    }


    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isAttackPressed() {
        return atackPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isHomePresed() {
        return homePresed;
    }

}
