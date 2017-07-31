package com.sgstudio.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.utils.Box2DHelper;

public class Сhair {
	private SpriteBatch batch;
	private Sprite texture;
	
	private World world;
	private Body body;
	
	public Сhair(SpriteBatch batch, Sprite texture){
		this.batch = batch;
		this.texture = texture;
		
		createPhysics();
	}
	
	private void createPhysics() {
		body = Box2DHelper.makeBoxAroundSprite(world, texture);
		Box2DHelper.setTransform(body, 290, 55, 0);
		body.setFixedRotation(true);
	}
	
	private float x;
	public void render(){
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(texture, pos.x - texture.getWidth() / 2, pos.y - texture.getHeight() / 2);
		x = pos.x;
	}

	public void setPos(int x, int y){
		Box2DHelper.setTransform(body, x, y, 0);
	}
	
	public Vector2 getPosition() {
		return Box2DHelper.getPosition(body);
	}
}
