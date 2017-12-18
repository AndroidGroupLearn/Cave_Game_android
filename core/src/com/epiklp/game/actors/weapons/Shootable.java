package com.epiklp.game.actors.weapons;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Asmei on 2017-12-04.
 */

public interface Shootable {
    final int LIMIT = 5;
    Array<Bullet> activeBullets = new Array<Bullet>();

    void shoot();

}
