package model.entites;

import java.io.IOException;

public interface IGame {
	void start();
	void saveGame(String filename) throws IOException;
}
