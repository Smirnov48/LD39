package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.graphics.Stats;
import com.sgstudio.game.ground.Background;
import com.sgstudio.game.ground.Rails;
import com.sgstudio.game.music.MusicGame;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class MyGame implements Screen {
	public static SpriteBatch batch;
	private MusicGame music;
	private final Main main;

	private MainHero hero;
	public Train train;

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	public Stats stats;
	private Rails rails;
	private Background background;

	public MyGame(final Main main) {
		this.main = main;

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
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
		
		background.render();
		train.render();
		hero.render();
		stats.render();

		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		
		stats.render();
		
		batch.end();
	}
	

	
	private void update() {
		music.update();
		train.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		hero.dispose();
	}

	@Override
	public void show() {
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		batch = main.getBatch();
		train = new Train(batch);
		background = new Background(batch, train);
		rails = new Rails(world);
		hero = new MainHero(batch, world);
		stats = new Stats(batch,hero,train);
		music = new MusicGame();
		
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
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