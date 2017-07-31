package com.sgstudio.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.train.Fuel;
import com.sgstudio.utils.Box2DHelper;

public class Passager {
	private Fuel fuel;

	private SpriteBatch batch;
	private Sprite texture;

	private World world;
	private Body body;

	private int view = 3;

	public Passager(SpriteBatch batch, Sprite texture, World world) {
		this.batch = batch;
		this.world = world;
		this.texture = texture;

		fuel = new Fuel(view);
	}

	private boolean notDel = true;

	public void delTexture() {
		notDel = !notDel;
	}

	public void createModel(int x, int y) {
		createPhysics(x, y);
	}

	private void createPhysics(int x, int y) {
		body = Box2DHelper.makeBoxAroundSpriteStatic(world, texture, this);
		Box2DHelper.setTransform(body, x, y, 0);
		body.setFixedRotation(true);
	}

	@SuppressWarnings("unused")
	private float x;

	public void render() {
		Vector2 pos = Box2DHelper.getPosition(body);
		if (notDel)
			batch.draw(texture, pos.x - texture.getWidth() / 2, pos.y - texture.getHeight() / 2);
		x = pos.x;
	}

	public void setPos(int x, int y) {
		Box2DHelper.setTransform(body, x, y, 0);
	}

	public Vector2 getPosition() {
		return Box2DHelper.getPosition(body);
	}

	public int getFuel() {
		int value = fuel.getFuel(1);
		if (fuel.isBroken()) {
			notDel = false;
			
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					try {
						world.destroyBody(body);
					} catch (java.lang.NullPointerException e) {
						Gdx.app.log("Error: ", e.getMessage());
					}
				}
			});			
		}
		return value;
	}

	public boolean isBroken() {
		return fuel.isBroken();
	}
}
