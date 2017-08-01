package com.sgstudio.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.game.player.Demon;

public class MiniMap {
	private Locomotive train;
	private SpriteBatch batch;
	private Texture imgRails;
	private Texture imgTrain;
	private Texture imgDemon;

	public MiniMap(SpriteBatch batch, Locomotive train) {
		this.batch = batch;
		this.train = train;

		imgDemon = new Texture("atlas/monstr.png");
		imgRails = new Texture("minimap/rails.png");
		imgTrain = new Texture("minimap/train.png");
	}

	public void render() {
		batch.draw(imgRails, 150, 570, 500, 8);
		if (train.getDistance() < train.getWay()) {
			batch.draw(imgTrain, (float) (150 + (float) (0.125 * train.getDistance())), 575, 24, 24);
		} else {
			batch.draw(imgTrain, 650, 575, 24, 24);
		}
		batch.draw(imgDemon, (float) (125 + (float) (0.125 * (Demon.x))), 575, 24, 24);
	}

	public void dispose() {
		imgRails.dispose();
		imgTrain.dispose();
	}
}
