package com.sgstudio.game;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.controller.KeyManager;
import com.sgstudio.game.controller.MyContactListener;
import com.sgstudio.game.graphics.MiniMap;
import com.sgstudio.game.graphics.Stats;
import com.sgstudio.game.ground.Background;
import com.sgstudio.game.ground.Rails;
import com.sgstudio.game.music.MusicGame;
import com.sgstudio.game.player.Demon;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Locomotive;
import com.sgstudio.game.train.Passenger;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;
import com.sgstudio.menu.Menu;
import com.sgstudio.utils.Box2DHelper;

public class Tutorial implements Screen {

	Texture tut;
	@SuppressWarnings("unused")
	private Map<String, TextureRegion> atlasSound;
	/* Time values for actions */
	private long actTime = 0;

	private MyContactListener listener;

	@SuppressWarnings("unused")
	private Texture tex;
	private Texture title1;

	public static SpriteBatch batch;

	private MusicGame music;
	private final Main main;
	private Demon demon;

	private MainHero hero;
	public Train train;
	public Passenger pas;

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;

	public Stats stats;
	private Rails rails;
	private Background background;

	// private Particle particle;

	private MiniMap map;
	public int allDistance = 40000;
	private OrthographicCamera staticCamera;

	private Checker checker;
	private int isTut = 2;
	@SuppressWarnings("unused")
	private float disToMonsterFromCamera;

	public KeyManager man;
	Texture stop;
	private float x;
	private boolean cutSceneFlag = true;
	private boolean cutSceneFlag2 = true;
	private boolean scene = true;

	public Tutorial(Main main) {
		this.main = main;

		staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2 - 1000, Gdx.graphics.getHeight() / 2, 0);
	}

	@Override
	public void show() {
		tut = new Texture("tutorial.png");
		title1 = new Texture("title2.png");
		Box2D.init();
		batch = new SpriteBatch();

		world = new World(new Vector2(0, -10), true);
		listener = new MyContactListener(world);
		world.setContactListener(listener);
		debugRenderer = new Box2DDebugRenderer();

		// particle = new Particle(batch);
		train = new Train(main, batch, world);
		Locomotive locomotive = train.getLocomotive();
		demon = new Demon(main, batch, locomotive, world);
		pas = new Passenger(batch, world);
		man = new KeyManager();
		background = new Background(batch, locomotive);
		rails = new Rails(world, batch, locomotive);
		hero = new MainHero(main, batch, world, locomotive);
		stats = new Stats(batch, hero, locomotive);
		map = new MiniMap(batch, locomotive);
		music = new MusicGame();
		music.setMusicThree();

		checker = new Checker(main, locomotive, demon, hero);
		atlasSound = Menu.getAtlasSound();
		stop = new Texture("charstat.png");
		
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
			
		});
	}

	@SuppressWarnings("unused")
	private void update() {
		checker.update();
		background.update();
		music.update();
		train.update();
		demon.update();
		rails.update();
	}

	@Override
	public void render(float delta) {
		if (isTut == 2) {
			if (!man.getPressedEnter()) {
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				batch.setProjectionMatrix(camera.combined);
				// checker.update();
				background.update();
				music.update();
				// train.update();
				// demon.update();
				rails.update();
				batch.begin();
				world.step(1 / 60f, 6, 4);
				staticCamera.update();
				camera.position.set(hero.getPosition().x, camera.position.y, 0);
				camera.update();
				Gdx.gl.glClearColor(0, 0, 0, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				Matrix4 debugMatrix = batch.getProjectionMatrix().cpy().scale(Box2DHelper.PIXELS_TO_METERS,
						Box2DHelper.PIXELS_TO_METERS, 0);
//				debugRenderer.render(world, debugMatrix);
				batch.end();

				batch.setProjectionMatrix(staticCamera.combined);
				batch.begin();
				background.render();
				rails.render();
				batch.draw(tut, 0, 0);
				batch.end();
			} else {
				isTut = 1;
				actTime = System.currentTimeMillis();
			}
		} else if ((isTut == 1)) {
			if (!man.getPressedEnter() || (System.currentTimeMillis() - actTime < 100)) {
				world.step(1 / 60f, 6, 4);
				background.update();
				music.update();
				rails.update();
				staticCamera.update();
				camera.position.set(x + 1400, camera.position.y, 0);
				camera.update();
				if (scene) {
					/* To Potato */
					if ((camera.position.x > -8400) && (cutSceneFlag) && (cutSceneFlag2)) {
						x -= 40;
						//System.out.println("ToPotato");
						if (camera.position.x < -5900) {
							actTime = System.currentTimeMillis();
							cutSceneFlag = false;
						}
					} else if ((!cutSceneFlag) && (System.currentTimeMillis() - actTime < 2100)
							&& (cutSceneFlag2)) { /* Stopping 2 seconds */
						//System.out.println("Wait");
						if (System.currentTimeMillis() - actTime > 2000) {
							cutSceneFlag2 = false;
						}
					} else if ((!cutSceneFlag) && (!cutSceneFlag2) && (camera.position.x < 320)) {
						x += 40;
						//System.out.println("ToHero");
					}
				}
				Gdx.gl.glClearColor(0, 0, 0, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				batch.setProjectionMatrix(staticCamera.combined);
				batch.begin();
				background.render();
				rails.render();
				batch.draw(title1, 0,0);
				batch.end();

				batch.setProjectionMatrix(camera.combined);
				batch.begin();
				train.render();

				// Draw a Hero
				batch.draw(stop, 305, 45, -stop.getWidth(), stop.getHeight());
				// hero.render(listener.getContact(), listener.getContactF());
				demon.render();

				batch.end();

				Matrix4 debugMatrix = batch.getProjectionMatrix().cpy().scale(Box2DHelper.PIXELS_TO_METERS,
						Box2DHelper.PIXELS_TO_METERS, 0);
//				debugRenderer.render(world, debugMatrix);
			} else {
				isTut = 3;
				actTime = System.currentTimeMillis();
				System.out.println("Game!!");
			}
		} else if ((isTut == 3)) {
			checker.reset();
			music.stop();
			main.setScreen(main.game);
		}
	}

	public void cutScene() {
		disToMonsterFromCamera = -8340 + camera.position.x;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		hero.dispose();
		rails.dispose();
		map.dispose();
		pas.dispose();
		// particle.dispose();
	}

	public static SpriteBatch getBatch() {
		return batch;
	}
}
