package com.sgstudio.game.powers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Forest {

	private ArrayList<BerryBush> bushes;
	private SpriteBatch batch;

	public Forest(SpriteBatch batch) {
		this.batch = batch;

		bushes = new ArrayList<BerryBush>();
		for (int i = 0; i < 10; i++) {
			bushes.add(new BerryBush(batch));
		}
	}

	public void update() {
		for (int i = 0; i < bushes.size(); i++) {
			bushes.get(i).update();
		}
	}

	public void render() {
		for (int i = 0; i < bushes.size(); i++) {
			bushes.get(i).render();
		}
	}

	public void dispose() {
		for (int i = 0; i < bushes.size(); i++) {
			bushes.get(i).dispose();
		}
	}

}
