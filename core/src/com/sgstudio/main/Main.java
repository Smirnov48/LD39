package com.sgstudio.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.MyGame;
import com.sgstudio.menu.Menu;
import com.sgstudio.settings.Save;

public class Main extends Game {
	private Save save;
	
	public MyGame game;
	public Menu menu;
	
	private static SpriteBatch batch;
	private static BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		save = new Save();
		game = new MyGame(this);
		menu = new Menu(this);
		
		setScreen(game);
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
		System.out.println(save.getInt("Width") + "  " + width);
	}
	
	@Override
	public void dispose(){
		
	}
	
	public SpriteBatch getBatch(){ return batch; }
	public BitmapFont getFont(){ return font; }
}
