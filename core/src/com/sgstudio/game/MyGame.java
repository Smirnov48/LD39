package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.powers.Forest;
import com.sgstudio.game.village.Village;
import com.badlogic.gdx.math.Rectangle;
import com.sgstudio.main.Main;
import com.sgstudio.game.player.MainHero;

public class MyGame implements Screen {
	public static SpriteBatch batch;
	Texture good;
	Texture bad;
	private final Main main;
	private MainHero hero;
	private Forest forest;
	public Rectangle rec;
	public Village village;
	
	public MyGame(final Main main) {
		this.main = main;
	}

	@Override
	public void render (float delta) {
		update();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hero.updatePos(rec);
		batch.begin();
		if (village.HP > 50000){
			batch.draw(good, 50, 100, 50, 50);
		} else {
			batch.draw(bad, 50, 100, 64, 64);
		}
		forest.render();
		hero.render();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		forest.dispose();
		hero.dispose();
	}

	@Override
	public void show() {
		batch = main.getBatch();
		hero = new MainHero(batch);
		forest = new Forest(batch);
		rec = new Rectangle();
		rec.x = 90;
		rec.y = 0;
		village = new Village();
		good = new Texture("pashasimages/good.gif"); 
		bad = new Texture("pashasimages/bad.png"); 
	}
	
	private void update() {
		forest.update();
		village.update();
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static SpriteBatch getBatch() {
		return batch;
	}
}