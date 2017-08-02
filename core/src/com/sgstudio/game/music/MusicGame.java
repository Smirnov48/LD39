package com.sgstudio.game.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicGame {
	private static Music One = null; 
	private static Music Two = null; 
	private static Music Three = null;
	public static boolean stade = true;
	
	public MusicGame(){
		 
//		One = Gdx.audio.newMusic(Gdx.files.internal("audio/music/BlueCoach.ogg"));
//		One.setVolume(0);
//		Two = Gdx.audio.newMusic(Gdx.files.internal("audio/music/ATrainyFromRomashkovo.ogg"));
//		Two.setVolume(0);
		if (Three == null) {
			Three = Gdx.audio.newMusic(Gdx.files.internal("audio/music/LastSong.ogg"));
			Three.setVolume(0);
		}
	}
	
//	public void setMusicOne(){
//		One.play();
//		Two.stop();
//		Three.stop();
//	}
//	public void setMusicTwo(){
//		One.stop();
//		Two.play();
//		Three.stop();
//	}
	public void setMusicThree(){
//		One.stop();
//		Two.stop();
		Three.play();
	}
	
	public void update(){
		if(!isMuted()){
			music();
		}
	}
	
	private void music(){
//		if(One.isPlaying()){
//			if(One.getPosition()<.6f) One.setVolume(One.getVolume()+.005f);
//			else if(One.getPosition()>=27f) One.setVolume(One.getVolume()-.005f);
//			System.out.println("[One] Position: " + One.getPosition() + "  Time:" + One.getVolume());
//		} 
//		else if(Two.isPlaying()){
//			if(Two.getPosition()<.6f) Two.setVolume(Two.getVolume()+.005f);
//			else if(Two.getPosition()>=29f) Two.setVolume(Two.getVolume()-.005f);
//			System.out.println("[Two] Position: " + Two.getPosition() + "  Time:" + Two.getVolume());
//		} 
		if(Three.isPlaying()){
			if(Three.getPosition() < 1.6f) {
				if (Three.getVolume() < 0.5) {
					Three.setVolume(Three.getVolume()+.005f);
				}
			}				
			else if(Three.getPosition() >= 107f) {
				if (Three.getVolume() > 0.5) {
					Three.setVolume(Three.getVolume()-.005f);
				}
			}
			//System.out.println("[Three] Position: " + Three.getPosition() + "  Time:" + Three.getVolume() + "Volume:" + Three.getVolume());
		}
		
//		if(One.getPosition()>=25.5f){
//			One.stop();
//			Three.play();
//		}
//		if(Two.getPosition()>=29.5f){
//			Two.stop();
//			One.play();
//		}
		if(Three.getPosition()>=108.5f){
			Three.stop();
			Three.play();
		}
	}
	
	public void stopMusic() {
//		if(One.isPlaying()){
//			if(One.getPosition()>=27f) One.setVolume(One.getVolume()-.005f);
//		} 
//		else if(Two.isPlaying()){
//			if(Two.getPosition()>=29f) Two.setVolume(Two.getVolume()-.005f);
//		} 
		if(Three.isPlaying()){
			if(Three.getPosition()>=29f) Three.setVolume(Three.getVolume()-.005f);
		}
	}
	
	public void stop() {
//		if(One.isPlaying()){
//			One.stop();
//		} 
//		else if(Two.isPlaying()){
//			Two.stop();
//		} 
		if(Three.isPlaying()){
			Three.stop();
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