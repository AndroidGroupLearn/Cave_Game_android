package com.epiklp.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.epiklp.game.Cave;
import com.epiklp.game.Functional.Assets;
import com.epiklp.game.Functional.Controller;
import com.epiklp.game.Functional.MyContactListener;
import com.epiklp.game.Functional.TheBox;
import com.epiklp.game.actors.characters.Hero;


/**
 * Created by epiklp on 23.12.17.
 */

public class Menu implements Screen {
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
    private Image enter;
    private Body creditBody, shopBody, cavBody;
    private Music soundTrack;

    private boolean pause = false;

    private PauseMenu MenuPause;

    public Menu(Cave cave)
    {
        this.cave = cave;

        //music
        soundTrack = Assets.manager.get(Assets.menuMusic);
        soundTrack.setLooping(true);
        soundTrack.play();

        //Download Assets
        image = new Image(Assets.manager.get(Assets.menuLayer));
        image.setSize(Cave.WIDTH, Cave.HEIGHT);
        shop = new Image(Assets.manager.get(Assets.shopLayer));
        shop.setSize(200,200);
        shop.setPosition(500,55);
        credit = new Image(Assets.manager.get(Assets.creditLayer));
        credit.setSize(150,150);
        credit.setPosition(180, 55);
        cave1 = new Image(Assets.manager.get(Assets.cave1Layer));
        cave1.setSize(400,Cave.HEIGHT);
        cave1.setPosition(Cave.WIDTH-500, 55);
        cave2 = new Image(Assets.manager.get(Assets.cave2Layer));
        cave2.setSize(400,Cave.HEIGHT);
        cave2.setPosition(Cave.WIDTH-500, 55);
        enter = new Image(Assets.manager.get(Assets.goButton));
        enter.setSize(Cave.WIDTH / 10, Cave.WIDTH / 10);

        //Create stage
        camera = new OrthographicCamera(Cave.WIDTH, Cave.HEIGHT);
        viewport = new ExtendViewport(Cave.WIDTH / 1.5f , Cave.HEIGHT / 1.2f, camera);
        stage = new Stage(viewport);
        stage.addActor(image);
        stage.addActor(shop);
        stage.addActor(credit);
        stage.addActor(cave1);
        hero = new Hero();  //Hero
        stage.addActor(hero);
        stage.addActor(cave2);

        //Crete WorldBox
        TheBox.initWorld();
        b2dr = new Box2DDebugRenderer();
        TheBox.createBox(0,50,Cave.WIDTH,10,true, (short)0, (short)0);
        creditBody = TheBox.createBox(257, 128, 64,64, true, (short)0, (short)0);
        shopBody = TheBox.createBox(600, 154, 86,96, true, (short)0, (short)0);


        //Multi Events
        MenuPause = new PauseMenu();
        controller = new Controller(false);
        myContactListener = new MyContactListener();
        Gdx.input.setInputProcessor(new InputMultiplexer());
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(MenuPause);
        inputMultiplexer.addProcessor(controller);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        controller.draw();
        b2dr.render(TheBox.world, camera.combined.scl(Cave.PPM));
        if(pause == true) {
            MenuPause.draw();
            updateMenu(Gdx.graphics.getDeltaTime());
        }
        else {
            update(Gdx.graphics.getDeltaTime());
            TheBox.world.setContactListener(myContactListener);
        }
    }

    private void update(float delta)
    {
        TheBox.world.step(1 / 60f, 6, 2);
        inputUpdate();  //Controler
        /*
        float x = hero.getBody().getPosition().x;
        if(x > 180 && x < 330) // credits
        {
            stage.addActor(enter);
            enter.setPosition(180, Cave.HEIGHT/2);
        }
        else if(x > 500 && x < 700)
        {
            stage.addActor(enter);
            enter.setPosition(500, Cave.HEIGHT/2);
        }//shop
        else if(x > Cave.WIDTH-500)
        {
            stage.addActor(enter);
            enter.setPosition(Cave.WIDTH-500, Cave.HEIGHT/2);
        }//map
        else
        {
            enter.remove();
        }
        */
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

        if(controller.isHomePresed())
        {
            pause();
        }
    }

    public void updateMenu(float delta)
    {
        if(MenuPause.presssResume)
        {
            resume();
        }
        if(MenuPause.pressRestart)
        {
            dispose();
            cave.setScreen(new GameScreen(cave));
        }
        if(MenuPause.pressExit)
        {
            dispose();
            Gdx.app.exit();
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
