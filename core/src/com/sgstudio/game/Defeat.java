package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.main.Main;
import com.sgstudio.game.MyGame;
import com.sgstudio.menu.Menu;

public class Defeat implements Screen{
	private Main main;
	SpriteBatch batch;
	Texture button1;
	Texture button2;
	Texture button3;
	Texture lose;
	private static boolean Moved = false;
	private static boolean Pressed = false;
	private static boolean Play = true;
	
	public Defeat(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		button1 = new Texture("pashasimages/button1.psd");
		button2 = new Texture("pashasimages/button2.psd");
		button3 = new Texture("pashasimages/button3.psd");
		lose = new Texture("pashasimages/lose.psd");
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) { return false; }

			@Override
			public boolean keyUp(int keycode) { return false; }

			@Override
			public boolean keyTyped(char character) { return false; }

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if(screenX >= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2 && 
						screenX <= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2){			
					if((screenY <=  button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2) && 
							(screenY >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
							Pressed = true;
						} else { 
							Pressed = false;
						}
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
				if(screenX >= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2 && 
						screenX <= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2){		
					if((screenY <=  button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2) && 
							(screenY >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
						Moved = true;
					} else {
						Moved = false;
					}
				}
				return false;
			}

			@Override
			public boolean scrolled(int amount) { return false; }
			
		});
	}
	
	float r=0,g=0,b=0;
	boolean upR=true, upG=true, upB=true;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(lose, 
				Gdx.graphics.getWidth() / 2 - lose.getWidth() / 2, 
				Gdx.graphics.getHeight() / 2 + 150 - lose.getHeight() / 2, 
				lose.getWidth(), 
				lose.getHeight() );
		
		if(!Moved){
			batch.draw(button1,
					Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2,
					button1.getWidth(),
					button1.getHeight());
		} else if (Pressed && Moved) {
			batch.draw(button2,
					Gdx.graphics.getWidth() / 2 - button2.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button2.getHeight() / 2,
					button2.getWidth(),
					button2.getHeight());
		} else {
			batch.draw(button3,
					Gdx.graphics.getWidth() / 2 - button3.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - button3.getHeight() / 2,
					button3.getWidth(),
					button3.getHeight());
		}
		
		if(Gdx.input.isButtonPressed(0)){
			  
			if (((Gdx.input.getX() <= button1.getWidth() + Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)&&
			(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2)) &&
					
			((Gdx.input.getY() <= button1.getHeight() + Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2))&&
			(Gdx.input.getY() >= Gdx.graphics.getHeight() / 2 - button1.getHeight() / 2)){
				main.setScreen(main.menu);
			}
		}
		batch.end();
		switchColor();
	}
	
	private void switchColor(){
		if(upR){
			r+=0.0025;
			if(r>=1) upR=!upR;
		}else{
			r-=0.0005;
			if(r<=0) upR=!upR;
		}
		
		if(upG){
			g+=0.0015;
			if(g>=1) upG=!upG;
		}else{
			g-=0.0015;
			if(g<=0) upG=!upG;
		}
		
		if(upB){
			b+=0.0005;
			if(b>=1) upB=!upB;
		}else{
			b-=0.0025;
			if(b<=0) upB=!upB;
		}
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

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
	}
}
