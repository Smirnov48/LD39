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
	
	public Demon(SpriteBatch batch,Train train) {
		this.batch = batch;
		this.train = train;
		speed = 30;
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
		speed+=speedUp;
		disToTrain += speed;
		disToTrain -= train.getSpeed();
		System.out.println("Demon in " + disToTrain + " meters.");
	}
	
	public void dispose() {
		
	}
}