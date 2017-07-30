package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.main.Main;
import com.sgstudio.utils.Box2DHelper;

public class Locomotive {
	ParticleEffect p = new ParticleEffect();

	private int ovenWood;//Wood in Oven	
	private int wood;    //Wood in Hero
	private static int maxOvenWood; //MaxWood of Oven
	private boolean ovenFire; // isOvenFire

	private float speed;//Train Speed
	private float speedUp;//Train SpeedUp
	private static int distance = 0;//Distance during game
	private final int allDistance = 4000; //Full distance
	
	//Time values
	private static long startTime;
	private static float time = 0;

	private static SpriteBatch batch;
	private static World world;
	private static Sprite sprite;
	private static Body body;
	private static MainHero hero;
	
	private Main main;

	public Locomotive(Main main,SpriteBatch batch, World world) {
		this.main = main;
		this.batch = batch;
		this.world = world;

		//Wood and Oven stats
		ovenWood = 100;
		maxOvenWood = 300;
		wood = 100;
		ovenFire = true;

		//SetStartTime
		Locomotive.startTime = System.currentTimeMillis();
		
		//Speed
		speed = 23;
		speedUp = 0;
		
		//Graphics
		Texture img = new Texture("train1.png");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
		
		//Physics
		createPhysics();
		p.load(Gdx.files.internal("particle/smoke"), Gdx.files.internal(""));
	}

	public Locomotive() { }

	private void createPhysics() {
		Vector2 size = new Vector2(sprite.getWidth() / 2, 12);
		Vector2 pos = new Vector2(0, 200);
		body = Box2DHelper.makeBox(world, size, pos);
		size = new Vector2(sprite.getWidth() / 2 - 30, 5);
		pos = new Vector2(0, 316);
		Box2DHelper.addShapeBox(body, size, pos);
		size = new Vector2(sprite.getWidth() / 2 - 40, 50);
		pos = new Vector2(0, 256);
		Box2DHelper.addShapeBox(body, size, pos);
		size = new Vector2(15, 10);
		pos = new Vector2(-180, 236);
		Box2DHelper.addShapeBox(body, size, pos);
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
			distance = 0;
		} else if (distance + i >= allDistance) {
			distance = allDistance;
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
	
	public void update() {
		updateOven();
		updateStand();
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
	
	private void updateStand() {
		if(getDistance() >= getWay()) {
			main.setScreen(main.victory);
		}
	}

	public void render() {
		p.setPosition(250, 250);
		p.update(Gdx.graphics.getDeltaTime());
		p.draw(batch);
			
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
}