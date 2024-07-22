package model.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {
	private List<String> words;

	public WordList() {
		words = new ArrayList<>();
		words.add("apple");
		words.add("google");
		words.add("walmart");
		words.add("amazon");
		words.add("facebook");
	}

	public String getRandomWord() {
		Random random = new Random();
		return words.get(random.nextInt(words.size()));
	}
}
