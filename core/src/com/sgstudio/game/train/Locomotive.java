package com.sgstudio.game.train;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.main.Main;
import com.sgstudio.utils.Box2DHelper;

public class Locomotive {
	ParticleEffect p = new ParticleEffect();

	/*Wood*/
	private int ovenWood;//Oven	
	private int wood;    //Hero
	private int maxOvenWood; //Max Oven
	private boolean ovenFire; // Oven Stade

	/*Kinematic*/
	private float speed;
	private float speedUp;
	private int distance = 0;//Passed Distance
	private final int allDistance = 4000; //Full distance
	
	//Time values
	private long startTime;
	private float time = 0;

	private SpriteBatch batch;
	private World world;
	private Sprite sprite;
	private Body body;
	@SuppressWarnings("unused")
	private MainHero hero;
	private boolean first = true;

	private Main main;

	@SuppressWarnings("static-access")
	public Locomotive(Main main,SpriteBatch batch, World world) {
		this.main = main;
		this.batch = batch;
		this.world = world;

		//Wood and Oven stats
		ovenWood = 20;
		maxOvenWood = 300;
		wood = 100;
		ovenFire = true;

		//SetStartTime
		startTime = System.currentTimeMillis();
		
		//Speed
		speed = 13;
		speedUp = 0;
		
		//Graphics
		Texture img = new Texture("train1.png");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
		
		//Physics
		createPhysics();
		//p.load(Gdx.files.internal("particle/smoke"), Gdx.files.internal(""));
	}

	/*Box2D*/
	private void createPhysics() {
		Vector2 size = new Vector2(sprite.getWidth() / 2, 13);
		Vector2 pos = new Vector2(0, 200);
		body = Box2DHelper.makeBox(world, size, pos, this);
		size = new Vector2(sprite.getWidth() / 2 - 30, 5);
		pos = new Vector2(0, 316);
		Box2DHelper.addShapeBox(body, size, pos, 0f, this, false);
		size = new Vector2(sprite.getWidth() / 2 - 40, 50);
		pos = new Vector2(0, 256);
		Box2DHelper.addShapeBox(body, size, pos, 0f, this, false);
		size = new Vector2(15, 10);
		pos = new Vector2(-170, 236);
		Box2DHelper.addShapeBox(body, size, pos, 0f, this, false);
		Box2DHelper.setTransform(body, 590, -165, 0);
	}

	//Getters
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

	/* Update and Change methods*/
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
			distance = 0;
		} else if (distance + i >= allDistance) {
			distance = allDistance + 10;
		} else {
			distance += i;
		}
	}

	/**/
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
	
	public void update() {
		if(first) {
			startTime = System.currentTimeMillis();
			first = false;
		}
		updateOven();
	}

	private void updateOven() {
		if (time != (System.currentTimeMillis() - startTime) / 1000) {
			time++;
			updDistance((int) (speed));
			if (getOvenWood() > 0) {
				speedUp += 0.01;
				updSpeed(speedUp);
				updOvenWood(-1);
				ovenFire = true;
			} else {
			ovenFire = false;
			speedUp = 0;
			updSpeed(-2f);
			}
		}
	}

	public void render() {
			
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y  - sprite.getHeight() / 2 + 285);
	}

	public float getY() {
		return body.getPosition().y;
	}

	public float getX() {
		return body.getPosition().x;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void reset() {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		Array<Fixture> array = body.getFixtureList();
		for (int i = 0; i < array.size; i++) {
			Filter filter = new Filter();
			filter.maskBits = 0;
			array.get(i).setFilterData(filter);
		}
		main.setScreen(main.defeat);
	}
}