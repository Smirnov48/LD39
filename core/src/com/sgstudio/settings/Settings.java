package com.sgstudio.settings;

public class Settings {
	private int width = 0;
	private int height = 0;
	private int playingMusic = 0;
	
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	public int getPlayingMusic(){ return playingMusic; }
	
	public void setWidth(int width){ this.width = width; }
	public void setHeight(int height){ this.height = height; }
	public void setPlayingMusic(int playingMusic){ this.playingMusic = playingMusic; }
}
