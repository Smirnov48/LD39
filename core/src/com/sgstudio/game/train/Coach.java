package com.sgstudio.game.train;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.models.Chair;
import com.sgstudio.game.models.Table;
import com.sgstudio.game.models.Wardrobe;
import com.sgstudio.utils.Box2DHelper;

public class Coach {
	private SpriteBatch batch;
	private Texture img;
	private Sprite sprite;

	private World world;
	private Body body;
	private int number;

	public Coach(SpriteBatch batch, World world, int number) {
		this.batch = batch;
		this.world = world;
		this.number = number;

		img = new Texture(Gdx.files.internal("atlas/coach.png"));
		sprite = new Sprite(img);
		
		createChair();
		createTable();
//		createWardrobe();
		
		createPhysics();
	}
	
	private Chair chair[] = new Chair[10];
	private Sprite chairT;
	private void createChair(){
		chairT = new Sprite(new Texture("atlas/test.png"));
		for(int i=0;i<number;i++){
			chair[i] = new Chair(batch, chairT, world);
			chair[i].createModel(number*-500-10, 60);
		}
	}
	
	private Table table[] = new Table[10];
	private Sprite tableT;
	private void createTable(){
		tableT = new Sprite(new Texture("table1.png"));
		for(int i=0;i<number;i++){
			table[i] = new Table(batch, tableT, world);
			table[i].createModel(number*-500+15, 60);
		}
	}
	
/*	private Wardrobe wardrobe[] = new Wardrobe[10];
	private Sprite wardrobeT;
	private void createWardrobe(){
		wardrobeT = new Sprite(new Texture("wardrobe2.png"));
		for(int i=0;i<number;i++){
			wardrobe[i] = new Wardrobe(batch, wardrobeT, world);
			wardrobe[i].createModel(number*-500+45, 60);
		}
	}*/

	private void createPhysics() {
		Vector2 size = new Vector2(sprite.getWidth() / 2, 12);
		Vector2 pos = new Vector2(number * -500, 200);
		body = Box2DHelper.makeBox(world, size, pos, "Coach");

		size = new Vector2(sprite.getWidth() / 2 - 30, 5);
		pos = new Vector2(number * -500, 316);
		Box2DHelper.addShapeBox(body, size, pos);
		Box2DHelper.setTransform(body, 132, -170, 0);
	}

	public void render() {
		Vector2 pos = Box2DHelper.getPosition(body);
		batch.draw(sprite, number * -500 + pos.x - sprite.getWidth() / 2, pos.y + sprite.getHeight() + 10);
		for(int i=0;i<number;i++) chair[i].render();
		for(int i=0;i<number;i++) table[i].render();
//		for(int i=0;i<number;i++) wardrobe[i].render();
	}

}
