package com.sgstudio.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.AboutSGstudio;
import com.sgstudio.game.Defeat;
import com.sgstudio.game.MyGame;
import com.sgstudio.game.Victory;
import com.sgstudio.menu.Menu;
import com.sgstudio.utils.MyJson;

public class Main extends Game {
	private MyJson json;
	
	public MyGame game;
	public Menu menu;
	public Defeat defeat;
	public Victory victory;
	public AboutSGstudio aboutsgstudio;
	
	private static SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		json = new MyJson();
		game = new MyGame(this);
		menu = new Menu(this);
		defeat = new Defeat(this);
		victory = new Victory(this);
		aboutsgstudio = new AboutSGstudio(this);
		
		
		setScreen(menu);
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		json.setPlayingMusic(true);
		json.setResolution(width, height);
		json.writeToFile();
	}
	
	@Override
	public void dispose(){
		
	}
	
	public SpriteBatch getBatch(){ return batch; }

}
