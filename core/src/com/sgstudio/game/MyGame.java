package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.sgstudio.game.ground.Background;
import com.sgstudio.game.ground.Rails;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.powers.Forest;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class MyGame implements Screen {
	public static SpriteBatch batch;
	private final Main main;
	private Music One, Two;

	private MainHero hero;
	private Forest forest;
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
		forest.render();
		hero.render();
		stats.render();

		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		
		stats.render();
		batch.end();
	}
	
	private void music(){
		if(One.isPlaying()){
			float oneTime = One.getPosition();
			if(oneTime<=4) One.setVolume(One.getVolume()+2.5f);
			else if(oneTime<=21) One.setVolume(One.getVolume()-2.5f);
			else if(oneTime<=24){
				One.stop();
				Two.play();
			}
		} else if(Two.isPlaying()){
			float twoTime = Two.getPosition();
			if(twoTime<=4) Two.setVolume(One.getVolume()+2.5f);
			else if(twoTime<=26) Two.setVolume(One.getVolume()-2.5f);
			else if(twoTime<=30){
				One.play();
				Two.stop();
			}
		}
	}
	
	private void update() {
		background.update();
		music();
		forest.update();
		train.updateOven();
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
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		batch = main.getBatch();
		train = new Train(batch);
		background = new Background(batch, train);
		rails = new Rails(world);
		hero = new MainHero(batch, world);
		forest = new Forest(batch);
		stats = new Stats(batch,hero,train);

		One = Gdx.audio.newMusic(Gdx.files.internal("audio/music/MainTheme.wav"));
		One.play();
		One.setVolume(0);
		Two = Gdx.audio.newMusic(Gdx.files.internal("audio/music/BlueCoach.wav"));
		Two.stop();
		Two.setVolume(0);
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