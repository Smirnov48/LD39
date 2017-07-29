package com.sgstudio.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerTree {

	private int x;
	private int y;

	private Texture img;
	private SpriteBatch batch;
	
	private int food;
	private int sticks;
	private int health;

	public PowerTree(SpriteBatch batch) {
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		
		x = 150;
		y = 150;
		
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
