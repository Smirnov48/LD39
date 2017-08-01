package com.sgstudio.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.AboutSGstudio;
import com.sgstudio.game.Defeat;
import com.sgstudio.game.MyGame;
import com.sgstudio.game.Tutorial;
import com.sgstudio.game.Victory;
import com.sgstudio.menu.Menu;

public class Main extends Game {
	
	public MyGame game;
	public Menu menu;
	public Defeat defeat;
	public Victory victory;
	public AboutSGstudio aboutsgstudio;
	public Tutorial tutorial;
	
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		game = new MyGame(this);
		menu = new Menu(this);
		defeat = new Defeat(this);
		victory = new Victory(this);
		aboutsgstudio = new AboutSGstudio(this);
		tutorial = new Tutorial(this);
		
		
		setScreen(menu);
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void dispose(){
		
	}
	
	public SpriteBatch getBatch(){ return batch; }

}
