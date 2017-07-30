package com.sgstudio.game.train;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.main.Main;

public class Train {

	private Locomotive locomotive;
	private ArrayList<Coach> coaches;

	public Train(Main main, SpriteBatch batch, World world) {
		locomotive = new Locomotive(main, batch, world);
		
		coaches = new ArrayList<Coach>();
		for (int i = 0; i < 10; i++) {
			coaches.add(new Coach(batch, world, i));
		}
	}

	public void update() {
		locomotive.update();
	}
	
	public void render() {
		locomotive.render();
		for (int i = 0; i < 10; i++) {
			Coach coach = coaches.get(i);
			coach.render();
		}
	}

	public Locomotive getLocomotive() {
		return locomotive;
	}

}