package com.sgstudio.game;

public class Village {
	final int DEATH = 0;
	final int FULHP = 10000;
	int HP = 100000;
	int hunger = 100;
	int thirst = 100;
	public void update(){
		hunger -= 0.1;
		thirst -= 0.3;
		
		if (hunger <= 0){
			HP -= 0.5;
		}
		if (thirst <= 0){
			HP -= 1;
		}
		System.out.println(hunger);
	}
}