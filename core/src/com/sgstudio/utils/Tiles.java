package com.sgstudio.utils;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tiles {
	private Texture texture = null;
	private int Lines = 0;
	private int Columns = 0;
	
	private static Map<String, TextureRegion> textureRegions = null;
	
	public void createAtlas(String tiles, int lines, int columns){
		texture = new Texture(tiles);
		textureRegions = null;
		textureRegions = new HashMap<String, TextureRegion>();
		Lines = lines;
		Columns = columns;
		createAtlas();
	}
	
	private void createAtlas(){
		TextureRegion tmp[][] = TextureRegion.split(texture, texture.getWidth()/Lines, texture.getHeight()/Columns);
		for(int y=0;y<Columns;y++) for(int x=0;x<Lines;x++) textureRegions.put("tiles"+y+"_"+x, tmp[y][x]);
	}
	
	public Map<String, TextureRegion> getTextureRegion(){ return textureRegions; }
}
