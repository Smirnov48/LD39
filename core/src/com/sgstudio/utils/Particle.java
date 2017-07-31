package com.sgstudio.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Particle {
	ParticleEffect effect;
	private SpriteBatch batch;
	
	public Particle(SpriteBatch batch) {
		
		this.batch = batch;
		
		effect = new ParticleEffect();
		
		effect.load(Gdx.files.internal("particle/smoke.p"), Gdx.files.internal("particle"));
		effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
		effect.start();
	}
	
	public void render() {
		effect.draw(batch);
	}
	
	public void dispose() {
		//particleAtlas.dispose();
	}
}
