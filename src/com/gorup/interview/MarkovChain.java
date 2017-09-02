package com.gorup.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MarkovChain {
    private Map<String, List<String>> words;
    private String currentWord;
    private Random random;

    public MarkovChain() {
        this.words = new HashMap<String, List<String>>();
        this.currentWord = null;
        this.random = new Random();
    }

    public static void main(String[] args) {
        final MarkovChain mChain = new MarkovChain();
        mChain.addWord("it");
        mChain.addWord("was");
        mChain.addWord("the");
        mChain.addWord("best");
        mChain.addWord("of");
        mChain.addWord("times");
        mChain.addWord("it");
        mChain.addWord("was");
        mChain.addWord("the");
        mChain.addWord("worst");
        mChain.addWord("of");
        mChain.addWord("times");

        System.out.println(String.format("the %s", mChain.getWord("the")));
        System.out.println(String.format("the %s", mChain.getWord("the")));
        System.out.println(String.format("the %s", mChain.getWord("the")));
        System.out.println(String.format("the %s", mChain.getWord("the")));
        System.out.println(String.format("the %s", mChain.getWord("the")));
    }

    public void addWord(final String word) {
        if (currentWord == null) {
            currentWord = word;
            return;
        }

        System.out.println(String.format("%s << %s", currentWord, word));

        words.computeIfAbsent(currentWord, (__) -> new ArrayList<String>()).add(word);
        currentWord = word;
    }

    public String getWord(final String word) {
        return words.get(word).get(random.nextInt(words.get(word).size()));
    }
}
