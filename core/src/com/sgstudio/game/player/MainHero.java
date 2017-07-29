package com.sgstudio.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sgstudio.game.MyGame;

public class MainHero {

	float posX,posY;
	Texture img;
	SpriteBatch batch;
	Sprite sprite;
	
	public static final float SPEED = 2f;
	public static final float SIZE = 0.7f;
	
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	
	Rectangle bounds = new Rectangle();
	State state = State.NONE;
	
	public MainHero(SpriteBatch batch){
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		
		
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		sprite = new Sprite(img);
		sprite.setX(100);
		sprite.setY(Gdx.graphics.getHeight() / 2);
		/*sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
               Gdx.graphics.getHeight() / 2);*/
	}
	
	public enum State {
		NONE, WALKING, DEAD
	}
	
	public void render() {
		batch.draw(sprite,sprite.getX(),sprite.getY());	
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void dispose() {
		img.dispose();
	}
	
	public void updatePos(Rectangle rec) {
		if(sprite.getY() < 16) {
			
		} else {
			posY-=1;
			sprite.setY(posY);
		}
	}
}
