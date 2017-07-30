package com.sgstudio.game.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicGame {
	private Music One, Two;
	
	public MusicGame(){
		One = Gdx.audio.newMusic(Gdx.files.internal("audio/music/ATrainyFromRomashkovo.wav"));
		One.stop();
		One.setVolume(0);
		Two = Gdx.audio.newMusic(Gdx.files.internal("audio/music/BlueCoach.wav"));
		Two.play();
		Two.setVolume(0);
	}
	
	public void update(){
		music();
	}
	
	private void music(){
		if(One.isPlaying()){
			if(One.getPosition()<.6f) One.setVolume(One.getVolume()+.005f);
			else if(One.getPosition()>=27f) One.setVolume(One.getVolume()-.005f);
//			System.out.println("[One] Position: " + One.getPosition() + "  Time:" + One.getVolume());
		} else if(Two.isPlaying()){
			if(Two.getPosition()<.6f) Two.setVolume(Two.getVolume()+.005f);
			else if(Two.getPosition()>=29f) Two.setVolume(Two.getVolume()-.005f);
//			System.out.println("[Two] Position: " + Two.getPosition() + "  Time:" + Two.getVolume());
		}
		
		if(One.getPosition()>=25.5f){
			One.stop();
			Two.play();
		}
		if(Two.getPosition()>=29.5f){
			Two.stop();
			One.play();
		}
	}
}
