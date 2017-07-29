package graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.village.Village;

public class Stats {
	
	SpriteBatch batch;
	BitmapFont mediumFont;
	BitmapFont largeFont;
	BitmapFont smallFont;
	MainHero hero;
	Village village;
	
	public Stats(SpriteBatch batch,MainHero hero,Village village) {
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
		this.village = village;
	}
	
	public void render() {
		//PlayerStats
		largeFont.draw(batch,"Player:",20,580);
		smallFont.draw(batch, "Health: ", 20, 530);
		mediumFont.draw(batch,String.valueOf((int)hero.getHealth()),115,535);
		smallFont.draw(batch, "Water: ", 20, 505);
		mediumFont.draw(batch,String.valueOf((int)hero.getWater()),115,510);
		smallFont.draw(batch, "Hunger: ", 20, 485);
		mediumFont.draw(batch,String.valueOf((int)hero.getHealth()),115,490);
		smallFont.draw(batch, "Mood: ", 20, 465);
		mediumFont.draw(batch,String.valueOf((int)hero.getHealth()),115,470);
		//VillageStats
		largeFont.draw(batch,"Village:",600,580);
	}
}
