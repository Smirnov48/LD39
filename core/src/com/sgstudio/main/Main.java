package com.sgstudio.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.MyGame;

public class Main extends Game {
	public MyGame game;
	
	private static SpriteBatch batch;
	private static BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		game = new MyGame(this);
		
		setScreen(game);
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose(){
		
	}
	
	public SpriteBatch getBatch(){ return batch; }
	public BitmapFont getFont(){ return font; }
}
