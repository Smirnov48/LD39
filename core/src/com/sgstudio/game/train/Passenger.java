package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Passenger {
	final int S = 200;
	int x;
	int cont = 0;
	int con = 200;
	private SpriteBatch batch;
	Texture tex = new Texture("people.png");
	Sprite[] sprite = {new Sprite(tex, 0, 47, 155, 300),new Sprite(tex, 175, 47, 155, 300),new Sprite(tex, 350, 47, 155, 300)};
	
	
	public Passenger(SpriteBatch batch) {
		this.batch = batch;
		for(int i = 0; i < 3; i++) {
			x = -30 + -400 * i;
			sprite[i].setBounds(x, 35, 32, 64);
		}
	}
	
	public void render() {
		for(int i = 0; i < 3; i++) {
			if(sprite[i].getX() + cont != S + sprite[i].getX()){
				sprite[i].setPosition(sprite[i].getX() + 1, sprite[i].getY());
				sprite[i].draw(batch);
				cont++;
			}
			if (cont == 200){
				if(sprite[i].getX() + con >= sprite[i].getX()){
					sprite[i].setPosition(sprite[i].getX() - 1, sprite[i].getY());
					sprite[i].draw(batch);
					con--;
				}
			}
			if (con == 0){
				con = 200;
				cont = 0;
			}
		}
	}
	
	public void dispose() {
		tex.dispose();
	}
}