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
	//Graphic
	Texture img;
	SpriteBatch batch;
	Sprite sprite;
	
	//Box2D
	private World world;
	private Body body;
	Vector2 vec;
	//Player Box2D
	final static float MAX_VELOCITY = 3f;
	public final static float SPEED = 5f;
	public final static float SIZE = 0.8f;
	public Fixture playerPhysicsFixture;
	public Fixture playerSensorFixture;
	Body box;
	
	//Stats
	private int wood;
	private static int maxWood;
	
	public MainHero(SpriteBatch batch, World world,Body b){		
		//Graphics
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
		sprite.setX(100);
		sprite.setY(Gdx.graphics.getHeight() / 2);
		
		//Stats
		maxWood = 100;
		wood = 0;
		
		//box2d
		this.world = world;
		//createPhysics();
		vec = new Vector2();
		
		/*box = b;		
		PolygonShape poly = new PolygonShape();		
		poly.setAsBox(0.4f, 0.4f);
		playerPhysicsFixture = box.createFixture(poly,0);
		poly.dispose();
		CircleShape circle = new CircleShape();		
		circle.setRadius(0.4f);
		circle.setPosition(new Vector2(0, -0.05f));
		playerSensorFixture = box.createFixture(circle, 0);
		//������
		//setFriction(200F);
		circle.dispose();		
		box.setBullet(true);*/
		
		
		//Systems messages 
		System.out.println("Main hero has been successfully created!");
		System.out.println("Wood: " + wood + "/" + maxWood);
		System.out.println("MainHero has been created();");
	}
	
	/*private void createPhysics() {
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
	}*/

	public enum State {
		NONE, WALKING, DEAD
	}
	
	public void render() {
	//	batch.draw(sprite, body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);	
	}
	
	public void dispose() {
		img.dispose();
	}
	
	public void update() {
		//��������������� � KeyManager
		//body.applyforce
		
	}
	/*
	//Box2D methods
	public float getFriction(){
		return playerSensorFixture.getFriction();
	}
	
	public Body getBody(){
		return box;
	}
	
	public void setFriction(float f){
		playerSensorFixture.setFriction(f); 
		playerPhysicsFixture.setFriction(f); 
	}
	
	public Vector2 getPosition(){
		return box.getPosition();
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	Vector2 velocity = new Vector2();
	public void update(float delta) {
		Vector2 vel = box.getLinearVelocity();
		velocity.y = vel.y;
		box.setLinearVelocity(velocity);
		if(isJump) {box.applyLinearImpulse(0, 14, box.getPosition().x,  box.getPosition().y, isJump);	isJump = false;}
	}
	boolean isJump = false;
	public void jump(){
		isJump = true;
	}
	public void resetVelocity(){
		getVelocity().x =0;
		getVelocity().y =0;
	}*/
	
	//Get Stats Methods
	public int getWood() {return wood;}
	public int getMaxWood() {return maxWood;} 
	
	//Update Stats Methods
	public void updWood(int i) {if(wood + i <= 0) {wood = 0;}
		else if(wood + i > 0) {wood += i;}
		else if(wood + i > maxWood) { wood = maxWood;}
		else {wood += i;}
	}
	
	//Set methods
	public void setWood(int i) {
		if(i < maxWood) {wood = maxWood;}
		else if (i < 0) {wood = 0;}}
	public void setMaxWood(int i) {
		maxWood = i;
	}
}