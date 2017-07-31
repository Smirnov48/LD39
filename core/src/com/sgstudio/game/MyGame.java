package com.sgstudio.game;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
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
//import com.sgstudio.utils.Particle;

public class MyGame implements Screen {
	private Sound carbon;
	private Sound putToOven;
	private Sound bang;
	
	private boolean Play = true, Moved = false, Pressed = false;
	
	private MyContactListener listener;
	
	private Texture tex;

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
	
	//private Particle particle;

	private MiniMap map;
	public int allDistance = 40000;
	private OrthographicCamera staticCamera;
	
	private Checker checker;
	private int isTut = 2;
	
	public KeyManager man;

	public MyGame(final Main main) {
		this.main = main;

		staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}

	@Override
	public void render(float delta) {
		isTut = 1;
		world.step(1 / 60f, 6, 4);
		update();
		staticCamera.update();
		camera.position.set(hero.getPosition().x, camera.position.y, 0);
		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(staticCamera.combined);
		batch.begin();
		background.render();
		rails.render();
		stats.render(listener.getContact(), listener.getContactF());
		map.render();
		batch.end();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		train.render();
		pas.render();
		hero.render(listener.getContact(), listener.getContactF());
		demon.render();
		
		batch.draw(tex, -800, -600);
		batch.end();

		Matrix4 debugMatrix = batch.getProjectionMatrix().cpy().scale(Box2DHelper.PIXELS_TO_METERS, Box2DHelper.PIXELS_TO_METERS, 0);
		debugRenderer.render(world, debugMatrix);
	}

	private void update() {
		checker.update();
		background.update();
		music.update();
		train.update();
		demon.update();
		rails.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		hero.dispose();
		rails.dispose();
		map.dispose();
		pas.dispose();
		//particle.dispose();
	}

	private int i = 0;
	
	private Map<String, TextureRegion> atlasSound;
	@Override
	public void show() {
		
		Box2D.init();
		batch = main.getBatch();
		
		world = new World(new Vector2(0, -10), true);
		listener = new MyContactListener(world);
		world.setContactListener(listener);
		debugRenderer = new Box2DDebugRenderer();

		batch = main.getBatch();
		//particle = new Particle(batch);
		train = new Train(main, batch, world);
		Locomotive locomotive = train.getLocomotive();
		demon = new Demon(main, batch, locomotive, world);
		pas = new Passenger(batch, world);
		man = new KeyManager();
		background = new Background(batch, locomotive);
		rails = new Rails(world, batch, locomotive);
		hero = new MainHero(batch, world, locomotive);
		stats = new Stats(batch, hero, locomotive);
		map = new MiniMap(batch, locomotive);
		music = new MusicGame();
		tex = new Texture("coor.png");
		
		checker = new Checker(main, locomotive, demon, hero);
		
		atlasSound = Menu.getAtlasSound();
		
		carbon = Gdx.audio.newSound(Gdx.files.internal("audio/sound/Carbon.wav"));
		carbon.stop();
		putToOven = Gdx.audio.newSound(Gdx.files.internal("audio/sound/putToOven.wav"));
		putToOven.stop();
		bang = Gdx.audio.newSound(Gdx.files.internal("audio/sound/Bang.wav"));
		bang.stop();

		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean keyDown(int keycode) {
				int swapValue = 0;
				if (Gdx.input.isKeyPressed(Keys.F) && listener.getContactF().indexOf("F")==7) {
					int Fuel = listener.getFuel();
					System.out.println("In Pull " + Fuel + " woods.");
					if (hero.getWood() + Fuel > hero.getMaxWood())
						swapValue = hero.getMaxWood() + Fuel - hero.getWood();
						hero.updWood(Fuel);
						i += swapValue;
					if (hero.getWood() + Fuel < hero.getMaxWood())
						hero.updWood(Fuel);
					if(Fuel==0) bang.play();
					if(Fuel!=0) carbon.play();
				} else if (Gdx.input.isKeyPressed(Keys.V)) {
					System.out.println("In Pull " + i + " woods.");
					if (hero.getWood() + i < hero.getMaxWood() && i > 0) {
						hero.updWood(i);
						System.out.println("Added " + i + " wood to Hero.");
						i-=i % 11;
						System.out.println("Added to Pull " + i + " wood. ");
					}
					if (hero.getWood() + i > hero.getMaxWood() && i > 0) {
						swapValue = i - (hero.getMaxWood() - hero.getWood());
						hero.updWood(i);
						i = swapValue;
					}	
				}
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
				if(screenX>=15 && screenX<=15+atlasSound.get("tiles0_0").getRegionWidth()){
					if(screenY<=Gdx.graphics.getHeight()-15 && screenY>=Gdx.graphics.getHeight()-15-atlasSound.get("tiles0_0").getRegionHeight())
						Pressed=true;
					else Pressed=false;
				} else Pressed=false;
				return false;
			}
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				if(Moved && Pressed){
					if(Play) {
						music.stopMusic();
					}
					else music.musicStade();
					Play=!Play;
					
				}
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				if(screenX>=15 && screenX<=15+atlasSound.get("tiles0_0").getRegionWidth()){
					if(screenY<=Gdx.graphics.getHeight()-15 && screenY>=Gdx.graphics.getHeight()-15-atlasSound.get("tiles0_0").getRegionHeight())
						Moved=true;
					else Moved=false;
				} else Moved=false;
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}

		});
	}

	@Override
	public void hide() {
		music.stopMusic();
	}

	@Override
	public void resize(int width, int height) {

	}
	
	private boolean isPlaying;
	@Override
	public void pause() {
		System.out.println("Pause");
		isPlaying = Play;
		music.setMuted(Play);
		music.stopMusic();
		if(isPlaying) Play=!Play;
	}

	@Override
	public void resume() {
		Play=true;
		music.setMuted(Play);
		if(isPlaying) Play=!Play;
	}

	public static SpriteBatch getBatch() {
		return batch;
	}
}