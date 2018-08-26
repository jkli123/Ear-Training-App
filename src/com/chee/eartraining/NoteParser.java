package com.chee.eartraining;

public class NoteParser extends AbstractParser {

	public NoteParser() {
		super();
	}
	
	public String parseNote(String noteString) {
		String[] parseable = splitNoteIntoNameAndOctave(noteString);
		verifyNote(parseable);
		return ALL_NOTES.get(Integer.parseInt(parseable[0])) + parseable[1];
	}
	
	public String[] splitNoteIntoNameAndOctave(String note) {
		return note.split("-");
	}
	
	private void verifyNote(String[] note) {
		if(note.length != 2) {
			throw new IllegalArgumentException("Given note does not conform to parser standards.");
		}
		int noteName = Integer.parseInt(note[0]);
		int octaveName = Integer.parseInt(note[1]);
		if(noteName >= ALL_NOTES.size()) {
			throw new IllegalStateException("Given note is more than the possible number of notes in theory");
		}
		if(octaveName < 0 || octaveName > 11) {
			throw new IllegalStateException("Given octave " + octaveName +" is not possible to be played");
		}
	}
	
	public static void main(String[] args) {
		NoteParser np = new NoteParser();
		NoteRandomizer nr = new NoteRandomizer();
		for(int i = 0; i < 100; i++) {
			System.out.println(np.parseNote(nr.nextRandomNote()));
		}
	}

}
