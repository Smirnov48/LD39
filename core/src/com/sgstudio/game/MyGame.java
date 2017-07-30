package com.sgstudio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sgstudio.game.graphics.Stats;
import com.sgstudio.game.ground.Background;
import com.sgstudio.game.ground.Rails;
import com.sgstudio.game.music.MusicGame;
import com.sgstudio.game.player.MainHero;
import com.sgstudio.game.train.Fuel;
import com.sgstudio.game.train.Train;
import com.sgstudio.main.Main;

public class MyGame implements Screen {
	private Fuel obj1;
	private Fuel obj2;
	private Fuel obj3;
	
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
		rails.render();
		train.render();
		hero.render();
		stats.render();
		

		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		batch.end();
	}
	

	
	private void update() {
		background.update();
		music.update();
		train.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		hero.dispose();
		rails.dispose();
	}
	
	private int i=0;
	@Override
	public void show() {
		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		batch = main.getBatch();
		train = new Train(batch, world);
		background = new Background(batch, train);
		rails = new Rails(world,batch,train);
		hero = new MainHero(batch, world, train);
		stats = new Stats(batch,hero,train);
		music = new MusicGame();
		
		obj1 = new Fuel(1);
		obj3 = new Fuel(2);
		obj2 = new Fuel(3);
		
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) {
				
				if(Gdx.input.isKeyPressed(Keys.Z)){
					int Fuel = obj1.getFuel();
					if(hero.getWood()+Fuel>hero.getMaxWood()) i+=hero.getWood()+Fuel-hero.getMaxWood();
					if(hero.getWood()+Fuel<hero.getMaxWood()) hero.updWood(Fuel);
				}
				else if(Gdx.input.isKeyPressed(Keys.X)){
					int Fuel = obj2.getFuel();
					if(hero.getWood()+Fuel>hero.getMaxWood()) i+=hero.getWood()+Fuel-hero.getMaxWood();
					if(hero.getWood()+Fuel<hero.getMaxWood()) hero.updWood(Fuel);
				}
				else if(Gdx.input.isKeyPressed(Keys.C)){
					int Fuel = obj3.getFuel();
					if(hero.getWood()+Fuel>hero.getMaxWood()) i+=hero.getWood()+Fuel-hero.getMaxWood();
					if(hero.getWood()+Fuel<hero.getMaxWood()) hero.updWood(Fuel);
				} else if(Gdx.input.isKeyPressed(Keys.V)){
					int I = 0;
					if(hero.getWood()+i<hero.getMaxWood()){
						I=hero.getWood()+i-hero.getMaxWood();
					}
					if(hero.getWood()<hero.getMaxWood() && i>0){
						int y = hero.getMaxWood() - hero.getWood();
						i-=y;
						hero.updWood(y);
					}
					if(I>0) i=I;
					if(i>0) System.out.println("You can not take "+i+" woods! Button 'V' - Pick them up");
				}
				if(i>0) System.out.println("You can not take "+i+" woods! Button 'V' - Pick them up");
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				
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