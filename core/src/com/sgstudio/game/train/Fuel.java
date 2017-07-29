package com.sgstudio.game.train;

import com.sgstudio.utils.Random;

public class Fuel {
	private static final int StrikesStool = 3;
	private static final int StrikesTable = 5;
	private static final int StrikesPassenger = 4;
	
	private static int view=0;
	/*
	 * 0 - Null
	 * 1 - Stool
	 * 2 - Table
	 * 3 - Passenger
	 */
	
	private int strikesF=0;
	private boolean broken=false;
	
	private Random rand;
	
	public Fuel(int view){
		rand = new Random();
		Fuel.view = view;
	}
	
	public int getFuel(){
		System.out.println(strikesF);
		if(!broken) if(getBroken()){
			broken=true;
			return fuelCounting();
		}
		return 0;
	}
	
	private boolean getBroken(){
		if(view==0){
			
		} else if(view==1){
			if(strikesF==StrikesStool) return true;
			else{
				strikesF++;
				return false;
			}
		} else if(view==2){
			if(strikesF==StrikesTable) return true;
			else{
				strikesF++;
				return false;
			}
		} else if(view==3){
			if(strikesF==StrikesPassenger) return true;
			else{
				strikesF++;
				return false;
			}
		}
		return false;
	}
	
	private int fuelCounting(){
		if(view==0){
			return rand.randInt(0, 0);
		} else if(view==1){
			return rand.randInt(2, 4);
		} else if(view==2){
			return rand.randInt(5, 7);
		} else if(view==3){
			return rand.randInt(8, 10);
		}
		return 0;
	}
}
