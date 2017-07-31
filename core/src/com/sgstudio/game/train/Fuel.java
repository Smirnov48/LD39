package com.sgstudio.game.train;

import com.sgstudio.utils.Random;

public class Fuel {
	private static final int StrikesСhair = 3;
	private static final int StrikesTable = 5;
	private static final int StrikesPassenger = 4;
	private static final int StrikesСupboard = 8;
	
	private int view=0;
	/*
	 * 0 - Null
	 * 1 - Сhair
	 * 2 - Table
	 * 3 - Passenger
	 * 4 - Сupboard
	 */
	
	private int strikesF=0;
	private boolean broken=false;
	
	private Random rand;
	
	public Fuel(int view){
		rand = new Random();
		this.view = view;
	}
	
	public boolean isBroken(){ return broken; }
	
	public int getFuel(int view){
//		System.out.println(StrikesСhair + " " + StrikesTable + " " + StrikesPassenger + " " + StrikesСupboard);
		switch(view){
			case (1):
				System.out.println(StrikesСhair);
				if(!broken) System.out.println(strikesF + "/" + StrikesСhair);
				else System.out.println("Obj broken!");
				break;
			case (2):
				if(!broken) System.out.println(strikesF + "/" + StrikesTable);
				else System.out.println("Obj broken!");
				break;
			case (3):
				if(!broken) System.out.println(strikesF + "/" + StrikesPassenger);
				else System.out.println("Obj broken!");
				break;
			case (4):
				if(!broken) System.out.println(strikesF + "/" + StrikesСupboard);
				else System.out.println("Obj broken!");
				break;
		}
		if(!broken) if(getBroken()){
			broken=true;
			return fuelCounting();
		}
		return 0;
	}
	
	private boolean getBroken(){
		switch(view){
			case (1):
				if(strikesF==StrikesСhair) return true;
				else{
					strikesF++;
					return false;
				}
			case (2):
				if(strikesF==StrikesTable) return true;
				else{
					strikesF++;
					return false;
				}
			case (3):
				if(strikesF==StrikesPassenger) return true;
				else{
					strikesF++;
					return false;
				}
			case (4):
				if(strikesF==StrikesСupboard) return true;
				else{
					strikesF++;
					return false;
				}
		}
		return false;
	}
	
	private int fuelCounting(){
		switch(view){
			case(1):
				return rand.randInt(2, 4);
			case(2):
				return rand.randInt(5, 7);
			case(3):
				return rand.randInt(8, 10);
			case(4):
				return rand.randInt(11, 13);
		}
		return 0;
	}
}
