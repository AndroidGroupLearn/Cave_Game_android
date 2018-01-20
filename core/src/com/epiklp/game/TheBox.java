package com.epiklp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.epiklp.game.actors.GameObject;

import java.util.Iterator;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by Asmei on 2017-11-28.
 */

public class TheBox {


    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_ENEMY  = 0x0002;
    public static final short CATEGORY_MAP    = 0x0004;
    public static final short CATEGORY_ITEM   = 0x0008;
    public static final short CATEGORY_SENSOR = 0x0016;
    public static final short CATEGORY_BULLET = 0x0032;
    public static final short CATEGORY_LIGHT  = 0x0064;

    public static final short MASK_PLAYER = -1;
    public static final short MASK_ENEMY  = CATEGORY_MAP | CATEGORY_SENSOR | CATEGORY_PLAYER | CATEGORY_BULLET;
    public static final short MASK_LIGHT  = CATEGORY_MAP | CATEGORY_BULLET | CATEGORY_PLAYER | CATEGORY_ENEMY | CATEGORY_LIGHT | CATEGORY_BULLET;
    public static final short MASK_SENSOR = CATEGORY_PLAYER;
    public static final short MASK_BULLET = CATEGORY_MAP | CATEGORY_BULLET | CATEGORY_PLAYER | CATEGORY_ENEMY;


    public static World world;
    public static RayHandler rayHandler;


    private static Array<GameObject> deleteArray = new Array<GameObject>();

    public static void initWorld() {
        world = new World(new Vector2(0, -10f), true);
        initRayHandler();
    }

    public static Body createBox(float x, float y, float width, float height, boolean isStatic, short category, short mask) {
        Body pBody;
        BodyDef def = new BodyDef();
        if (isStatic) def.type = BodyDef.BodyType.StaticBody;
        else def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x / Cave.PPM, y / Cave.PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / Cave.PPM, height / Cave.PPM);

        pBody.createFixture(shape, 1);//.setUserData("player");
        shape.dispose();
        return pBody;
    }

    
    public static void createBoxSensor(Body body, float width, float height, Vector2 shiftFromCenter){

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/Cave.PPM,height/Cave.PPM, new Vector2(shiftFromCenter.x/Cave.PPM,shiftFromCenter.y/Cave.PPM), 0);
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 0;
        fixDef.friction = 0;
        fixDef.isSensor = true;
        fixDef.filter.categoryBits = CATEGORY_SENSOR;
        fixDef.filter.maskBits     = MASK_SENSOR;
        body.createFixture(fixDef);
        shape.dispose();
    }
    public static void destroyWorld() {
        if(rayHandler!= null) {
            rayHandler.dispose();
        }
        if (world != null) {
            world.dispose();
        }

    }
    public static void initRayHandler() {
        RayHandler.setGammaCorrection(false);
        RayHandler.useDiffuseLight(false);
        rayHandler = new RayHandler(TheBox.world);
        rayHandler.setAmbientLight(.05f);
        rayHandler.setCulling(true);
        rayHandler.setShadows(true);
        Light.setGlobalContactFilter(TheBox.CATEGORY_LIGHT, (short) 0, TheBox.MASK_LIGHT);
    }

    public static PointLight createPointLight(Body body, int rays, Color color, int distance, int x, int y){
        PointLight pointLight  = new PointLight(rayHandler, rays, color, 10, -2,-2);
        pointLight.attachToBody(body);
        pointLight.setXray(true);
        pointLight.setIgnoreAttachedBody(true);
        return pointLight;

    }
    //It should be check world.isLocket before you use it
    public static void destroyBody(Body body){
        world.destroyBody(body);
    }

    public static void addToDeleteArray(GameObject gameObject){
        deleteArray.add(gameObject);
    }
    public static Iterator<GameObject> getDeleteArrayIter(){
        return deleteArray.iterator();
    }
}
