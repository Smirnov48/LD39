package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Passenger {
	
	private SpriteBatch batch;
	Texture tex = new Texture("people.png");
	Sprite[] sprite = {new Sprite(tex, 0, 47, 155, 300),new Sprite(tex, 175, 47, 155, 300),new Sprite(tex, 350, 47, 155, 300)};
	
	
	public Passenger(SpriteBatch batch) {
		this.batch = batch;
		for(int i = 0; i < 3; i++) {
			sprite[i].setBounds(-30 + 40 * i, 35, 32, 64);
		}
	}
	
	public void render() {
		for(int i = 0; i < 3; i++) {
			sprite[i].draw(batch);
		}
	}
	
	public void dispose() {
		tex.dispose();
	}
}