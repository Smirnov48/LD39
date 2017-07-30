package com.sgstudio.game.ground;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sgstudio.game.train.Train;
import com.sgstudio.utils.Tiles;

public class Background {
	private Tiles tiles;
	
	private Map<String, TextureRegion> atlasBg1;
	private Map<String, TextureRegion> atlasBg2;
	private Map<String, TextureRegion> atlasBg3;
	private Map<String, TextureRegion> atlasBg4;
	private Map<String, TextureRegion> atlasBg5;
	private Map<String, TextureRegion> atlasBg6;
	
	private float speed = 0;
	private Sprite bg = new Sprite(new Texture("atlas/bg.png"));
	private Sprite bg1[] = {new Sprite(), new Sprite(),new Sprite()};
	private Sprite bg2[] = {new Sprite(), new Sprite(),new Sprite()};;
	private Sprite bg3[] = {new Sprite(), new Sprite(),new Sprite()};;
	private Sprite bg4[] = {new Sprite(), new Sprite(),new Sprite()};;
	private Sprite bg5[] = {new Sprite(), new Sprite(),new Sprite()};;
	private Sprite bg6[] = {new Sprite(), new Sprite(),new Sprite()};;
	private SpriteBatch batch;
	private Train train;
	int x = 0;
	
	public Background(SpriteBatch batch, Train train){
		this.batch = batch;
		this.train = train;
		
		tiles = new Tiles();
		tiles.createAtlas("atlas/bg1.png", 3, 1);
		atlasBg1 = tiles.getTextureRegion();
		tiles.createAtlas("atlas/bg2.png", 3, 1);
		atlasBg2 = tiles.getTextureRegion();
		tiles.createAtlas("atlas/bg3.png", 3, 1);
		atlasBg3 = tiles.getTextureRegion();
		tiles.createAtlas("atlas/bg4.png", 3, 1);
		atlasBg4 = tiles.getTextureRegion();
		tiles.createAtlas("atlas/bg5.png", 3, 1);
		atlasBg5 = tiles.getTextureRegion();
		tiles.createAtlas("atlas/bg6.png", 3, 1);
		atlasBg6 = tiles.getTextureRegion();
		
		for(int i=0;i<3;i++){
			bg1[i] = new Sprite(atlasBg1.get("tiles0_"+i));
			bg2[i] = new Sprite(atlasBg2.get("tiles0_"+i));
			bg3[i] = new Sprite(atlasBg3.get("tiles0_"+i));
			bg4[i] = new Sprite(atlasBg4.get("tiles0_"+i));
			bg5[i] = new Sprite(atlasBg5.get("tiles0_"+i));
			bg6[i] = new Sprite(atlasBg6.get("tiles0_"+i));
		}
		
		bg1[0].setX(x);
		bg1[1].setX(bg1[1].getRegionWidth());
		bg1[2].setX(bg1[2].getRegionWidth()*2);

		bg2[0].setX(x);
		bg2[1].setX(bg2[1].getRegionWidth());
		bg2[2].setX(bg2[2].getRegionWidth()*2);
		
		bg3[0].setX(x);
		bg3[1].setX(bg3[1].getRegionWidth());
		bg3[2].setX(bg3[2].getRegionWidth()*2);
		
		bg4[0].setX(x);
		bg4[1].setX(bg4[1].getRegionWidth());
		bg4[2].setX(bg4[2].getRegionWidth()*2);
		
		bg5[0].setX(x);
		bg5[1].setX(bg5[1].getRegionWidth());
		bg5[2].setX(bg5[2].getRegionWidth()*2);
		
		bg6[0].setX(x);
		bg6[1].setX(bg6[1].getRegionWidth());
		bg6[2].setX(bg6[2].getRegionWidth()*2);
	}

	private void pos(){
		if(bg1[0].getX()<=-800) bg1[0].setX(bg1[2].getX()+bg1[2].getWidth());
		if(bg1[1].getX()<=-800) bg1[1].setX(bg1[0].getX()+bg1[0].getWidth());
		if(bg1[2].getX()<=-800) bg1[2].setX(bg1[1].getX()+bg1[1].getWidth());
		
		if(bg2[0].getX()<=-800) bg2[0].setX(bg2[2].getX()+bg2[2].getWidth());
		if(bg2[1].getX()<=-800) bg2[1].setX(bg2[0].getX()+bg2[0].getWidth());
		if(bg2[2].getX()<=-800) bg2[2].setX(bg2[1].getX()+bg2[1].getWidth());
		
		if(bg3[0].getX()<=-800) bg3[0].setX(bg3[2].getX()+bg3[2].getWidth());
		if(bg3[1].getX()<=-800) bg3[1].setX(bg3[0].getX()+bg3[0].getWidth());
		if(bg3[2].getX()<=-800) bg3[2].setX(bg3[1].getX()+bg3[1].getWidth());
		
		if(bg4[0].getX()<=-800) bg4[0].setX(bg4[2].getX()+bg4[2].getWidth());
		if(bg4[1].getX()<=-800) bg4[1].setX(bg4[0].getX()+bg4[0].getWidth());
		if(bg4[2].getX()<=-800) bg4[2].setX(bg4[1].getX()+bg4[1].getWidth());
		
		if(bg5[0].getX()<=-800) bg5[0].setX(bg5[2].getX()+bg5[2].getWidth());
		if(bg5[1].getX()<=-800) bg5[1].setX(bg5[0].getX()+bg5[0].getWidth());
		if(bg5[2].getX()<=-800) bg5[2].setX(bg5[1].getX()+bg5[1].getWidth());
		
		if(bg6[0].getX()<=-800) bg6[0].setX(bg6[2].getX()+bg6[2].getWidth());
		if(bg6[1].getX()<=-800) bg6[1].setX(bg6[0].getX()+bg6[0].getWidth());
		if(bg6[2].getX()<=-800) bg6[2].setX(bg6[1].getX()+bg6[1].getWidth());
	}
	
	public void render() {
		batch.draw(bg, 0, 0);
		for(int i=0;i<3;i++) bg1[i].draw(batch);
		for(int i=0;i<3;i++) bg1[i].setX(bg1[i].getX()-speed/2f);
		for(int i=0;i<3;i++) bg2[i].draw(batch);
		for(int i=0;i<3;i++) bg2[i].setX(bg2[i].getX()-speed/1.8f);
		for(int i=0;i<3;i++) bg3[i].draw(batch);
		for(int i=0;i<3;i++) bg3[i].setX(bg3[i].getX()-speed/1.6f);
		for(int i=0;i<3;i++) bg4[i].draw(batch);
		for(int i=0;i<3;i++) bg4[i].setX(bg4[i].getX()-speed/1.4f);
		for(int i=0;i<3;i++) bg5[i].draw(batch);
		for(int i=0;i<3;i++) bg5[i].setX(bg5[i].getX()-speed/1.2f);
		for(int i=0;i<3;i++) bg6[i].draw(batch);
		for(int i=0;i<3;i++) bg6[i].setX(bg6[i].getX()-speed);
		
		pos();
	}

	public void update() {
		speed = train.getSpeed()/1.5f;		
	}
	
}
