package com.sgstudio.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.AboutSGstudio;
import com.sgstudio.game.Defeat;
import com.sgstudio.game.MyGame;
import com.sgstudio.game.Victory;
import com.sgstudio.menu.Menu;
import com.sgstudio.settings.Save;

public class Main extends Game {
	private Save save;
	
	public MyGame game;
	public Menu menu;
	public Defeat defeat;
	public Victory victory;
	public AboutSGstudio aboutsgstudio;
	
	private static SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		save = new Save();
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
		save.preferences("Settings");
		save.Resolution(width, height);
		save.flush();
	}
	
	@Override
	public void dispose(){
		
	}
	
	public SpriteBatch getBatch(){ return batch; }
}
