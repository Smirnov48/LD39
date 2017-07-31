package com.sgstudio.game.models;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.utils.Box2DHelper;

public class Сhair {
	private Map<String, TextureRegion> atlasWood;
	
	private SpriteBatch batch;
	private Sprite texture;
	
	private World world;
	private Body body;
	
	public Сhair(SpriteBatch batch, Sprite texture, World world){
		this.batch = batch;
		this.world = world;
		this.texture = texture;
	}
	
	public void createModel(int x, int y){
		createPhysics(x, y);
	}
	
	private void createPhysics(int x, int y) {
		body = Box2DHelper.makeBoxAroundSprite(world, texture);
		Box2DHelper.setTransform(body, x, y, 0);
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
