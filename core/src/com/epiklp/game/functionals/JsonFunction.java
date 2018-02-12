package com.epiklp.game.functionals;

import com.badlogic.gdx.utils.Json;
import com.epiklp.game.Cave;


/**
 * Created by epiklp on 20.01.18.
 */

public class JsonFunction {
    public static Json json;
    public static One one;

    public static void initzialie() {
        json = new Json();
        json.setUsePrototypes(false);
    }

    public static void Create() {
        one = new One();
        json.toJson(one,Cave.FILE);
        Sound.EFFECTS = one.game.effect;
        Sound.MUSIC = one.game.music;
        Cave.LANGUAGE = one.game.language;
    }

    public static void Read() {
        one = json.fromJson(One.class, Cave.FILE);
        Sound.EFFECTS = one.game.effect;
        Sound.MUSIC = one.game.music;
        Cave.LANGUAGE = one.game.language;
    }

    public static void edit(String what)
    {
        if(what.equals("Music"))
        {
            one.game.music = Sound.MUSIC = !Sound.MUSIC;
        }
        else if(what.equals("effects"))
        {
            one.game.effect = Sound.EFFECTS = !Sound.EFFECTS;
        }
        else if (what.equals("language"))
        {
            if(Cave.LANGUAGE.equals("en"))
                one.game.language = Cave.LANGUAGE = "pl";
            else
                one.game.language = Cave.LANGUAGE = "en";
        }
    }

    public static void updateJSON()
    {
        json.toJson(one,Cave.FILE);
    }
}

class One
{
    Game game = new Game();
    Player player = new Player();
}

class Game
{
    boolean music = false;
    boolean effect = true;
    String language = "en";
}

class Player
{
    int level = 1;
    float experience = 0;
    int strengh = 10;
    int maxlife = 100;
    int life = 100;
    int magic = 100;
    float attackSpeed = 0.7f;
}