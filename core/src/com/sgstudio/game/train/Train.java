package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

	public Train(SpriteBatch batch) {
		this.batch = batch;
		
		ovenWood = 100;
		maxOvenWood = 300;
		wood = 1000;
		System.out.println("Train has been created.");
		ovenFire = true;
		Train.startTime = System.currentTimeMillis();
		speed = 10;
		speedUp = 0;
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
		//		
	}
}