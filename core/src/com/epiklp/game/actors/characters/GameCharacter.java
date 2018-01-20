package com.epiklp.game.actors.characters;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epiklp.game.Cave;
import com.epiklp.game.actors.GameObject;

/**
 * Created by Asmei on 2017-11-27.
 */

public abstract class GameCharacter extends GameObject {

    protected int strengh;
    protected int life;

    protected boolean turn = true;

    protected float speedWalk;
    protected float attackSpeed;
    protected float attackDelta;


    public GameCharacter(Sprite sprite) {
        super(sprite);
        this.sprite = sprite;
    }

    public abstract void initStats();
    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(body.getPosition().x * Cave.PPM - sprite.getWidth() / 2, body.getPosition().y * Cave.PPM - sprite.getHeight() / 2);
        sprite.draw(batch);
    }


    @Override
    public void act(float delta){
        attackDelta += delta;
    }

    public float getSpeedWalk() {
        return speedWalk;
    }

    public void setSpeedWalk(float speedWalk) {
        this.speedWalk = speedWalk;
    }

    public int getStrengh() {
        return strengh;
    }

    public void setStrengh(int strengh) {
        this.strengh = strengh;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life += life;
        if(isDead())
            setToDelete();
    }

    public boolean isDead() {
        return (life <= 0);
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setTurn(boolean a)
    {
        turn = a;
    }

    public boolean getTurn()
    {
        return turn;
    }
}

