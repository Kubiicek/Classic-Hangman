package model.entites;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Game implements IGame, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_ATTEMPTS = 6;
    private WordList wordList;
    private Player player;
    private String currentWord;
    private char[] currentGuess;

    public Game() {
        wordList = new WordList();
        player = new Player();
    }

    @Override
    public void start() {
        currentWord = wordList.getRandomWord();
        if (currentWord != null) {
            currentGuess = new char[currentWord.length()];
            Arrays.fill(currentGuess, '_');
        } else {
            currentGuess = new char[0];
        }
    }

    public void guessLetter(char guess) {
        if (player.hasTriedLetter(guess)) {
            JOptionPane.showMessageDialog(null, "Você já tentou essa letra.");
            return;
        } else {
            player.addTriedLetter(guess);
            checkLetter(guess);
        }
    }

    public void guessWord(String guess) {
        if (player.hasTriedWord(guess)) {
            JOptionPane.showMessageDialog(null, "Você já tentou essa palavra.");
            return;
        } else {
            player.addTriedWord(guess);
            checkWord(guess);
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
        }
    }

    private void checkWord(String guess) {
        if (currentWord.equals(guess)) {
            currentGuess = currentWord.toCharArray();
        } else {
            player.incrementMistakes();
        }
    }

    public boolean isGameOver() {
        return isWin() || player.getMistakes() >= MAX_ATTEMPTS;
    }

    public boolean isWin() {
        return currentWord.equals(new String(currentGuess));
    }

    @Override
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

    public char[] getCurrentGuess() {
        return currentGuess;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public Player getPlayer() {
        return player;
    }
}