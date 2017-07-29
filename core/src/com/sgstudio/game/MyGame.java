package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.graphics.Stats;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.powers.Forest;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class MyGame implements Screen {
	public static SpriteBatch batch;
	private final Main main;

	private MainHero hero;
	private Forest forest;
	public Train train;

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Body ground;

	public Stats stats;
		
	private void createGround() {
		if (ground != null)
			world.destroyBody(ground);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		FixtureDef fixtureDef = new FixtureDef();

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(camera.viewportWidth, 100);

		fixtureDef.shape = shape;

		ground = world.createBody(bodyDef);
		ground.createFixture(fixtureDef);
		ground.setTransform(0, 0, 0);

		shape.dispose();
	}

	public MyGame(final Main main) {
		this.main = main;

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);

		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		createGround();
	}
	
	private float speed=0;
	private Sprite bg[] = {new Sprite(new Texture("atlas/bgR.png")), new Sprite(new Texture("atlas/bgG.png")), new Sprite(new Texture("atlas/bgB.png"))};
	private void bg(){
		for(int i=0;i<3;i++) bg[i].draw(batch);
		for(int i=0;i<3;i++) bg[i].setX(bg[i].getX()-speed);
		
		if(bg[0].getX()<=-800){
			bg[0].setX(bg[2].getX()+bg[2].getWidth());
		}
		if(bg[1].getX()<=-800){
			bg[1].setX(bg[0].getX()+bg[0].getWidth());
		}
		if(bg[2].getX()<=-800){
			bg[2].setX(bg[1].getX()+bg[1].getWidth());
		}
	}

	@Override
	public void render(float delta) {
		world.step(1 / 60f, 6, 4);

		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		bg();


		forest.render();
		hero.render();
		stats.render();

		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		
		stats.render();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		forest.dispose();
		hero.dispose();
	}

	@Override
	public void show() {
		Box2D.init();

		batch = main.getBatch();
		hero = new MainHero(batch,world);
		forest = new Forest(batch);
		train = new Train(batch);
		stats = new Stats(batch,hero,train);
		bg[0].setX(0);
		bg[1].setX(bg[1].getRegionWidth());
		bg[2].setX(bg[2].getRegionWidth()*2);
	}

	private void update() {
		speed = train.getSpeed();
		forest.update();
		train.updateOven();
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	public static SpriteBatch getBatch() {
		return batch;
	}
}