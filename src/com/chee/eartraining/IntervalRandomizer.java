package com.chee.eartraining;

import java.util.TreeMap;

/**
 * Generates a random interval from a TreeMap of intervals provided
 * for by the user. All generated intervals are in semitone.
 * @author Chee Peng
 *
 */
public class IntervalRandomizer extends AbstractRandomizer {

	public static final int NUMBER_OF_INTERVALS = 14;
	private TreeMap<Integer, Integer> intervalMapping;
	private NoteParser np;
	private NoteRandomizer nr;
	protected int currentInterval;
	
	public IntervalRandomizer(TreeMap<Integer, Integer> intervalMapping) {
		super();
		this.intervalMapping = intervalMapping;
		np = new NoteParser();
		nr = new NoteRandomizer();
		currentInterval = 0;
	}
	
	public String nextRandomInterval() {
		String startingNote = nr.nextRandomNote();
		verifyGivenNote(startingNote);
		String firstNote = np.parseNote(startingNote);
		String[] parseStartingNote = np.splitNoteIntoNameAndOctave(startingNote);
		int nextNoteName = Integer.parseInt(parseStartingNote[0]) + this.nextRandomIntervalNumber();
		int nextOctave = Integer.parseInt(parseStartingNote[1]);
		if(nextNoteName > 11) {
			nextOctave += nextNoteName / 12;
			nextNoteName %= 12;
		}
		String nextNote = np.parseNote((Integer.toString(nextNoteName)) + "-" + Integer.toString(nextOctave));
		return firstNote + "->" + nextNote;
	}
	
	private void verifyGivenNote(String startingNote) {
		if(!startingNote.contains("-")) {
			throw new IllegalArgumentException("Provided note seems to have been parsed. Provide a raw note format");
		}
	}
	
	/**
	 * From the given intervalMapping, generate a random interval
	 * given in semitone
	 * @return Integer representing the number of semitone.
	 */
	public int nextRandomIntervalNumber() {
		currentInterval = intervalMapping.get(this.nextRandomInteger(intervalMapping.size()));
		return currentInterval;
	}
	
	public static void main(String[] args) {
		TreeMap<Integer, Integer> intervalMapping = new TreeMap<>();
		intervalMapping.put(0, 14);
		IntervalRandomizer ir = new IntervalRandomizer(intervalMapping);
		for(int i = 0; i < 10; i++) {
			System.out.println(ir.nextRandomInterval());				
		}
	}
}
