package com.example.Components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.DB.DB;

public class ScoreCounter {
    private IntegerProperty counter;
    private final Map<String, Integer> scoreMap = Map.of("low", 100, "medium", 300, "high", 500);
    public Map<LocalDateTime, Integer> scoreData = new HashMap<>();
    private DB db;
    private String ID;

    public ScoreCounter(String ID) {
        this.counter = new SimpleIntegerProperty(0);
        this.db = new DB();
        this.ID = ID;
    }

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

    public IntegerProperty counterProperty() {
        return counter;
    }

    public void addScore(String score) {
        int currentCounter = this.getCounter(); // Get the current counter value
        int scoreToAdd = scoreMap.get(score.toLowerCase());
        this.counter.set(currentCounter + scoreToAdd); // Set the updated value to the property
        db.updateScore(this.ID, this.counter.get());
        scoreData.put(LocalDateTime.now(), currentCounter + scoreToAdd); // Record score data
    }

    public void subtractScore(String score) {
        int currentCounter = this.getCounter(); // Get the current counter value
        int scoreToSubtract = scoreMap.get(score.toLowerCase());
        this.counter.set(currentCounter - scoreToSubtract); // Set the updated value to the property
        db.updateScore(this.ID, this.counter.get());
        scoreData.put(LocalDateTime.now(), currentCounter - scoreToSubtract); // Record score data
    }

    public Map<LocalDateTime, Integer> getScoreData() {
        return scoreData;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.now(); // Return current time
    }

    public int getCounter() {
        return db.getScore(this.ID);
    }
}