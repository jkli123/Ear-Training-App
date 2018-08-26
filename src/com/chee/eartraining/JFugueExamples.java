package com.chee.eartraining;

import java.util.Arrays;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class JFugueExamples {

	public void helloWorld() {
		Player player = new Player();
		player.play("C D E F G A B");
	}
	
	public void helloWorld2() {
		Player player = new Player();
		player.play("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq  V1 I[Flute] Rw | Rw | GmajQQQ CmajQ");
	}
	
	public void patternIntro() {
		Pattern p1 = new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
		Pattern p2 = new Pattern("V1 I[Flute] Rw | Rw | GmajQQQ CmajQ");
		Player player = new Player();
		player.play(p1, p2);
	}
	
	public void patternIntro2() {
		Pattern p1 = new Pattern("Eq Ch. | Eq Ch. | Dq Eq Dq Cq").setVoice(0).setInstrument("Piano");
		Pattern p2 = new Pattern("Rw | Rw | GmajQQQ CmajQ").setVoice(1).setInstrument("Guitar");
		Player player = new Player();
		player.play(p1, p2);
	}
	
	public void chordProgressions() {
		ChordProgression cp = new ChordProgression("I IV V");
		Chord[] chords = cp.setKey("C").getChords();
		for(Chord chord : chords) {
			System.out.println("Chord "+chord+"has these notes: ");
			Note[] notes = chord.getNotes();
			for(Note note: notes) {
				System.out.print(note+" ");
			}
			System.out.println();
		}
		Player player = new Player();
		player.play(cp);
	}
	
	public void advanceChords() {
		ChordProgression cp = new ChordProgression("I IV V");
		Player player = new Player();
		//player.play(cp.eachChordAs("$0q $1q $2q Rq"));
		//player.play(cp.allChordsAs("$0q $0q $0q $0q $1q $1q $2q $0q"));
		player.play(cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0")
				 .eachChordAs("V0 $0s $1s $2s Rs V1 $!q"));
	}

	public static void main(String[] args) {
	}
}
