package com.chee.eartraining;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public abstract class AbstractTrainer implements BasicTrainer {

	private int correct;
	private int wrong;
	protected Player player;
	protected Pattern question;
	
	public AbstractTrainer() {
		player = new Player();
		question = new Pattern();
	}
	
	@Override
	public int getScore() {
		return getCorrect()/(getCorrect() + getWrong());
	}

	@Override
	public int getCorrect() {
		return correct;
	}

	@Override
	public int getWrong() {
		return wrong;
	}
	
	public void correctAnswerSelected() {
		correct++;
	}
	
	public void wrongAnswerSelected() {
		wrong++;
	}
	
	public Pattern getQuestion() {
		return question;
	}

}
