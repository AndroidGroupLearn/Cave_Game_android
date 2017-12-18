package com.epiklp.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.epiklp.game.Cave;
import com.epiklp.game.TheBox;

import box2dLight.Light;

/**
 * Created by Asmei on 2017-11-27.
 */

public abstract class GameObject extends Actor {

    protected Body body;
    protected Sprite sprite;
    protected Light light;


    public GameObject(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void initStats();
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(body.getPosition().x * Cave.PPM - sprite.getWidth() / 2, body.getPosition().y * Cave.PPM - sprite.getHeight() / 2);
        sprite.draw(batch);
    }

    public void destroy(){
        if(light != null)
            light.remove();
        TheBox.destroyBody(body);
        this.remove();
    }

    public void setToDelete(){TheBox.addToDeleteArray(this);}

    public Sprite getSprite() {
        return sprite;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}

