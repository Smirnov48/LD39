package com.sgstudio.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sgstudio.game.MyGame;
import com.sgstudio.game.village.Village;

public class MainHero {
	float posX,posY;
	Texture img;
	SpriteBatch batch;
	Sprite sprite;
	
	private float wood;
	private static float maxWood;
	
	public MainHero(SpriteBatch batch){		
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		sprite = new Sprite(img);
		sprite.setX(100);
		sprite.setY(Gdx.graphics.getHeight() / 2);
		/*sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
               Gdx.graphics.getHeight() / 2);*/
		
		//�������������� ��� �������� ���������
		maxWood = 100;
		wood = 0;
		
		System.out.println("Main hero has been successfully created!");
		System.out.println("Wood: " + wood + "/" + maxWood);
	}
	
	public enum State {
		NONE, WALKING, DEAD
	}
	
	public void render() {
		batch.draw(sprite,sprite.getX(),sprite.getY());	
	}
	
	public void dispose() {
		img.dispose();
	}
	
	//Get Stats Methods
	public float getWood() {return wood;}
	
	//Update Stats Methods
	public void updWood(float i) {if(wood + i <= 0) {wood = 0;}
		else if(wood + i > 0) {wood += i;}
		else if(wood + i > maxWood) { wood = maxWood;}
		else {wood += i;}
	}
	
	//Set methods
	public void setHealth(float i) {
		if(i < maxWood) {wood = maxWood;}
		else if (i < 0) {wood = 0;}}
	
	public void setMaxWood(float i) {
		maxWood = i;
	}
}