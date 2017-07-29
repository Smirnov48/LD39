package com.sgstudio.utils;

public class Random {
	private static java.util.Random rand = new java.util.Random();
	
	public int randInt(int min, int max) {
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
