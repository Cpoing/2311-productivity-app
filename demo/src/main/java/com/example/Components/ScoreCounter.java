package com.example.Components;

import java.util.Map;

/**
 * ScoreCounter is the class that associates the score of the application with
 * its corresponding level of priority as a value.
 * 
 * counter is the total score.
 * scoreMap is the map used to map the following priorities: low, medium, high
 * to an integer value.
 */
public class ScoreCounter {
    int counter;
    final Map<String, Integer> scoreMap = Map.of("low", 100, "medium", 300, "high", 500);

    /**
     * The constructor that initializes the score at 0.
     */
    public ScoreCounter() {
        this.counter = 0;
    }

    // returns the rank of the user
    public String rankScore() {
        String rank = "";
        if (counter <= 500) {
            rank = "Rookie";
        } else if (counter <= 1000 && counter > 500) {
            rank = "Intermediate";
        } else if (counter <= 2000 && counter > 1000) {
            rank = "Master";
        } else if (counter <= 3000 && counter > 2000) {
            rank = "Grand Master";
        } else if (counter > 3000) {
            rank = "Legendary";
        }
        return rank;
    }

    // returns the score counter
    public int getCounter() {
        return this.counter;
    }

    // used to add score based on completed tasks (checkmarked)
    public void addScore(String score) {
        this.counter += scoreMap.get(score.toLowerCase());
    }

    // used to decrement score based on unfinished tasks past the due date
    public void subtractScore(String score) {
        this.counter -= scoreMap.get(score.toLowerCase());
    }
}
