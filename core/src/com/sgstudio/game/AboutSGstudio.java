package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.main.Main;

public class AboutSGstudio implements Screen{
	private Main main;
	SpriteBatch batch;
	Texture back1;
	Texture back2;
	Texture back3;
	Texture text;
	Texture title;
	int posofx;
	int posofy;
	int posoftextx;
	int posoftexty;
	private static boolean Moved = false;
	private static boolean Pressed = false;
	private static boolean Play = true;
	
	public AboutSGstudio(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		batch = main.getBatch();
		back1 = new Texture("pashasimages/back1.psd");
		back2 = new Texture("pashasimages/back2.psd");
		back3 = new Texture("pashasimages/back3.psd");
		text = new Texture("pashasimages/text.psd");
		title = new Texture("pashasimages/title.psd");
		posofx = 50;
		posofy = 50;
		posoftextx = 50;
		posoftexty = 100;
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) { return false; }

			@Override
			public boolean keyUp(int keycode) { return false; }

			@Override
			public boolean keyTyped(char character) { return false; }

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if(((Gdx.input.getX() <= back1.getWidth() + posofx)&&
					(Gdx.input.getX() >= posofx)) &&
					
					((Gdx.input.getY() <= back1.getHeight() + posofy))&&
					(Gdx.input.getY() >= posofy)){
					Pressed = true;
				} else { 
					Pressed = false;
				}
			return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(Moved && Pressed){
					main.setScreen(main.menu);
				} else {
					Play = !Play;
				}
				Pressed = false;
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				if(((Gdx.input.getX() <= back1.getWidth() + posofx)&&
						(Gdx.input.getX() >= posofx)) &&
						
						((Gdx.input.getY() <= back1.getHeight() + posofy))&&
						(Gdx.input.getY() >= posofy)){
					Moved = true;
				} else {
					Moved = false;
				}
			return false;
			}

			@Override
			public boolean scrolled(int amount) { return false; }
			
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(title, 
				Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, 
				Gdx.graphics.getHeight() / 2 + 250 - title.getHeight() / 2, 
				title.getWidth(), 
				title.getHeight() );
				
		batch.draw(text,
				posoftextx, 
				posoftexty, 
				text.getWidth(), 
				text.getHeight() );
		
		if(!Moved){
			batch.draw(back1,
					posofx, 
					posofy,
					back1.getWidth(),
					back1.getHeight());
		} else if (Pressed && Moved) {
			batch.draw(back2,
					posofx, 
					posofy,
					back2.getWidth(),
					back2.getHeight());
		} else {
			batch.draw(back3,
					posofx, 
					posofy,
					back3.getWidth(),
					back3.getHeight());
		}
		
		if(Gdx.input.isButtonPressed(0)){
			  
			if (((Gdx.input.getX() <= back1.getWidth() + Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() / 2 - posofx))&&
					(Gdx.input.getX() >= posofx)) &&
					
					((Gdx.input.getY() <= back1.getHeight() + Gdx.graphics.getHeight() / 2 - ( Gdx.graphics.getHeight() / 2 -posofy)))&&
					(Gdx.input.getY() >= posofy)){
				main.setScreen(main.menu);
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {}

}
