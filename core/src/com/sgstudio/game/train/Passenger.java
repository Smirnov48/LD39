package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Passenger {
	final int S = 800;
	int x;
	int cont = 0;
	int con = 800;
	int cont1 = 0;
	int con1 = 800; 
	private SpriteBatch batch;
	Texture tex = new Texture("people.png");
	Sprite[] sprite = {new Sprite(tex, 0, 47, 155, 300),new Sprite(tex, 175, 47, 155, 300),new Sprite(tex, 350, 47, 155, 300),
			new Sprite(tex, 0, 47, 155, 300),new Sprite(tex, 175, 47, 155, 300),
			new Sprite(tex, 350, 47, 155, 300),new Sprite(tex, 0, 47, 155, 300),
			new Sprite(tex, 175, 47, 155, 300),new Sprite(tex, 350, 47, 155, 300),
			new Sprite(tex, 0, 47, 155, 300),new Sprite(tex, 175, 47, 155, 300)};	
	
	public Passenger(SpriteBatch batch, World world) {
		this.batch = batch;
		for(int i = 0; i < 11; i++) {
			x = 150 + -450 * i;
			sprite[i].setBounds(x, 35, 32, 64);
		}
	}
	
	public void render() {
		for(int i = 0; i < 11; i++) {
			if (i%2 != 0){
				if(sprite[i].getX() + cont != S + sprite[i].getX()){
					sprite[i].setPosition(sprite[i].getX() + 1, sprite[i].getY());
					sprite[i].draw(batch);
					cont++;
				}
				if (cont == 800){
					if(sprite[i].getX() + con >= sprite[i].getX()){
						sprite[i].setPosition(sprite[i].getX() - 1, sprite[i].getY());
						sprite[i].draw(batch);
						con--;
					}
				}
				if (con == 0){
					con = 800;
					cont = 0;
				} 
			} else if ((i%2 == 0) || (i == 0)) {
				if(sprite[i].getX() + con1 > sprite[i].getX()){
					sprite[i].setPosition(sprite[i].getX() - 1, sprite[i].getY());
					sprite[i].draw(batch);
					con1--;
				}
				if (con1 == 0){
					if(sprite[i].getX() + cont1 != S + sprite[i].getX()){
						sprite[i].setPosition(sprite[i].getX() + 1, sprite[i].getY());
						sprite[i].draw(batch);
						cont1++;
					}
				}
				if (cont1 == 800){
					con1 = 800;
					cont1 = 0;
				}
			}
		}
	}
	
	public void dispose() {
		tex.dispose();
	}
}