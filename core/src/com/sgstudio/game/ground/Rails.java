package com.sgstudio.game.ground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.train.Train;

public class Rails {
	Texture imgRails;
	SpriteBatch batch;
	int railsX;
	final int railsY = 100;
	private Train train;
	private float speed = 0;
	
	private Sprite bgRails[] = {new Sprite(new Texture("rails.png")), new Sprite(new Texture("rails.png")), new Sprite(new Texture("rails.png"))};
	
	public Rails(World world,SpriteBatch batch,Train train) {
		this.train = train;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		FixtureDef fixtureDef = new FixtureDef();

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Gdx.graphics.getWidth(), 100);

		fixtureDef.shape = shape;

		Body ground = world.createBody(bodyDef);
		ground.createFixture(fixtureDef);
		ground.setTransform(0, 0, 0);

		shape.dispose();
		
		//Graphics
		this.batch = batch;
		bgRails[0].setX(0);
		bgRails[1].setX(bgRails[1].getRegionWidth());
		bgRails[2].setX(bgRails[2].getRegionWidth()*2);
		for(int i = 0; i < 3; i++)bgRails[i].setY(99);
	}
	
	public void render() {
		for(int i=0;i<3;i++) bgRails[i].draw(batch);
		for(int i=0;i<3;i++) bgRails[i].setX(bgRails[i].getX()-speed);
		
		if(bgRails[0].getX()<=-800){
			bgRails[0].setX(bgRails[2].getX()+bgRails[2].getWidth());
		}
		if(bgRails[1].getX()<=-800){
			bgRails[1].setX(bgRails[0].getX()+bgRails[0].getWidth());
		}
		if(bgRails[2].getX()<=-800){
			bgRails[2].setX(bgRails[1].getX()+bgRails[1].getWidth());
		}
		
	}
	
	public void update() {
		speed = train.getSpeed()/1.7f;		
	}
	
	public void dispose() {
	}

}
