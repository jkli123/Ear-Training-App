package com.chee.eartraining;

import java.util.TreeMap;

import org.jfugue.pattern.Pattern;

public class IntervalTrainer extends AbstractTrainer {

	private TreeMap<Integer, Integer> intervalsToTrain;
	private String instrument;
	private int bpm;
	private IntervalRandomizer ir;
	private IntervalParser ip;
	private int currentInterval;
	
	public IntervalTrainer(TreeMap<Integer, Integer> intervals) {
		intervalsToTrain = intervals;
		instrument = "Piano";
		bpm = 120;
		ir = new IntervalRandomizer(intervalsToTrain);
		ip = new IntervalParser();
		question.setInstrument(instrument).setTempo(bpm);
		currentInterval = 0;
	}
	
	public IntervalTrainer(TreeMap<Integer, Integer> intervals, String instrument, int bpm) {
		intervalsToTrain = intervals;
		this.instrument = instrument;
		this.bpm = bpm;
		ir = new IntervalRandomizer(intervalsToTrain);
		ip = new IntervalParser();
		question.setInstrument(instrument).setTempo(bpm);
		currentInterval = 0;
	}
	
	public int getCurrentInterval() {
		return this.currentInterval;
	}
	
	private String[] nextRandomInterval() {
		String[] result = ip.parseInterval(ir.nextRandomInterval());
		this.currentInterval = ir.currentInterval;
		return result;
	}
	
	public Pattern nextIntervalToTrain() {
		String[] intervalToTrain = nextRandomInterval();
		question.add(intervalToTrain[0] + "q.");
		question.add(intervalToTrain[1] + "w");
		return question;
	}
	
	public Pattern startTraining() {
		question = nextIntervalToTrain();
		this.player.play(question);
		return question;
	}

	@Override
	public Pattern next() {
		question.clear();
		question = nextIntervalToTrain();
		playCurrentInterval();
		return question;
	}

	@Override
	public Pattern hearAgain() {
		if(question != null) {
			playCurrentInterval();
			return question;
		} else {
			throw new IllegalStateException("Training has not started or has ended prematurely");
		}
	}

	public int endTraining() {
		intervalsToTrain.clear();
		intervalsToTrain = null;
		instrument = null;
		bpm = 0;
		ir = null;
		ip = null;	
		return getScore();
	}
	
	public TreeMap<Integer, Integer> getIntervalsToTrain() {
		return this.intervalsToTrain;
	}
	
	private void playCurrentInterval() {
		player.play(question);
	}
	
	public static void main(String[] args) {
		TreeMap<Integer, Integer> intervalMapping = new TreeMap<>();
		intervalMapping.put(0, 4);
		intervalMapping.put(0, 7);
		IntervalTrainer it = new IntervalTrainer(intervalMapping);
		it.player.play(it.nextIntervalToTrain());
		
	}

}
