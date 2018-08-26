package com.chee.eartraining;

/**
 * Random produces a note from ranges C2(inclusive) to B7(inclusive) by default
 * Customizable ranges are available when initalising the NoteRandomizer object
 * @author Chee Peng
 *
 */
public class NoteRandomizer extends AbstractRandomizer {

	private static final int NUMBER_OF_NOTES = 12;
	private int lowestOctave;
	private int highestOctave;
	
	public NoteRandomizer() {
		super();
		lowestOctave = 2;
		highestOctave = 7;
	}
	
	public NoteRandomizer(int lowestOctave, int highestOctave) {
		super();
		this.lowestOctave = lowestOctave;
		this.highestOctave = highestOctave;
	}
	
	/**
	 * Produces a random note where the first number is the mapping of number to note
	 * in AbstractTrainer class. The second number is the octave range the note is in.
	 * The two are seperated by a '-' sign.
	 * E.g. 4-5, means D#5
	 * @return String containing note and octave of note.
	 */
	public String nextRandomNote() {
		int note = this.nextRandomInteger(NUMBER_OF_NOTES);
		int octave = this.nextRandomInteger(highestOctave - lowestOctave + 1) + lowestOctave;
		return Integer.toString(note) + "-" + Integer.toString(octave);
	}
	
	public static void main(String[] args) {
		NoteRandomizer nr = new NoteRandomizer(0, 9);
		for(int i = 0; i < 30; i++) {
			System.out.println(nr.nextRandomNote());
		}
	}
}
