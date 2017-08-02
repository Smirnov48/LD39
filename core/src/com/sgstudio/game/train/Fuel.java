package com.sgstudio.game.train;

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
	
	public Fuel(int view){
		this.view = view;
	}
	
	public boolean isBroken(){ return broken; }
	
	public int getFuel(int view){
		/*
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
		*/
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
				return 2;
			case(2):
				return 4;
			case(3):
				return 4;
			case(4):
				return 6;
		}
		return 0;
	}
}
