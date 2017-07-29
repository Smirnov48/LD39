package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.powers.Forest;
import com.sgstudio.game.village.Village;
import com.sgstudio.main.Main;
import com.sgstudio.game.graphics.Stats;
import com.sgstudio.game.player.MainHero;

public class MyGame implements Screen {
	public static SpriteBatch batch;
	private final Main main;

	private MainHero hero;
	private Forest forest;
	public Village village;

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

		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		createGround();
	}

	@Override
	public void show() {
		Box2D.init();

		batch = main.getBatch();
		hero = new MainHero(batch, world);
		forest = new Forest(batch);
		village = new Village(batch);
		stats = new Stats(batch,hero,village);
	}

	@Override
	public void render(float delta) {
		world.step(1 / 60f, 6, 4);

		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		village.render();
		forest.render();
		hero.render();

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

	private void update() {
		forest.update();
		village.update();
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