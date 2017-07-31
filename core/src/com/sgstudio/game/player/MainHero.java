package com.sgstudio.game.player;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	private SpriteBatch batch;
	public static Sprite sprite;
	private Tiles tiles;

	private World world;
	private Body body;

	private int wood;
	private static int maxWood;
	private KeyManager keys;
	private Locomotive train;
	
	private float x;

	public MainHero(SpriteBatch batch, World world, Locomotive train) {
		this.train = train;
		this.batch = MyGame.getBatch();
		this.world = world;
		tiles = new Tiles();
		tiles.createAtlas("Char.png", 3, 1);
		atlasChar = tiles.getTextureRegion();
		
		sprite = new Sprite(atlasChar.get("tiles0_2"));

		maxWood = 10;
		wood = 10;

		createPhysics();

		keys = new KeyManager();
		x = train.getX();
	}

	private void createPhysics() {
		body = Box2DHelper.makeBoxAroundSprite(world, sprite, "Player");
		Box2DHelper.setTransform(body, 290, 60, 0);
		body.setFixedRotation(true);
	}

	public enum State {
		NONE, WALKING, DEAD
	}

	public void render(boolean contact, String contactF) {
		update(contact,contactF);
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, pos.x - sprite.getWidth() / 2, pos.y - sprite.getHeight() / 2);
		x = pos.x;
	}

	public void dispose() {
	}

	public void update(boolean contact, String contactF) {
		if (keys.getPressedLeft()) {
			body.applyForceToCenter(-1.0f, 0, true);
			
		}
		if (keys.getPressedRight()) {
			body.applyForceToCenter(1.0f, 0, true);
		}
		if (keys.getPressedUp()) {
			body.applyForceToCenter(0, 1.5f, true);
		}
		if (keys.getPressedE()) {
			if(contact && contactF.equals("Press 'E' to put the wood")) putWood();
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

	// Update Stats Methods
	public void updWood(int i) {
		if (wood + i <= 0) {
			wood = 0;
		} else if (wood + i > maxWood) {
			wood = maxWood;
		} else {
			wood += i;
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