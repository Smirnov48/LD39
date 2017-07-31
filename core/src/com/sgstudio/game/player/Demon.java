package com.sgstudio.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.main.Main;
import com.sgstudio.utils.Box2DHelper;

public class Demon {

	private float speed;
	private Locomotive train;
	private SpriteBatch batch;
	private int way;
	private static float disToTrain;
	private static long startTime;
	private static float time = 0;
	private Main main;
	private Texture patato;
	private float x;
	private World world;
	private Sprite sprite;
	private Body body;

	public Demon(Main main, SpriteBatch batch, Locomotive train, World world) {
		this.main = main;
		this.world = world;

		Demon.startTime = System.currentTimeMillis();
		this.batch = batch;
		this.train = train;
		speed = 35;
		// speedUp = 0.05f;
		way = train.getWay();
		disToTrain = 1000 + train.getDistance();
		patato = new Texture("atlas/monstr.png");
		sprite = new Sprite(patato);
		x = -6500 + disToTrain - sprite.getWidth();
		createPhysics();
	}

	private void createPhysics() {
		body = Box2DHelper.makeCircleAroundSprite(world, sprite, "Monstr");
		body.setType(BodyDef.BodyType.KinematicBody);
		Box2DHelper.setTransform(body, (int) x, (int) sprite.getWidth() / 2 + 15, 0);
		body.setAngularVelocity(-6);
	}

	public void render() {
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y - sprite.getHeight() / 2, sprite.getWidth() / 2, sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight(), 1, 1, MathUtils.radiansToDegrees * body.getAngle());
	}

	public void update() {
		kinematic();
		System.out.println(disToTrain);
		Box2DHelper.setTransform(body, (int) -disToTrain - 500, (int) sprite.getWidth() / 2 + 15, body.getAngle());
	}

	public void kinematic() {
		if (time != (System.currentTimeMillis() - startTime) / 1000) {
			time++;
			speed += speed / 100 * 1;
			disToTrain -= speed;
			disToTrain += train.getSpeed();
			if (disToTrain < 0) {
				main.setScreen(main.defeat);
			}
		}
	}

	public float getDemonX() {
		return x;
	}

	public float getDemonWidth() {
		return patato.getWidth();
	}

	public float getDisToTrain() {
		return disToTrain;
	}

	public void dispose() {

	}
}