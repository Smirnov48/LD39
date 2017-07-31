package com.sgstudio.game.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicGame {
	private Music One, Two;
	public static boolean stade = true;
	
	public MusicGame(){
		One = Gdx.audio.newMusic(Gdx.files.internal("audio/music/ATrainyFromRomashkovo.wav"));
		One.play();
		One.setVolume(0);
		Two = Gdx.audio.newMusic(Gdx.files.internal("audio/music/BlueCoach.wav"));
		Two.stop();
		Two.setVolume(0);
	}
	
	public void update(){
		if(!isMuted()){
			music();
		}
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
	
	public void stopMusic() {
		if(One.isPlaying()){
			if(One.getPosition()>=27f) One.setVolume(One.getVolume()-.005f);
		} else if(Two.isPlaying()){
			if(Two.getPosition()>=29f) Two.setVolume(Two.getVolume()-.005f);
		}
		stade = false;
	}
	
	public void stop() {
		if(One.isPlaying()){
			One.stop();
		} else if(Two.isPlaying()){
			One.stop();
		}
	}
	
	public void musicStade() {
		if(stade) {
			stade = false;
		} else {
			stade = true;
		}
	}
	
	public void setMuted(boolean muted){ stade = muted; }

	public boolean isMuted() {
		if(!stade) {return true;}
		else {return false;}
	}
}