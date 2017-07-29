package com.sgstudio.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Train;
import com.sgstudio.game.village.Village;

public class Stats {
	
	SpriteBatch batch;
	BitmapFont mediumFont;
	BitmapFont largeFont;
	BitmapFont smallFont;
	MainHero hero;
	Train train;
	
	public Stats(SpriteBatch batch,MainHero hero,Train train) {
		//Шрифты разного размера
		largeFont = new BitmapFont();
		smallFont = new BitmapFont();
		mediumFont = new BitmapFont();
		//Генератор
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("pixel.ttf"));
		//Параметр
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
		train.updateOven();
		
		//PlayerStats
		largeFont.draw(batch,"Player:",20,580);
		smallFont.draw(batch, "Wood: ", 20, 530);
		mediumFont.draw(batch,String.valueOf((int)hero.getWood()),115,535);
		
		//TrainStats
		largeFont.draw(batch,"Train:",600,580);
		smallFont.draw(batch, "Fuel: ", 600, 530);
		mediumFont.draw(batch,String.valueOf((int)train.getOvenWood()),730,535);
		smallFont.draw(batch, "Speed: ", 600, 500);
		mediumFont.draw(batch,String.valueOf((int)train.getSpeed()),730,505);
		if(train.onFire()) {
			mediumFont.draw(batch,"OnFire!",680,470);
		}
		
	}
}
