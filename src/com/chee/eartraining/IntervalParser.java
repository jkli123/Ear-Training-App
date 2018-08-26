package com.chee.eartraining;

import java.util.Arrays;
import java.util.TreeMap;

public class IntervalParser extends AbstractParser {

	private NoteParser np;
	
	public IntervalParser() {
		super();
		np = new NoteParser();
	}
	
	private void verifyInterval(String interval) {
		if(!interval.contains("->")) {
			throw new IllegalArgumentException("Provided interval is not valid");
		}
	}
	
	public String[] parseInterval(String interval) {
		verifyInterval(interval);
		return interval.split("->");
	}
	
	public static void main(String[] args) {
		IntervalParser ip = new IntervalParser();
		NoteRandomizer nr = new NoteRandomizer();
		TreeMap<Integer, Integer> intervalMapping = new TreeMap<>();
		intervalMapping.put(0, 14);
		IntervalRandomizer ir = new IntervalRandomizer(intervalMapping);
		for(int i = 0; i < 100; i++) {
			System.out.println(Arrays.toString(ip.parseInterval(ir.nextRandomInterval())));		
		}
	}
}
