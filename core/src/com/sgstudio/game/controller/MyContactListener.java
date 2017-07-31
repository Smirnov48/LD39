package com.sgstudio.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.sgstudio.game.models.Chair;

public class MyContactListener implements ContactListener {
	private boolean contact = false;
	private int view=0;
	private String contactF = "";
	
	private long startTime;
	private float time = 0;
	private int i=0;
	
	private Body fuelBody;
	private Body Destroy;
	
	private World world;
	private Chair obj;
	
	public MyContactListener(World world){
		startTime = System.currentTimeMillis();
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		Gdx.app.postRunnable(new Runnable(){
			@Override
			public void run() {
				try{ 
					if(Destroy != null & Destroy.getFixtureList().get(0).getUserData().equals("Chair")) world.destroyBody(Destroy); 
					Destroy=null; 
				}
				catch (java.lang.NullPointerException e) { }
			}
		});
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		WorldManifold manifold = contact.getWorldManifold();
//		System.out.println(contact.getFixtureA().getUserData() + "  " + contact.getFixtureB().getUserData());
		for(int j=0;j<manifold.getNumberOfContactPoints();j++){
			if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Player") &&
					contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData() instanceof Chair){
				obj = (Chair) contact.getFixtureB().getUserData();
				this.contact = true;
				this.contactF = "Press 'F' to break chair";
				i=0;
				view=1;
				contact.setEnabled(false);
			} else if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Locomotive") &&
					contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Player")){
				this.contact = true;
				this.contactF = "Press 'E' to put the wood";
				i=0;
			} else if(contact.getFixtureA().getUserData() != null && !contact.getFixtureA().getUserData().equals("Player") &&
					contact.getFixtureB().getUserData() != null && !(contact.getFixtureB().getUserData() instanceof Chair)) {
				if (time != (System.currentTimeMillis() - startTime) / 250) {
					time++;
					i++;
				}
			}
			
			if(i==1){
				this.contact = false;
				this.contactF = "";
				view=0;
			}
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}
	
	public boolean getContact(){ return contact; }
	public String getContactF(){ return contactF; }
	public int getView(){ return view; }
	public Body getBodyFuel(){ return fuelBody; }
	public int getFuel(){ 
		System.out.println(obj);
		return obj.getFuel();
	}
}
