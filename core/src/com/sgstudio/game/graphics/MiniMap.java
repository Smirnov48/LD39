package com.sgstudio.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Train;

public class MiniMap {
	private Train train;
	private SpriteBatch batch;
	private Texture imgRails;
	private Texture imgTrain;

	public MiniMap(SpriteBatch batch,Train train){
		this.batch = batch;
		this.train = train;
		
		imgRails = new Texture("minimap/rails.png");
		imgTrain = new Texture("minimap/train.png");
	}
	
	public void render() {
		batch.draw(imgRails, 150, 570, 500, 8);
		if(train.getDistance() < train.getWay()) {
			batch.draw(imgTrain, (float) (150 + (float)(0.125 * train.getDistance())),575,24,24);
		} else {
			batch.draw(imgTrain, 650 ,575,24,24);
		}
	}
	
	public void dispose() {
		imgRails.dispose();
		imgTrain.dispose();
	}
}
