package com.sgstudio.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.train.Fuel;
import com.sgstudio.utils.Box2DHelper;

public class Chair {
	private static Fuel fuel;
	
	private SpriteBatch batch;
	private Sprite texture;
	
	private World world;
	private Body body;
	
	private int view=1;
	
	public Chair(SpriteBatch batch, Sprite texture, World world){
		this.batch = batch;
		this.world = world;
		this.texture = texture;
		
		fuel = new Fuel(view);
	}
	
	public void createModel(int x, int y){
		createPhysics(x, y);
	}
	
	private void createPhysics(int x, int y) {
		body = Box2DHelper.makeBoxAroundSpriteStatic(world, texture, "Chair");
		Box2DHelper.setTransform(body, x, y, 0);
		body.setFixedRotation(true);
	}
	
	private float x;
	public void render(){
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(texture, pos.x - texture.getWidth() / 2,  pos.y - texture.getHeight() / 2 );
		x = pos.x;
	}

	public void setPos(int x, int y){
		Box2DHelper.setTransform(body, x, y, 0);
	}
	
	public Vector2 getPosition() {
		return Box2DHelper.getPosition(body);
	}
	
	public static int getFuel(){ return fuel.getFuel(1); }
	public boolean isBroken(){ return fuel.isBroken(); }
}
