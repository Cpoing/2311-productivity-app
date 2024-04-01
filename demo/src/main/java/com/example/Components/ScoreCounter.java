package com.example.Components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.DB.DB;

/**
 * ScoreCounter class represents a counter for scores and provides methods to add, subtract scores,
 * and retrieve score data.
 */
public class ScoreCounter {
    private IntegerProperty counter;
    private final Map<String, Integer> scoreMap = Map.of("low", 100, "medium", 300, "high", 500);
    public Map<LocalDateTime, Integer> scoreData = new HashMap<>();
    private DB db;
    private String ID;

    /**
     * Constructor for ScoreCounter.
     * 
     * @param ID The user ID associated with the scores.
     */
    public ScoreCounter(String ID) {
        this.counter = new SimpleIntegerProperty(0);
        this.db = new DB();
        this.ID = ID;
    }

    /**
     * Method to determine the rank based on the current score.
     * 
     * @return The rank based on the current score.
     */
    public String rankScore() {
        int counterValue = this.getCounter(); // Get the integer value from the property
        String rank = "";
        if (counterValue <= 500) {
            rank = "Rookie";
        } else if (counterValue <= 1000) {
            rank = "Intermediate";
        } else if (counterValue <= 2000) {
            rank = "Master";
        } else if (counterValue <= 3000) {
            rank = "Grand Master";
        } else {
            rank = "Legendary";
        }
        return rank;
    }

    /**
     * Getter method for the counterProperty.
     * 
     * @return The IntegerProperty representing the score counter.
     */
    public IntegerProperty counterProperty() {
        return counter;
    }

    /**
     * Method to add score to the counter.
     * 
     * @param score The score to add.
     */
    public void addScore(String score) {
        int currentCounter = this.getCounter(); // Get the current counter value
        int scoreToAdd = scoreMap.get(score.toLowerCase());
        this.counter.set(currentCounter + scoreToAdd); // Set the updated value to the property
        db.updateScore(this.ID, this.counter.get());
        scoreData.put(LocalDateTime.now(), currentCounter + scoreToAdd); // Record score data
    }

    /**
     * Method to subtract score from the counter.
     * 
     * @param score The score to subtract.
     */
    public void subtractScore(String score) {
        int currentCounter = this.getCounter(); // Get the current counter value
        int scoreToSubtract = scoreMap.get(score.toLowerCase());
        this.counter.set(currentCounter - scoreToSubtract); // Set the updated value to the property
        db.updateScore(this.ID, this.counter.get());
        scoreData.put(LocalDateTime.now(), currentCounter - scoreToSubtract); // Record score data
    }

    /**
     * Getter method to retrieve score data.
     * 
     * @return A map containing score data with timestamps.
     */
    public Map<LocalDateTime, Integer> getScoreData() {
        return scoreData;
    }

    /**
     * Method to get the current time.
     * 
     * @return The current LocalDateTime.
     */
    public LocalDateTime getTime() {
        return LocalDateTime.now(); // Return current time
    }

    /**
     * Method to get the current score.
     * 
     * @return The current score.
     */
    public int getCounter() {
        return db.getScore(this.ID);
    }
}