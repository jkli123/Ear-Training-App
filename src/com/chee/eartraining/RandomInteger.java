package com.chee.eartraining;

import java.util.Random;

public class RandomInteger {

	private int range;
	private Random random = new Random();
	
	public RandomInteger(int range) {
		this.range = range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getNextRandomInteger() {
		return random.nextInt(range);
	}
}
