package model.entites;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GameGuiEN extends JFrame {
    private Game game;
    private JLabel currentGuessLabel;
    private JLabel messageLabel;
    private JTextField inputField;
    private JPanel keyboardPanel;
    private JLabel attemptsLabel;

    public GameGuiEN() {
        try {
            game = Game.loadGame("saved_game.dat");
        } catch (IOException | ClassNotFoundException e) {
            game = new Game();
            game.start();
        }
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Hangman");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    game.saveGame("saved_game.dat");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        currentGuessLabel = createLabel("Word: " + new String(game.getCurrentGuess()), 24);
        messageLabel = createLabel("Welcome to Hangman!", 18);
        attemptsLabel = createLabel("Attempts remaining: " + (6 - game.getPlayer().getMistakes()), 18);
        topPanel.add(currentGuessLabel, BorderLayout.NORTH);
        topPanel.add(messageLabel, BorderLayout.CENTER);
        topPanel.add(attemptsLabel, BorderLayout.SOUTH);

        JPanel inputPanel = new JPanel();
        inputField = new JTextField(1);
        JButton guessLetterButton = new JButton("Guess letter");
        JButton guessWordButton = new JButton("Guess word");
        JButton resetButton = new JButton("Restart game");
        guessLetterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessLetter();
            }
        });
        guessWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessWord();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        JPanel wordGuessPanel = new JPanel(new BorderLayout());
        wordGuessPanel.add(inputField, BorderLayout.CENTER);
        wordGuessPanel.add(guessWordButton, BorderLayout.EAST);
        inputPanel.add(wordGuessPanel);
        inputPanel.add(guessLetterButton);
        inputPanel.add(resetButton);

        keyboardPanel = createKeyboardPanel();

        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        getContentPane().add(keyboardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createLabel(String text, int fontSize) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }

    private JPanel createKeyboardPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 9));
        for (char c = 'a'; c <= 'z'; c++) {
            final char letter = c;
            JButton button = new JButton(String.valueOf(letter));
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guessLetter(letter);
                }
            });
            panel.add(button);
        }
        return panel;
    }

    private void guessLetter() {
        String input = inputField.getText().toLowerCase();
        if (input.length() == 1) {
            guessLetter(input.charAt(0));
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a single letter.");
        }
        inputField.setText("");
    }

    private void guessLetter(char letter) {
        game.guessLetter(letter);
        updateUI();
    }

    private void guessWord() {
        String input = inputField.getText().toLowerCase();
        if (input.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "Do not leave it blank.", "Warning",
        			JOptionPane.WARNING_MESSAGE);
        	return;
        }
        game.guessWord(input);
        updateUI();
        inputField.setText("");
    }

    private void updateUI() {
        currentGuessLabel.setText("Word: " + new String(game.getCurrentGuess()));
        attemptsLabel.setText("Attempts remaining: " + (6 - game.getPlayer().getMistakes()));

        if (game.isGameOver()) {
            if (game.isWin()) {
                messageLabel.setText("Congratulations! You guessed the word!");
            } else {
                messageLabel.setText("Game over! The word was: " + game.getCurrentWord());
            }
            
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetGame();
                }
            });
            timer.setRepeats(false); 
            timer.start();
        }
    }

    private void resetGame() {
        File saveFile = new File("saved_game.dat");
        if (saveFile.exists()) {
            saveFile.delete();
        }

        game = new Game();
        game.start();
        updateUI();
        
        for (Component comp : keyboardPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(true);
            }
        }
        inputField.setEnabled(true);
        messageLabel.setText("New game started");
    }
}
