package model.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList implements Serializable {
    private static final long serialVersionUID = 1L;
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