package com.epiklp.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class TiledObject {
    public static Array<Body> parseTiledObjectLayer(World world, MapObjects objects) {
        Array<Body> bodies = new Array<Body>();
        for (MapObject object : objects) {
            Shape shape;
            if (object instanceof PolylineMapObject) {
                shape = createPolyLine((PolylineMapObject) object);
            } else if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject) object);
            } else {
                continue;
            }
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1);
            body.setUserData(TiledObject.class.getSimpleName());
            bodies.add(body);
            shape.dispose();
        }
        return bodies;
    }


    private static ChainShape createPolyLine(PolylineMapObject polyline) {
        float[] verticle = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVerticle = new Vector2[verticle.length / 2];
        for (int i = 0; i < verticle.length / 2; ++i) {
            worldVerticle[i] = new Vector2();
            worldVerticle[i].x = verticle[i * 2] / Cave.PPM;
            worldVerticle[i].y = verticle[i * 2 + 1] / Cave.PPM;
        }

        ChainShape cs = new ChainShape();
        cs.createChain(worldVerticle);
        return cs;
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / Cave.PPM * 2,
                (rectangle.y + rectangle.height * 0.5f) / Cave.PPM * 2);
        polygon.setAsBox(rectangle.width * 0.5f / Cave.PPM * 2,
                rectangle.height * 0.5f / Cave.PPM * 2,
                size,
                0.0f);
        return polygon;
    }
}
