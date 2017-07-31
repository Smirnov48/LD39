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
//	private MyGame newgame = new MyGame(main);;

	public Checker(Main main,Locomotive train,Demon demon,MainHero hero) {
		this.main = main;
		this.train = train;
		this.demon = demon;
		this.hero = hero;
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
//		if (demon.getDisToTrain() + demon.getDemonWidth() < 0) {
//			newgame.show();
//			main.setScreen(main.defeat);
//		}
		
		//Player near the Demon
//		if(hero.getHeroX() + 100 < demon.getDemonX()) {
//			main.setScreen(main.defeat);
//			newgame.show();
		//}
	}

	public void reset() {
		train.reset();
		demon.reset();
		hero.reset();
	}

}
