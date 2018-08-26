package com.chee.eartraining;

import org.jfugue.pattern.Pattern;

public interface BasicTrainer {

	/**
	 * Gets the score of the current training program
	 * @return score of current program in percentage truncated
	 */
	int getScore();
	
	/**
	 * Gets the number of correct answers in current training program
	 * @return Number of correct answers in current training program
	 */
	int getCorrect();
	
	/**
	 * Gets the number of wrong answers in current training program
	 * @return Number of wrong answers in current training program
	 */
	int getWrong();
	
	/**
	 * Starts the training program with parameters selected and returns the first question generated 
	 */
	Pattern startTraining();
	
	/**
	 * Proceed on to the next question and returns the question generated
	 */
	Pattern next();
	
	/**
	 * Hear the current question again
	 */
	Pattern hearAgain();
	
	/**
	 * End training program and returns the score over the training
	 */
	int endTraining();
}
