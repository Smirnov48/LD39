package com.sgstudio.game.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class MyContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		WorldManifold manifold = contact.getWorldManifold();
		System.out.println(contact.getFixtureA().getUserData() + "  " + contact.getFixtureB().getUserData());
		for(int j=0;j<manifold.getNumberOfContactPoints();j++){
			if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Player") &&
					contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Chair"))
				contact.setEnabled(false);
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
