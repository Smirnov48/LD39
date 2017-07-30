package com.sgstudio.game.ground;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.train.Train;
import com.sgstudio.utils.Box2DHelper;
import com.sgstudio.utils.Tiles;

public class Rails {
	private Tiles tiles;
	Texture imgRails;
	SpriteBatch batch;
	int railsX;
	final int railsY = 30;
	private Train train;
	private float speed = 0;
	
	private Sprite bgRails[] = {new Sprite(new Texture("rails1.png")), new Sprite(new Texture("rails1.png")), new Sprite(new Texture("rails1.png"))};
	private Sprite front[] = {new Sprite(), new Sprite(),new Sprite()};
	private Map<String, TextureRegion> atlasFront;
	
	public Rails(World world,SpriteBatch batch,Train train) {
		tiles = new Tiles();
		tiles.createAtlas("atlas/front.png", 3, 1);
		atlasFront = tiles.getTextureRegion();
		for(int i=0;i<3;i++) front[i] = new Sprite(atlasFront.get("tiles0_"+i));
		this.train = train;
		
		Body ground = Box2DHelper.makeBox(world, new Vector2(Gdx.graphics.getWidth(), 68));
		ground.setType(BodyDef.BodyType.StaticBody);
		
		this.batch = batch;
		bgRails[0].setX(0);
		bgRails[1].setX(bgRails[1].getRegionWidth());
		bgRails[2].setX(bgRails[2].getRegionWidth()*2);
		for(int i = 0; i < 3; i++)bgRails[i].setY(railsY);
	}
	
	public void render() {
		for(int i = 0;i < 3; i++) bgRails[i].draw(batch);
		for(int i=0;i<3;i++) bgRails[i].setX(bgRails[i].getX()-speed);
		for(int i=0;i<3;i++) front[i].setX(bgRails[i].getX()-speed*1.5f);
		for(int i=0;i<3;i++) front[i].draw(batch);
		
		if(bgRails[0].getX()<=-800){
			bgRails[0].setX(bgRails[2].getX()+bgRails[2].getWidth());
		}
		if(bgRails[1].getX()<=-800){
			bgRails[1].setX(bgRails[0].getX()+bgRails[0].getWidth());
		}
		if(bgRails[2].getX()<=-800){
			bgRails[2].setX(bgRails[1].getX()+bgRails[1].getWidth());
		}
	}
	
	public void update() {
		speed = train.getSpeed()/1.3f;		
	}
	
	public void dispose() {
	}

}
