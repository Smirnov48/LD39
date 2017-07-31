package com.sgstudio.game.player;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sgstudio.game.MyGame;

public class Animator {
	final int FRAME_COLS = 8; 
	final int FRAME_ROWS = 1; 
	 
	Animation walkAnimation; 
	Texture walkSheet; 
	Texture stanwalkSheet; 
	TextureRegion[] walkFrames; 
	SpriteBatch spriteBatch; 
	TextureRegion currentFrame; 
	 
	float stateTime;
	private MainHero mainHero;
	
    public Animator(MainHero mainHero) {
		this.mainHero = mainHero;
		walkSheet = new Texture(Gdx.files.internal("Char_move.png"));
	        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, 
	        		walkSheet.getHeight()/FRAME_ROWS);
	        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
	        int index = 0;
	        for (int i = 0; i < FRAME_ROWS; i++) {
	            for (int j = 0; j < FRAME_COLS; j++) {
	                walkFrames[index++] = tmp[i][j];
	            }
	        }
	        walkAnimation = new Animation(0.025f, walkFrames);
	        spriteBatch = MyGame.getBatch();; 
	        stateTime = 0f;
	        //stanwalkSheet =  new TextureRegion[FRAME_COLS * FRAME_ROWS];
	}
	
    public void render() {
		if (mainHero.getHeroX() != mainHero.getHeroX() - mainHero.getHeroDX()){
	        stateTime += Gdx.graphics.getDeltaTime(); 
	        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
	        spriteBatch.draw(currentFrame, mainHero.getHeroX() - currentFrame.getRegionWidth()/2, mainHero.getHeroY() - currentFrame.getRegionHeight()/2); 
		} else {
		    stateTime += Gdx.graphics.getDeltaTime(); 
	        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
	        spriteBatch.draw(currentFrame, mainHero.getHeroX() - currentFrame.getRegionWidth()/2, mainHero.getHeroY() - currentFrame.getRegionHeight()/2); 
		}
    }
}
