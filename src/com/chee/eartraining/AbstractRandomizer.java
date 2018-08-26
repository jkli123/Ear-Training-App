package com.chee.eartraining;

import java.util.Random;

public abstract class AbstractRandomizer implements BasicRandomizer {

	protected Random random;
	
	public AbstractRandomizer() {
		random = new Random();
	}
	
	public int nextRandomInteger(int range) {
		return random.nextInt(range);
	}
}
