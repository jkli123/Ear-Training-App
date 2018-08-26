package com.chee.eartraining;

public interface BasicRandomizer {
	
	/** 
	 * Returns a random integer from 0(inclusive) to range(not inclusive)
	 * 
	 * @param range The number to generate up to(not inclusive)
	 * @return a random number
	 */
	int nextRandomInteger(int range);
	
}
