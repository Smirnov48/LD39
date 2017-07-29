package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.game.powers.PowerTree;
import com.badlogic.gdx.math.Rectangle;
import com.sgstudio.main.Main;

public class MyGame implements Screen {
	public static SpriteBatch batch;

	private final Main main;
	private MainHero hero;
	private PowerTree tree;
	public Rectangle rec;
	public Texture img;
	
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
		tree.render();
		hero.render();
		batch.draw(img, rec.x, rec.y);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		hero.dispose();
		tree.dispose();
	}

	@Override
	public void show() {
		batch = main.getBatch();
		img = new Texture("test_plat.jpg");
		hero = new MainHero(batch);
		tree = new PowerTree(batch);
		rec = new Rectangle();
		rec.x = 90;
		rec.y = 0;
	}
	
	private void update() {
		tree.update();		
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