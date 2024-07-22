package model.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set<Character> triedLetters;
	private Set<String> triedWords;
	private int mistakes;

	public Player() {
		triedLetters = new HashSet<>();
		triedWords = new HashSet<>();
		mistakes = 0;
	}

	public void addTriedLetter(char letter) {
		triedLetters.add(letter);
	}

	public void addTriedWord(String word) {
		triedWords.add(word);
	}

	public boolean hasTriedLetter(char letter) {
		return triedLetters.contains(letter);
	}

	public boolean hasTriedWord(String word) {
		return triedWords.contains(word);
	}

	public void incrementMistakes() {
		mistakes++;
	}

	public int getMistakes() {
		return mistakes;
	}
}
