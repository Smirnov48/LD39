package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.MyGame;
import com.sgstudio.utils.Box2DHelper;

public class Train {

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
		
		Texture img = new Texture("train.png");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
		createPhysics();
	}

	private void createPhysics() {
		body = Box2DHelper.makeBox(world, new Vector2(sprite.getWidth(), 10));
		body.getPosition().x = sprite.getX();
		body.getPosition().y = sprite.getY() + sprite.getHeight()- 10;
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

	public boolean onFire() {
		return ovenFire;
	}

	public float getSpeed() {
		return speed;
	}

	public float getSpeedUp() {
		return speedUp;
	}

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

	public void changeOven() {
		if (ovenFire) {
			ovenFire = false;
		} else {
			ovenFire = true;
		}
	}

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
		batch.draw(sprite, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
	}
}