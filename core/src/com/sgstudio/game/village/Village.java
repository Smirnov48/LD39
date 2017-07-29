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
	float hunger = 100f;
	float thirst = 100f;
	public void update(){
		if(time!=(System.currentTimeMillis() - startTime) / 1000){
			time++;
			hunger -= 0.333333333333333333333333333333f;
			thirst -= 0.416666666666666666666666666666f;
			
			
			if (hunger < 0){
				hunger = 0;
			}
			if (hunger == 0){
				HP -= 300;
			}
			
			
			if (thirst < 0){
				thirst = 0;
			}
			if (thirst == 0){
				HP -= 400;
			}
			
			if (HP < 0){
				HP = 0;
			}
			/*if (thirst > 0){
			System.out.println(hunger);
			System.out.println(thirst);
			} else {
				System.out.println(hunger);
				System.out.println(HP);
			}*/
		}
	}
}