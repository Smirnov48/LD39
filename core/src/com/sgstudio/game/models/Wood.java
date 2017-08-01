package com.sgstudio.game.models;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sgstudio.utils.Box2DHelper;

public class Wood {
	private int wood;
	
	private World world;
	private Body body;
	
	private SpriteBatch batch;
	private Sprite texture;
	
	public Wood(SpriteBatch batch, Sprite texture, World world){
		this.batch = batch;
		this.texture = texture;
		this.world = world;
	}
	
	private boolean notDel=true;
	public void delTexture(){
		notDel=!notDel;
	}
	
	public void createModel(float x, float y){
		createPhysics(x, y);
	}
	
	public void setPos(int x, int y){
		Box2DHelper.setTransform(body, x, y, 0);
	}
	
	public Vector2 getPosition() {
		return Box2DHelper.getPosition(body);
	}
	
	private void createPhysics(float x, float y) {
		body = Box2DHelper.makeBoxAroundSpriteStatic(world, texture, this);
		Box2DHelper.setTransformNormal(body, x, y, 0);
		body.setFixedRotation(true);
	}
	
	@SuppressWarnings("unused")
	private float x;
	public void render(){
		if (body != null) {
			Vector2 pos = Box2DHelper.getPosition(body);
			if(notDel) batch.draw(texture, pos.x - texture.getWidth() / 2,  pos.y - texture.getHeight() / 2 );
			x = pos.x;
		}
	}
	
	public void del(){
		if (wood==0) {
			notDel = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					try {
						if (body != null) {
							world.destroyBody(body);
							body = null;
						}
					} catch (java.lang.NullPointerException e) {
						Gdx.app.log("Error: ", e.getMessage());
					}
				}
			});
		}
	}
	
	public void destroy() {
		Array<Fixture> array = body.getFixtureList();
		for (int i = 0; i < array.size; i++) {
			Filter filter = new Filter();
			filter.maskBits = 0;
			array.get(i).setFilterData(filter);
		}
	}
	
	public void setWood(int wood){ this.wood = wood; }
	public int getWood(){ return wood; }
}
