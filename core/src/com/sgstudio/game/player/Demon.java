package com.sgstudio.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.main.Main;

public class Demon {

	private float speed;
	private Locomotive train;
	private SpriteBatch batch;
	private int way;
	private static float disToTrain;
	private static long startTime;
	private static float time = 0;
	private Main main;
	private Texture dark;
	private float x;
	
	public Demon(Main main,SpriteBatch batch,Locomotive train) {
		this.main = main;
		Demon.startTime = System.currentTimeMillis();
		this.batch = batch;
		this.train = train;
		speed = 35;
		//speedUp = 0.05f;
		way = train.getWay();
		disToTrain = 1000 + train.getDistance();
		dark = new Texture("atlas/monstr.png");
		x = -6500+disToTrain-dark.getWidth();
	}
	
	public void render() {
		batch.draw(dark,x, 0);
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
	
	public float getDemonX() {
		return x;
	}
	
	public float getDemonWidth() {
		return dark.getWidth();
	}
	
	public float getDisToTrain() {
		return disToTrain;
	}

	
	public void dispose() {
		
	}
}