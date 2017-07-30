package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.utils.Box2DHelper;

public class Coach {

	private SpriteBatch batch;
	private Texture img;
	private Sprite sprite;

	private World world;
	private Body body;

	public Coach(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;

		img = new Texture(Gdx.files.internal("atlas/coach.png"));
		sprite = new Sprite(img);

		createPhysics();
	}

	private void createPhysics() {
		Vector2 size = new Vector2(sprite.getWidth() / 2, 12);
		Vector2 pos = new Vector2(0, 200);
		body = Box2DHelper.makeBox(world, size, pos);

		size = new Vector2(sprite.getWidth() / 2 - 30, 5);
		pos = new Vector2(0, 316);
		Box2DHelper.addShapeBox(body, size, pos);
		Box2DHelper.setTransform(body, 132, -170, 0);
	}

	public void render() {
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y + sprite.getHeight() + 10);
	}

}
