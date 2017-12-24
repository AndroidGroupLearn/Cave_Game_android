package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.epiklp.game.actors.characters.Hero;


/**
 * Created by epiklp on 23.12.17.
 */

public class newMenu implements Screen {
    final Cave cave;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private MyContactListener myContactListener;
    private Controller controller;
    private Hero hero;
    private float horizontalForce = 0;
    private Box2DDebugRenderer b2dr;
    private Image image;
    private Image shop;
    private Image credit;
    private Image cave1;
    private Image cave2;

    private boolean pause = false;

    private pauseMenu MenuPause;

    public newMenu(Cave cave)
    {
        this.cave = cave;

        image = new Image(Assets.manager.get(Assets.menulayer));
        image.setSize(Cave.WIDTH, Cave.HEIGHT);
        shop = new Image(Assets.manager.get(Assets.shoplayer));
        shop.setSize(200,200);
        shop.setPosition(500,55);
        credit = new Image(Assets.manager.get(Assets.creditlayer));
        credit.setSize(200,200);
        credit.setPosition(150, 55);
        cave1 = new Image(Assets.manager.get(Assets.cave1layer));
        cave1.setSize(400,Cave.HEIGHT);
        cave1.setPosition(Cave.WIDTH-500, 55);
        cave2 = new Image(Assets.manager.get(Assets.cave2layer));
        cave2.setSize(400,Cave.HEIGHT);
        cave2.setPosition(Cave.WIDTH-500, 55);

        TheBox.initWorld();
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Cave.WIDTH, Cave.HEIGHT);
        viewport = new ExtendViewport(Cave.WIDTH / 1.2f , Cave.HEIGHT / 1.2f, camera);
        stage = new Stage(viewport);
        stage.addActor(image);
        stage.addActor(shop);
        stage.addActor(credit);
        stage.addActor(cave1);
        myContactListener = new MyContactListener();
        controller = new Controller();
        Gdx.input.setInputProcessor(new InputMultiplexer());
        hero = new Hero();

        stage.addActor(hero);
        stage.addActor(cave2);
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(controller);
        TheBox.createBox(0,50,Cave.WIDTH,10,true, (short)0, (short)0);

        MenuPause = new pauseMenu();
    }

    @Override
    public void show() {

    }

    private void update(float delta)
    {
        TheBox.world.step(1 / 60f, 6, 2);
        inputUpdate();
    }

    private void inputUpdate() {
        if (Gdx.input.isTouched()) {
            if (controller.isLeftPressed()) {
                hero.getSprite().setFlip(true, false);
                hero.setTurn(false);
                if (horizontalForce > -(hero.getSpeedWalk()))
                    horizontalForce -= 0.4f;
            } else if (controller.isRightPressed()) {
                hero.getSprite().setFlip(false, false);
                hero.setTurn(true);
                if (horizontalForce < (hero.getSpeedWalk()))
                    horizontalForce += 0.4f;
            }
        } else {
            if (horizontalForce > 0.1) {
                horizontalForce -= 0.2f;
            } else if (horizontalForce < -0.1) {
                horizontalForce += 0.2f;
            } else {
                horizontalForce = 0;
            }
        }
        hero.setSpeedX(horizontalForce);
        if (controller.isUpPressed() && hero.getBody().getLinearVelocity().y == 0) {
            hero.setSpeedY(7f);
        }

        if (controller.isAttackPressed()) {
            hero.shoot();
        }

        if(controller.isHomePresed())
        {
            pause();
        }
    }

    @Override
    public void render(float delta) {

        stage.act();
        stage.draw();
        controller.draw();
        b2dr.render(TheBox.world, camera.combined.scl(Cave.PPM));
        if(pause == true)
            MenuPause.draw();
        else {
            update(Gdx.graphics.getDeltaTime());
            TheBox.world.setContactListener(myContactListener);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        pause = true;
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
        controller.dispose();
        hero.destroy();
    }
}
