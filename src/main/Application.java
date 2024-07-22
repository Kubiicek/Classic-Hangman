package main;

import model.entites.Game;
import model.entites.IGame;

public class Application {
	public static void main(String[] args) {
		IGame game = new Game();
		game.start();
	}
}
