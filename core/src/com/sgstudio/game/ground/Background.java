package com.sgstudio.game.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.train.Train;

public class Background {
	
	private float speed = 0;
	private Sprite bg[] = {new Sprite(new Texture("atlas/bgR.png")), new Sprite(new Texture("atlas/bgG.png")), new Sprite(new Texture("atlas/bgB.png"))};
	private SpriteBatch batch;
	private Train train;
	
	public Background(SpriteBatch batch, Train train){
		this.batch = batch;
		this.train = train;
		
		bg[0].setX(0);
		bg[1].setX(bg[1].getRegionWidth());
		bg[2].setX(bg[2].getRegionWidth()*2);
	}
	
	public void render() {
		for(int i=0;i<3;i++) bg[i].draw(batch);
		for(int i=0;i<3;i++) bg[i].setX(bg[i].getX()-speed);
		
		if(bg[0].getX()<=-800){
			bg[0].setX(bg[2].getX()+bg[2].getWidth());
		}
		if(bg[1].getX()<=-800){
			bg[1].setX(bg[0].getX()+bg[0].getWidth());
		}
		if(bg[2].getX()<=-800){
			bg[2].setX(bg[1].getX()+bg[1].getWidth());
		}
	}

	public void update() {
		speed = train.getSpeed()/1.5f;		
	}
	
}
