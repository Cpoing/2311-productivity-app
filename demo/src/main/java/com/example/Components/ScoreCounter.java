package com.example.Components;

import java.util.Map;

public class ScoreCounter {
    int counter;
    final Map<String, Integer> scoreMap = Map.of("Low", 100, "Medium", 300, "High", 500);

    public ScoreCounter() {
        this.counter = 0;
    }

    // returns the score counter
    public int getCounter() {
        return this.counter;
    }

    // used to add score based on completed tasks (checkmarked)
    public void addScore(String score) {
        this.counter += scoreMap.get(score);
    }

    // used to decrement score based on unfinished tasks past the due date
    public void subtractScore(String score) {
        this.counter -= scoreMap.get(score);
    }
}
