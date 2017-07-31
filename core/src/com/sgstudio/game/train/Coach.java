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
	
	private Chair chair[] = new Chair[1];
	private Chair chair1[] = new Chair[1];
	private Chair chair2[] = new Chair[1];
	private Chair chair3[] = new Chair[1];
	private Chair chair4[] = new Chair[1];
	private Chair chair5[] = new Chair[1];
	private Sprite chairT;
	private Sprite chairT1;
	private void createChair(){
		chairT = new Sprite(new Texture("atlas/test.png"));
		chairT1 = new Sprite(new Texture("atlas/test1.png"));
		for(int i=0;i<1;i++){
			chair[i] = new Chair(batch, chairT, world);
			chair[i].createModel(number*-500-10, 60);
		}
		for(int q=0;q<1;q++){
			chair1[q] = new Chair(batch, chairT1, world);
			chair1[q].createModel(number*-500+40, 60);
		}
		for(int w=0;w<1;w++){
			chair2[w] = new Chair(batch, chairT, world);
			chair2[w].createModel(number*-500+265-10, 60);
		}
		for(int e=0;e<1;e++){
			chair3[e] = new Chair(batch, chairT1, world);
			chair3[e].createModel(number*-500+265+35, 60);
		}
		for(int r=0;r<1;r++){
			chair4[r] = new Chair(batch, chairT, world);
			chair4[r].createModel(number*-500+145-10, 60);
		}
		for(int t=0;t<1;t++){
			chair5[t] = new Chair(batch, chairT1, world);
			chair5[t].createModel(number*-500+145+30, 60);
		}
	}
	
	private Table table[] = new Table[1];
	private Table table1[] = new Table[1];
	private Table table2[] = new Table[1];
	private Sprite tableT;
	private void createTable(){
		tableT = new Sprite(new Texture("table1.png"));
		for(int i=0;i<1;i++){
			table[i] = new Table(batch, tableT, world);
			table[i].createModel(number*-500+15, 60);
		}
		for(int j=0;j<1;j++){
			table1[j] = new Table(batch, tableT, world);
			table1[j].createModel(number*-500+265, 60);
		}
		for(int k=0;k<1;k++){
			table2[k] = new Table(batch, tableT, world);
			table2[k].createModel(number*-500+145, 60);
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
		for(int i=0;i<1;i++) chair[i].render();
		for(int q=0;q<1;q++) chair1[q].render();
		for(int w=0;w<1;w++) chair2[w].render();
		for(int e=0;e<1;e++) chair3[e].render();
		for(int r=0;r<1;r++) chair4[r].render();
		for(int t=0;t<1;t++) chair5[t].render();
		for(int i=0;i<1;i++) table[i].render();
		for(int j=0;j<1;j++) table1[j].render();
		for(int k=0;k<1;k++) table2[k].render();
//		for(int i=0;i<number;i++) wardrobe[i].render();
	}

}
