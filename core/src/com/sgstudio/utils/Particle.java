package com.sgstudio.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Particle {
	ParticleEffect effect;
	private SpriteBatch batch;
	TextureAtlas atlas;
	public Particle(SpriteBatch batch) {
		this.batch = batch;
		atlas = new TextureAtlas("particle/atlas.atlas");
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
