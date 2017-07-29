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
	private World world;
	private Body body;
	
	public MainHero(SpriteBatch batch, World world){		
		this.world = world;
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
	
		maxWood = 100;
		wood = 0;
		
		createPhysics();
		
		System.out.println("Main hero has been successfully created!");
		System.out.println("Wood: " + wood + "/" + maxWood);
	}
	
	private void createPhysics() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX(), sprite.getY());
	    
		body = world.createBody(bodyDef);
     
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		
		body.createFixture(fixtureDef);
		shape.dispose();
	}

	public enum State {
		NONE, WALKING, DEAD
	}
	
	public void render() {
		batch.draw(sprite, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);	
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

	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getWater() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHunger() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMood() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String strMood(int mood) {
		// TODO Auto-generated method stub
		return null;
	}
}