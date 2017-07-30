package com.sgstudio.game.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Train;

public class Demon {

	private float speed;
	private float speedUp;
	private Train train;
	private SpriteBatch batch;
	private int way;
	private float disToTrain;
	private static long startTime;
	private static float time = 0;
	
	public Demon(SpriteBatch batch,Train train) {
		Demon.startTime = System.currentTimeMillis();
		this.batch = batch;
		this.train = train;
		speed = 100;
		speedUp = 0.03f;
		way = train.getWay();
		disToTrain = way - train.getDistance();
	}
	
	public void render() {
		
	}
	
	public void update() {
		kinematic();
	}
	
	public void kinematic() {
		if (time != (System.currentTimeMillis() - startTime) / 1000) {
			time++;
			speed+=speedUp;
			disToTrain -= speed;
			disToTrain += train.getSpeed();
			System.out.println("Demon in " + disToTrain + " meters.");
		}
	}

	
	public void dispose() {
		
	}
}