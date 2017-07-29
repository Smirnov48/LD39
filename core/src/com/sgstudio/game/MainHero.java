package com.sgstudio.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainHero {

	int posX,posY;
	static Texture img;
	SpriteBatch batch;
	
	
	public MainHero(){
		img = new Texture("hero.jpg");
		batch = this.batch;
	}
	
	public static void render() {
		batch.begin;
		batch.draw(img,0,0);
		batch.end;
	}
	
	public static void dispose() {
		
	}
}
