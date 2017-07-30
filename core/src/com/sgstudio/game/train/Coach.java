package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Coach {
	private static int quantity = 10;

	private Train train;
	private static SpriteBatch batch;
	private static Texture coach;

	public void render() {
		for (int i = 0; i < quantity; i++)
			batch.draw(coach, train.getX() - (coach.getWidth() * i) - train.getSprite().getWidth() / 4, 18);
	}

	public Coach(SpriteBatch batch) {
		train = new Train();
		coach = new Texture(Gdx.files.internal("atlas/coach.png"));

		Coach.batch = batch;
	}
}
