package com.sgstudio.game.graphics;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.utils.Tiles;

public class Stats {
	private Tiles tiles;
	private Map<String, TextureRegion> atlasWood;
	
	SpriteBatch batch;
	BitmapFont mediumFont;
	BitmapFont largeFont;
	BitmapFont smallFont;
	MainHero hero;
	Locomotive train;
	
	public Stats(SpriteBatch batch,MainHero hero,Locomotive train) {
		tiles = new Tiles();
		tiles.createAtlas("atlas/wood.png", 2, 1);
		atlasWood = tiles.getTextureRegion();
		//������ ������� �������
		largeFont = new BitmapFont();
		smallFont = new BitmapFont();
		mediumFont = new BitmapFont();
		//���������
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("font/pixel.ttf"));
		//��������
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 40;
		param.borderColor = Color.BLACK;
		param.borderWidth = 1;
		largeFont = gen.generateFont(param);
		param.size = 24;
		smallFont = gen.generateFont(param);
		param.size = 28;
		mediumFont = gen.generateFont(param);
		
		this.batch = batch;
		this.hero = hero;
		this.train = train;
		
		
		System.out.println("All stats has been uploading.");
		System.out.println("Wood: " + hero.getWood() + "/" + hero.getMaxWood());
		System.out.println("Fuel: " + train.getOvenWood() + "/" + train.getMaxOvenWood());
	}
	
	public void render() {
		//PlayerStats
//		largeFont.draw(batch,"Player:",20,580);
		batch.draw(atlasWood.get("tiles0_1"), 10, Gdx.graphics.getHeight()-42);
//		smallFont.draw(batch, "Wood: ", 20, 530);
		mediumFont.draw(batch,String.valueOf((int)hero.getWood()),55,Gdx.graphics.getHeight()-20);
		
		//TrainStats
		largeFont.draw(batch,"Train:",670,580);
		smallFont.draw(batch, "Fuel: ", 680, 530);
		mediumFont.draw(batch,String.valueOf((int)train.getOvenWood()),760,535);
		smallFont.draw(batch, "Speed: ", 680, 500);
		mediumFont.draw(batch,String.valueOf((int)train.getSpeed()),760,505);
		if(train.onFire()) {
			mediumFont.draw(batch,"OnFire!",680,470);
		}
		
	}
}
