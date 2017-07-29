package com.sgstudio.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainHero {

	int posX,posY;
	Texture img;
	SpriteBatch batch;
	
	
	public MainHero(SpriteBatch batch){
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
	}
	
	public void render() {
		batch.draw(img,100,100);	
	}
	
	public void dispose() {
		img.dispose();
	}
}
