package com.sgstudio.game.train;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.main.Main;

public class Train {

	private Locomotive locomotive;

	public Train(Main main, SpriteBatch batch, World world) {
		locomotive = new Locomotive(main, batch, world);
	}

	public void update() {
	}
	
	public void render() {
		locomotive.render();
	}

	public Locomotive getLocomotive() {
		return locomotive;
	}

}