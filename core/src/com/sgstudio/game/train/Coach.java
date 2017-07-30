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

	private Train train;
	private World world;
	private Body body;

	public Coach(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;

		train = new Train();
		img = new Texture(Gdx.files.internal("atlas/coach.png"));
		sprite = new Sprite(img);

		createPhysics();
	}

	private void createPhysics() {
		body = Box2DHelper.makeBoxAroundSprite(world, sprite);
		Box2DHelper.setTransform(body, (int) (train.getX() + 132), 18, 0);
	}

	public void render() {
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y - sprite.getHeight() / 2);
	}

}
