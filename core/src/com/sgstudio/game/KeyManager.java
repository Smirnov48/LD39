package com.sgstudio.game;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyManager {
	static boolean getPressedLeft(int key){
		return Keys.LEFT == key;
	}
	static boolean getPressedRight(int key){
		return Keys.RIGHT == key;
	}
	static boolean getPressedUp(int key){
		return Keys.UP == key;
	}
	static boolean getPressedDown(int key){
		return Keys.DOWN == key;
	}
	static boolean getPressedA(int key){
		return Keys.A == key;
	}
	static boolean getPressedW(int key){
		return Keys.W == key;
	}
	static boolean getPressedD(int key){
		return Keys.D == key;
	}
	static boolean getPressedS(int key){
		return Keys.S == key;
	} 
	static boolean getPressedEscape(int key){
		return Keys.ESCAPE == key;
	}
	static boolean getPressedI(int key){
		return Keys.I == key;
	}
}
