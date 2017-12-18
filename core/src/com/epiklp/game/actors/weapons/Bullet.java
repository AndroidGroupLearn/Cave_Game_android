package com.epiklp.game.actors.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epiklp.game.actors.GameObject;
import com.epiklp.game.actors.characters.GameCharacter;

import box2dLight.PointLight;

/**
 * Created by Asmei on 2017-12-04.
 */

public abstract class Bullet extends GameObject {

    protected GameCharacter gameCharacter;
    protected int hitPoint;

    public Bullet(Sprite sprite, int hitPoint, GameCharacter gameCharacterRef){
        super(sprite);
        this.hitPoint = hitPoint;
        this.gameCharacter = gameCharacterRef;

    }

    public int getHitPoint(){return hitPoint;}

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }
}
