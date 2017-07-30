package com.sgstudio.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class Demon {

	private float speed;
	private Train train;
	private SpriteBatch batch;
	private int way;
	private float disToTrain;
	private static long startTime;
	private static float time = 0;
	private Main main;
	private Texture dark;
	
	public Demon(Main main,SpriteBatch batch,Train train) {
		this.main = main;
		Demon.startTime = System.currentTimeMillis();
		this.batch = batch;
		this.train = train;
		speed = 20;
		//speedUp = 0.05f;
		way = train.getWay();
		disToTrain = 100 + train.getDistance();
		dark = new Texture("atlas/monstr.png");
	}
	
	public void render() {
		batch.draw(dark, disToTrain-dark.getWidth(), 0);
	}
	
	public void update() {
		kinematic();
	}
	
	public void kinematic() {
		if (time != (System.currentTimeMillis() - startTime) / 1000) {
			time++;
			speed+=speed/100*1;
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