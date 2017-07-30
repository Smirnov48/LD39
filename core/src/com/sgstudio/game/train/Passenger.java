package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Passenger {
	
	private SpriteBatch batch;
	Texture tex;
	Sprite sprite;
	
	public Passenger(SpriteBatch batch) {
		this.batch = batch;
		tex = new Texture("people.png");
		sprite = new Sprite(tex, 0, 47, 155, 300);
		sprite.setPosition(10, 10);
		sprite.setBounds(10, 35, 32, 64);
	}
	
	public void render() {
		sprite.draw(batch);		
	}
	
	public void dispose() {
		tex.dispose();
	}
}