package com.sgstudio.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DHelper {
	
	public static Body makeBoxAroundSprite(World world, Sprite sprite) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX(), sprite.getY());
	    
		Body body = world.createBody(bodyDef);
     
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		
		body.createFixture(fixtureDef);
		shape.dispose();
		
		return body;
	}
	
	public static Body makeBox(World world, Vector2 size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
	    
		Body body = world.createBody(bodyDef);
     
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x, size.y);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		
		body.createFixture(fixtureDef);
		shape.dispose();
		
		return body;
	}
	
}
