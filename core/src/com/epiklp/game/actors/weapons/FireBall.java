package com.epiklp.game.actors.weapons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epiklp.game.Cave;
import com.epiklp.game.Functional.Assets;
import com.epiklp.game.Functional.TheBox;
import com.epiklp.game.actors.characters.GameCharacter;

/**
 * Created by epiklp on 29.11.17.
 */

public class FireBall extends Bullet {
    private float time;

    public FireBall(float x, float y, int hitPoint, GameCharacter gameCharacterRef, boolean turn) {
        super(new Sprite(Assets.manager.get(Assets.fireBall)), hitPoint, gameCharacterRef);

        body = TheBox.createBody(5, 0, false);
        TheBox.createBoxShape(body, 25f, 30f, 0.1f, TheBox.CATEGORY_BULLET, TheBox.MASK_BULLET);

        sprite.setSize(0.75f * Cave.PPM * Cave.SCALE, 0.75f * Cave.PPM * Cave.SCALE);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2 + 1.f);
        body.setTransform(x + (turn ? 2 : -2), y, 0);
        sprite.setFlip(!turn, false);

        body.setUserData(this);
        body.setBullet(true);

        time = 5f;
        light = TheBox.createPointLight(body, 64, Color.FOREST, 5, 0, 0);

        body.applyLinearImpulse((turn ? 1 : -1) * 7, 0, x, y, true);
    }

    @Override
    public void act(float delta) {
        body.setLinearVelocity(body.getLinearVelocity().x, -body.getLinearVelocity().y);
        time -= delta;
        if (time < 0) {
            setToDelete();
        }
    }


    @Override
    public void initStats() {

    }
}
