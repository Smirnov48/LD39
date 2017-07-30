package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyManager {
	public boolean getPressedLeft(){
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) return true;
		else return false;
	}
	public boolean getPressedRight(){
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) return true;
		else return false;
	}
	public boolean getPressedUp(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) return true;
		else return false;
	}
	public boolean getPressedDown(){
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) return true;
		else return false;
	}
	public boolean getPressedEscape(){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) return true;
		else return false;
	}
	public boolean getPressedI(){
		if(Gdx.input.isKeyPressed(Keys.I)) return true;
		else return false;
	}
	public boolean getPressedSpace(){
		if(Gdx.input.isKeyPressed(Keys.SPACE)) return true;
		else return false;
	}
	public boolean getPressedE() {
		if(Gdx.input.isKeyPressed(Keys.E)) return true;
		else return false;
	}
}
