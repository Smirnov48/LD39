package com.sgstudio.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Save {
	static Preferences pref;
	static BitmapFont font;
	static SpriteBatch batch;
	
	public void preferences(String Preferences){
		pref = Gdx.app.getPreferences(Preferences);
	}
	public void Resolution(float width, float height){
		pref.putFloat("width", width);
		pref.putFloat("height", height);
	}
	
	public float getFloat(String key){
		return pref.getFloat(key);
	}
	
	public int getInt(String key){
		return pref.getInteger(key);
	}
	
	public void flush(){ pref.flush(); }
}
