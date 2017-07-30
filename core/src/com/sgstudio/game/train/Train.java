package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.utils.Box2DHelper;

public class Train {
	ParticleEffect p = new ParticleEffect();

	private int ovenWood;
	private int wood;
	private static int maxOvenWood;
	private boolean ovenFire;

	private float speed;
	private float speedUp;
	private static long startTime;
	private static float time = 0;

	private SpriteBatch batch;
	private World world;
	private Sprite sprite;
	private Body body;
	private int distance = 0;
	private final int allDistance = 40000;

	public Train(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;

		ovenWood = 100;
		maxOvenWood = 300;
		wood = 1000;
		ovenFire = true;

		Train.startTime = System.currentTimeMillis();
		speed = 10;
		speedUp = 0;

		Texture img = new Texture("train1.png");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
		createPhysics();

		p.load(Gdx.files.internal("particle/smoke"), Gdx.files.internal(""));
	}

	private void createPhysics() {
		body = Box2DHelper.makeBoxAroundSprite(world, sprite);
		Box2DHelper.setTransform(body, 590, 165, 0);
	}

	public int getTrainWood() {
		return wood;
	}

	public int getMaxOvenWood() {
		return maxOvenWood;
	}

	public int getOvenWood() {
		return ovenWood;
	}

	public float getSpeed() {
		return speed;
	}

	public float getSpeedUp() {
		return speedUp;
	}

	public int getDistance() {
		return distance;
	}

	public int getWay() {
		return allDistance;
	}

	// Boolean for ovenWood is not 0.
	public boolean onFire() {
		return ovenFire;
	}

	// Update and Change methods
	public void updOvenWood(int i) {
		if (ovenWood + i <= 0) {
			ovenWood = 0;
		} else if (ovenWood + i > maxOvenWood) {
			ovenWood = maxOvenWood;
		} else if (ovenWood + i > 0) {
			ovenWood += i;
		}
	}

	public void updSpeed(float i) {
		if (speed + i <= 0) {
			speed = 0;
		} else if (speed + i > 0) {
			speed += i;
		} else {
			speed += i;
		}
	}

	public void updDistance(int i) {
		if (distance + i <= 0) {
			// Win!!
			distance = 0;
		} else if (distance + i >= allDistance) {
			System.out.println("Error!");
		} else {
			distance += i;
		}
	}

	public void changeOven() {
		if (ovenFire) {
			ovenFire = false;
		} else {
			ovenFire = true;
		}
	}

	// Setters
	public void setTrainWood(int i) {
		if (i < maxOvenWood) {
			wood = maxOvenWood;
		} else if (i < 0) {
			wood = 0;
		}
	}

	public void setMaxOvenWood(int i) {
		maxOvenWood = i;
	}

	private void updateOven() {
		if (time != (System.currentTimeMillis() - startTime) / 1000) {
			time++;
			if (getOvenWood() > 0) {
				speedUp += 0.01;
				updSpeed(speedUp);
				updDistance((int) (speed));
				System.out.println(distance);
				updOvenWood(-1);
				ovenFire = true;
			} else {
				ovenFire = false;
				speedUp = 0;
				updSpeed(-0.8f);
			}
		}
	}

	public void update() {
		updateOven();
	}

	public void render() {
		p.setPosition(250, 250);
		p.update(Gdx.graphics.getDeltaTime());
		p.draw(batch);
			
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y  - sprite.getHeight() / 2);
	}

	public float getY() {
		return body.getPosition().y;
	}

	public float getX() {
		return body.getPosition().x;
	}
}