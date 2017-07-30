package com.sgstudio.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sgstudio.game.player.Demon;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.main.Main;

public class Checker {
	private Main main;
	private Locomotive train;
	private Demon demon;
	private MainHero hero;
	private Sprite sprite;

	public Checker(Main main,Locomotive train,Demon demon,MainHero hero) {
		this.main = main;
		this.train = train;
		this.demon = demon;
		this.hero = hero;
		this.sprite = hero.sprite;
	}
	
	public void update() {
		checkVic();
		checkDefeat();
	}
	
	public void checkVic() {
		//Train got saveStation
		if(train.getDistance() >= train.getWay()) {
			main.setScreen(main.victory);
		}
	}
	
	public void checkDefeat() {
		//Demon on first van
		if (demon.getDisToTrain() < 0) {
			main.setScreen(main.defeat);
		}
		//Player near the Demon
		//System.out.println(sprite.getX() + "  " + demon.getDemonX());
		if(sprite.getX() + 15 < demon.getDemonX()) {
			main.setScreen(main.defeat);
		}
	}

}
