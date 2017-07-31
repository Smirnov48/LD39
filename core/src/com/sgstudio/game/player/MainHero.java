package com.sgstudio.game.player;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.MyGame;
import com.sgstudio.game.controller.KeyManager;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.utils.Box2DHelper;
import com.sgstudio.utils.Tiles;

public class MainHero {
	private Map<String, TextureRegion> atlasChar;
	
	/*Graphics*/
	private Sound putToOven;
	@SuppressWarnings("unused")
	private SpriteBatch batch;
	public static Sprite sprite;
	private Tiles tiles;
	public Animator animator;
	BitmapFont smallFont = new BitmapFont();

	/*Box2D*/
	private World world;
	private Body body;

	/*Hero's wood*/
	private int wood;
	private static int maxWood;
	private int lastWood; //time value
	boolean updatingWood = false;
	
	/*Supposed classes*/
	private KeyManager keys;
	private Locomotive train;
	
	/*Kinematic stats*/
	private float x;
	private float y;
	private float dx;
	
	/*Time values for actions*/
	private static long actTime = 0;

	public MainHero(SpriteBatch batch, World world, Locomotive train) {
		/*Wood Hero Pull*/
		maxWood = 10;
		wood = 0;
		
		/*Supposed classes*/
		this.train = train;
		this.world = world;
		keys = new KeyManager();
		
		/*Graphics*/
		this.batch = MyGame.getBatch();
		tiles = new Tiles();
		tiles.createAtlas("Char.png", 3, 1);
		atlasChar = tiles.getTextureRegion();
		sprite = new Sprite(atlasChar.get("tiles0_2"));
		animator = new Animator(this);
		
		/*Box2D*/
		createPhysics();
		
		//Kinematic
		x = train.getX();
		
		/*Font for Text and params*/
		smallFont = new BitmapFont();
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("font/pixel.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 24;
		param.borderColor = Color.BLACK;
		param.borderWidth = 1;
		smallFont = gen.generateFont(param);
		
		putToOven = Gdx.audio.newSound(Gdx.files.internal("audio/sound/putToOven.wav"));
		putToOven.stop();
	}

	private void createPhysics() {
		body = Box2DHelper.makeBoxAroundSprite(world, sprite, "Player");
		Box2DHelper.setTransform(body, 290, 60, 0);
		body.setFixedRotation(true);
	}

	public void render(boolean contact, String contactF) {
		update(contact,contactF);
		Vector2 pos = Box2DHelper.getPosition(body);
		animator.render();
		dx = x - pos.x;
		x = pos.x;
		y = pos.y;
		if(updatingWood) {
			if (System.currentTimeMillis() - actTime < 1000) {	
				smallFont.draw(batch, "+" + (wood-lastWood) , pos.x + dx, 200);
			} else {
				updatingWood = false;
			}
		}
	}
	
	public void dispose() {
		
	}
	
	private boolean jump = false;

	public void update(boolean contact, String contactF) {
		if(body.getPosition().y<=0.7) jump = true;
		else if(body.getPosition().y>=0.85) jump = false;
		
		if (keys.getPressedLeft()) {
			body.applyForceToCenter(-.9f, 0, true);
			if (body.getLinearVelocity().x < -5) {
				body.setLinearVelocity(-5, body.getLinearVelocity().y);
			}
		}
		if (keys.getPressedRight()) {
			body.applyForceToCenter(0.9f, 0, true);
			if (body.getLinearVelocity().x > 5) {
				body.setLinearVelocity(5, body.getLinearVelocity().y);
			}
		}
		if (keys.getPressedSpace()) {
			if(jump) body.applyForceToCenter(0, 5f, true);
		}
		if (keys.getPressedE()) {
			if(contact && contactF.equals("Press 'E' to put the wood")){
				if(wood!=0){
					putToOven.play();
					putWood();
				}
			}
		}
	}

	public void putWood() {
		train.updOvenWood(getWood());
		setWood(0);
	}

	public int getWood() {
		return wood;
	}

	public int getMaxWood() {
		return maxWood;
	}
	
	public float getHeroX() {
		return x;
	}
	
	public float getHeroY() {
		return y;
	}
	
	public float getHeroDX() {
		return dx;
	}

	// Update Stats Methods
	public void updWood(int i) {
		if (i!=0) {
		
		lastWood = wood;
		if (wood + i <= 0) {
			wood = 0;
		} else if (wood + i > maxWood) {
			wood = maxWood;
		} else {
			wood += i;
		}
		updatingWood = true;
		actTime = System.currentTimeMillis();
		}
	}

	// Set methods
	public void setWood(int i) {
		if (i >= maxWood) {
			wood = maxWood;
		} else if (i <= 0) {
			wood = 0;
		} else
			wood = i;
	}

	public void setMaxWood(int i) {
		maxWood = i;
	}

	public Vector2 getPosition() {
		return Box2DHelper.getPosition(body);
	}
	
	public void checkDeath() {
		
	}
}