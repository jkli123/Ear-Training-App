package com.chee.eartraining;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public abstract class AbstractTrainerWindow {

	protected AbstractTrainer trainer;
	protected JFrame mainFrame;
	protected JPanel mainPanel;
	protected JProgressBar progressBar;
	
	public AbstractTrainerWindow() {
		mainFrame = new JFrame(this.getClass().getSimpleName());
		mainPanel = new JPanel();
		progressBar = new JProgressBar();
	}
	
}
