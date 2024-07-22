package model.entites;

import java.io.*;
import java.util.Scanner;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_ATTEMPS = 6;
	private static int attemps = 6;
	private WordList wordList;
	private Player player;
	private String currentWord;
	private char[] currentGuess;

	public Game() {
		wordList = new WordList();
		player = new Player();
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		currentWord = wordList.getRandomWord();
		currentGuess = new char[currentWord.length()];
		for (int i = 0; i < currentGuess.length; i++) {
			currentGuess[i] = '_';
		}

		while (!isGameOver()) {
			System.out.println("Current guess: " + new String(currentGuess));
			System.out.println("You have " + attemps + " Attemps");
			System.out.println("Do you want to guess a (L)etter or the (W)ord?");
			String choice = scanner.nextLine().toUpperCase();

			switch (choice) {
			case "L" -> guessLetter(scanner);
			case "W" -> guessWord(scanner);
			default -> System.out.println("Invalid choice. Please enter 'L' for letter or 'W' for word.");
			}
		}

		if (isWin()) {
			System.out.println("Congratulations You've guessed the word: " + currentWord);
		} else {
			System.out.println("Game over! The word was: " + currentWord);
		}
	}

	private void guessLetter(Scanner scanner) {
		System.out.println("Enter a letter:");
		String input = scanner.nextLine().toLowerCase();
		if (input.length() != 1) {
			System.out.println("Please enter a single letter.");
			return;
		}
		char guess = input.charAt(0);
		if (player.hasTriedLetter(guess)) {
			System.out.println("You already tried that letter.");
		} else {
			player.addTriedLetter(guess);
			checkLetter(guess);
		}
	}

	private void guessWord(Scanner scanner) {
		System.out.println("Enter the word:");
		String input = scanner.nextLine().toLowerCase();
		if (input.length() != currentWord.length()) {
			System.out.println("The word length doesn't match.");
			return;
		}
		if (player.hasTriedWord(input)) {
			System.out.println("You already tried that word.");
		} else {
			player.addTriedWord(input);
			checkWord(input);
		}
	}

	private void checkLetter(char guess) {
		boolean found = false;
		for (int i = 0; i < currentWord.length(); i++) {
			if (currentWord.charAt(i) == guess) {
				currentGuess[i] = guess;
				found = true;
			}
		}
		if (!found) {
			player.incrementMistakes();
			attemps--;
			System.out.println("You have " + attemps + " Attemps");
		}
	}

	private void checkWord(String guess) {
		if (currentWord.equals(guess)) {
			currentGuess = currentWord.toCharArray();
		} else {
			player.incrementMistakes();
			attemps--;
			System.out.println("You have " + attemps + " Attemps");
		}
	}

	private boolean isGameOver() {
		return isWin() || player.getMistakes() >= MAX_ATTEMPS;
	}

	private boolean isWin() {
		return currentWord.equals(new String(currentGuess));
	}
	
	public void saveGame(String filename) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(this);
		}
	}
	
	public static Game loadGame(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			return (Game) in.readObject();
		}
	}
}
