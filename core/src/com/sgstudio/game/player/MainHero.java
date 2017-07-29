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
	
	private static long startTime;
	private static float time = 0;
	public void setStartTime(long startTime){ 
		MainHero.startTime = startTime; 
	}

	float posX,posY;
	Texture img;
	SpriteBatch batch;
	Sprite sprite;
	
	//Stats
	private float health;
	private float hunger;
	private float water;
	private float mood;
	
	//MaxStatsValues
	private float maxHealth;
	private float maxWater;
	private float maxHunger;
	private float maxMood;
	
	public static final float SPEED = 2f;
	public static final float SIZE = 0.7f;
	
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	
	Rectangle bounds = new Rectangle();
	State state = State.NONE;
	
	public MainHero(SpriteBatch batch){		
		img = new Texture("hero.jpg");
		this.batch = MyGame.getBatch();
		sprite = new Sprite(img);
		sprite.setX(100);
		sprite.setY(Gdx.graphics.getHeight() / 2);
		/*sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
               Gdx.graphics.getHeight() / 2);*/
		
		//Характеристики при создании персонажа
		maxHunger = 100f;
		maxWater = 100f;
		maxHealth = 100f;
		maxMood = 100f;
		hunger = maxHunger;
		water = maxWater;
		health = maxHealth;
		mood = maxMood / 2;
		
		System.out.println("Main hero has been successfully created!");
		System.out.println("Your start statistics:");
		System.out.println("Health: " + health);
		System.out.println("WaterHunger: " + water);
		System.out.println("FoodHunger: " + hunger);
		System.out.println("Mood: " + mood +" is " + strMood(mood));
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
	
	public void update(){
		if(time!=(System.currentTimeMillis() - startTime) / 1000){
			time++;
		}
	}
	
	//Get Stats Methods
	public float getHealth() {return health;}
	public float getWater() {return water;}
	public float getHunger() {return hunger;}
	public float getMood() {return mood;}
	
	//Update Stats Methods
	public void updHealth(float i) {if(health + i <= 0) {health = 0;}
		else if(health + i > 0) {health += i;}
		else if(health + i > maxHealth) { health = maxHealth;}
		else {health += i;}
	}
	public void updWater(float i) {if(water + i <= 0) {water = 0;}
		else if(water + i > 0) {water += i;}
		else if(water + i > maxWater) { water = maxWater;}
		else {water += i;}
	}
	public void updHunger(float i) {if(hunger + i <= 0) {hunger = 0;}
		else if(hunger + i > 0) {hunger += i;}
		else if(hunger + i > maxHunger) { hunger = maxHunger;}
		else {hunger += i;}
	}
	public void updMood(float i) {if(mood + i <= 0) {mood = 0;}
		else if(mood + i > 0) {mood += i;}
		else if(mood + i > maxMood) { mood = maxMood;}
		else {mood += i;}
	}
	
	//Set methods
	public void setHealth(float i) {health = i;}
	public void setWater(float i) {water = i;}
	public void setHunger(float i) {hunger = i;}
	public void setMood(float i) {mood = i;}
	public void setMaxHealth(float i) {maxHealth = i;}
	
	//Mood String Method
	public String strMood(float mood) {
		String a;
		if(mood == maxMood) {return "Perfect";}
		if((mood >= 0.9  * maxMood) && (mood < maxMood)) {a = "Great.";}
		if((mood < 0.9 * maxMood) && (mood >= 0.7 * maxMood)) {return "Excellent.";}
		if((mood < 0.7 * maxMood) && (mood >= 0.5 * maxMood)) {return "Good.";}
		if((mood < 0.5 * maxMood) && (mood >= 0.4 * maxMood)) {return "NotGood.";}
		if((mood < 0.4 * maxMood) && (mood >= 0.2 * maxMood)) {return "Poor.";}
		if((mood < 0.2 * maxMood) && (mood >= 0.05 *maxMood)) {return "Bad.";}
		if((mood < 0.05 * maxMood) && (mood >= 0)){return "Panic!";}
		else {return "nuul";}
	}
	
	public void updatePos(Rectangle rec) {
		if(sprite.getY() < 16) {
			
		} else {
			posY-=1;
			sprite.setY(posY);
		}
	}
}
