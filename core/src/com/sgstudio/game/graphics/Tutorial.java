package com.sgstudio.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial {
	
	private SpriteBatch batch;
	Texture tut;

	public Tutorial(SpriteBatch batch) {
		this.batch = batch;
		tut = new Texture("tutorial.png");
	}
	
	public void render() {
		batch.draw(tut, 0, 0);
	}
	
	public void dispose() {
		tut.dispose();
	}
}
