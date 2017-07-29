package com.sgstudio.game.village;

public class Village {
	private static long startTime;
	private static float time = 0;
	public void setStartTime(long startTime){ 
		Village.startTime = startTime; 
	}
	final int DEATH = 0;
	final int FULHP = 10000;
	int HP = 100000;
	int hunger = 100;
	int thirst = 100;
	public void update(){
		if(time!=(System.currentTimeMillis() - startTime) / 1000){
			time++;
			hunger -= 0.1;
			thirst -= 0.3;
			
			
			if (hunger < 0){
				hunger = 0;
			}
			if (hunger == 0){
				HP -= 0.5;
			}
			
			
			if (thirst < 0){
				thirst = 0;
			}
			if (thirst == 0){
				HP -= 1;
			}
			
			System.out.println(hunger);
			System.out.println(thirst);
		}
	}
}