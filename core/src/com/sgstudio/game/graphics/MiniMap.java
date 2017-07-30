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
		batch.draw(imgRails, 150, 0, 500, 8);
		batch.draw(imgTrain, (float) (150 + 0.1 * train.getDistance()),4,24,24);
	}
	
	public void dispose() {
		imgRails.dispose();
		imgTrain.dispose();
	}
}
