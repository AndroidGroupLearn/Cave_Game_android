package com.epiklp.game.actors.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epiklp.game.functionals.Assets;
import com.epiklp.game.functionals.b2d.BodyCreator;
import com.epiklp.game.functionals.b2d.TheBox;

/**
 * Created by Asmei on 2017-11-29.
 */

public class Crown extends Enemy {
    private Sprite currentFrame;
    private static float FRAME_DURATION = 0.15f;


    public Crown(float x, float y) {
        super(Assets.manager.get(Assets.textureAtlas).createSprite("crown", 1), 33, 32);

        body = BodyCreator.createBody(x, y, false);
        BodyCreator.createBoxShape(body, 30f, 20f, 1f, 0);
        BodyCreator.createBoxSensor(body, 150f, 70f, new Vector2(0, 45f), PATROL_SENSOR);
        body.setUserData(this);
        body.setGravityScale(0f);

        light = TheBox.createPointLight(body, 64, new Color(1.0f, 0.498f, 0.314f, .6f), true, 10, 0, 0);

        initStats();

        Array<Sprite> spritesForRunning = new Array<Sprite>();
        spritesForRunning.add(Assets.manager.get(Assets.textureAtlas).createSprite("crown",1));
        spritesForRunning.add(Assets.manager.get(Assets.textureAtlas).createSprite("crown",2));
        spritesForRunning.add(Assets.manager.get(Assets.textureAtlas).createSprite("crown",3));
        spritesForRunning.add(Assets.manager.get(Assets.textureAtlas).createSprite("crown",4));
        spritesForRunning.add(Assets.manager.get(Assets.textureAtlas).createSprite("crown",5));

        animator.addNewFrames(0.1f, spritesForRunning, STATE.RUNNING, Animation.PlayMode.LOOP);

        setPatrolPoints();

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        animate(delta, state);
    }

    @Override
    public void initStats() {
        this.actLife = this.maxLife = 30;
        this.attackSpeed = 2;
        this.runSpeed = 5;
        this.strengh = 10;
        this.attackRange = 5f;
        this.patrolRange = 2f;
        state = STATE.RUNNING;

    }
}
