package com.example.Components;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChecklistItem {
    private String description;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private String priority;
    private boolean isChecked;

    public ChecklistItem(String description, LocalDate dueDate, LocalTime dueTime, String priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.priority = priority;
        this.isChecked = false; // Initially, the item is not checked
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for dueDate
    public LocalDate getDueDate() {
        return dueDate;
    }

    // Getter method for dueTime
    public LocalTime getDueTime() {
        return dueTime;
    }

    // Getter method for priority
    public String getPriority() {
        return priority;
    }

    // Getter method for isChecked
    public boolean isChecked() {
        return isChecked;
    }

    // Setter method for isChecked
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}