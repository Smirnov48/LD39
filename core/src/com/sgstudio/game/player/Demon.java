package com.sgstudio.game.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class Demon {

	private float speed;
	private float speedUp;
	private Train train;
	private SpriteBatch batch;
	private int way;
	private float disToTrain;
	private static long startTime;
	private static float time = 0;
	private Main main;
	
	public Demon(Main main,SpriteBatch batch,Train train) {
		this.main = main;
		Demon.startTime = System.currentTimeMillis();
		this.batch = batch;
		this.train = train;
		speed = 16;
		speedUp = 0.05f;
		way = train.getWay();
		disToTrain = 100 + train.getDistance();
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
			if (disToTrain < 0) {
				main.setScreen(main.defeat);
			}
		}
	}

	
	public void dispose() {
		
	}
}