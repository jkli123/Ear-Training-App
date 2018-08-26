package com.chee.eartraining;

import java.util.TreeMap;

public abstract class AbstractParser implements BasicParser {

	protected final TreeMap<Integer, String> ALL_NOTES;
	
	public AbstractParser() {
		ALL_NOTES = new TreeMap<> ();
		ALL_NOTES.put(0, "C");
		ALL_NOTES.put(1, "C#");
		ALL_NOTES.put(2, "D");
		ALL_NOTES.put(3, "D#");
		ALL_NOTES.put(4, "E");
		ALL_NOTES.put(5, "F");
		ALL_NOTES.put(6, "F#");
		ALL_NOTES.put(7, "G");
		ALL_NOTES.put(8, "G#");
		ALL_NOTES.put(9, "A");
		ALL_NOTES.put(10, "A#");
		ALL_NOTES.put(11, "B");
	}
}
