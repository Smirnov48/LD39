package com.sgstudio.game.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.MyGame;

public class BerryBush {

	private int x;
	private int y;

	private Texture img;
	private SpriteBatch batch;

	private int food;
	private int sticks;
	private int health;

	public BerryBush(SpriteBatch batch) {
		img = new Texture("tree.jpg");
		this.batch = MyGame.getBatch();

		x = (int) (10 + (400 * Math.random()));
		y = (int) (10 + (50 * Math.random()));

		health = (int) (10000 + (5000 * Math.random()));
		food = 100;
		sticks = 10;
	}

	public void update() {
		health -= 1;
		food = +1;
		sticks = +1;
	}

	public void render() {
		batch.draw(img, x, y);
	}

	public void dispose() {
		img.dispose();
	}

}
