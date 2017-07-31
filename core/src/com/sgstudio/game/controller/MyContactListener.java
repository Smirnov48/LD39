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
import com.sgstudio.game.models.Table;

public class MyContactListener implements ContactListener {
	private boolean contact = false;
	private int view=0;
	private String contactF = "";
	
	private long startTime;
	private float time = 0;
	private int i=0;
	
	private Body destroy;
	
	private World world;
	private Chair objC;
	private Table objT;
	
	public MyContactListener(World world){
		startTime = System.currentTimeMillis();
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		
	}

	@Override
	public void endContact(Contact contact) {
		
	}
	
	public void deliteObj(){
		try{
			if(objC.isBroken() || objT.isBroken()){
				Gdx.app.postRunnable(new Runnable(){
					@Override
					public void run() {
						try{
							if(destroy != null && objC.isBroken()){
								world.destroyBody(destroy);
								objC.delTexture();
							}
							if(destroy != null && objT.isBroken()){
								world.destroyBody(destroy);
								objC.delTexture();
							}
							destroy=null;
						}
						catch (java.lang.NullPointerException e) {Gdx.app.log("Error: ", e.getMessage());}
					}
				});
			}
		} catch(java.lang.NullPointerException e){}
	}
	
	private String get="";
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		WorldManifold manifold = contact.getWorldManifold();
//		System.out.println(contact.getFixtureA().getUserData() + "  " + contact.getFixtureB().getUserData());
		for(int j=0;j<manifold.getNumberOfContactPoints();j++){
			if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Player") &&
					contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData() instanceof Chair){
				objC = (Chair) contact.getFixtureA().getUserData();
				this.contact = true;
				this.contactF = "Press 'F' to break chair";
				i=0;
				view=1;
				contact.setEnabled(false);
				destroy = contact.getFixtureA().getBody();
				get="Chair";
			} else if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Player") &&
					contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData() instanceof Table){
				objT = (Table) contact.getFixtureA().getUserData();
				this.contact = true;
				this.contactF = "Press 'F' to break table";
				i=0;
				view=2;
				contact.setEnabled(false);
				destroy = contact.getFixtureA().getBody();
				get="Table";
			} else if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Player") &&
					contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Firebox")){
				contact.setEnabled(false);
			} else if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Locomotive") &&
					contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Player")){
				this.contact = true;
				this.contactF = "Press 'E' to put the wood";
				i=0;
			} else if(contact.getFixtureB().getUserData() != null && !contact.getFixtureB().getUserData().equals("Player") &&
					contact.getFixtureA().getUserData() != null && !(contact.getFixtureA().getUserData() instanceof Chair)) {
				if (time != (System.currentTimeMillis() - startTime) / 250) {
					time++;
					i++;
				}
			}
			
			if(i==1){
				this.contact = false;
				this.contactF = "";
				this.get="";
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
	public int getFuel(){ 
		if(get.equals("Chair")) return objC.getFuel(); 
		else if(get.equalsIgnoreCase("Table")) return objT.getFuel();
		else return 0;
	}
}
