package main;

import javax.swing.SwingUtilities;

import model.entites.GameGUI;

public class Application {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::createAndShowGUI);
    }
	
	private static void createAndShowGUI() {
        new GameGUI();
    }
}
