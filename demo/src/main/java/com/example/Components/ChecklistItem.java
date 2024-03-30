package com.example.Components;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChecklistItem {
    private String description;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private String priority;
    private boolean isChecked;

    public ChecklistItem(String description, LocalDate dueDate, LocalTime dueTime, String priority, boolean isChecked) {
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.priority = priority;
        this.isChecked = isChecked;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for dueDate
    public LocalDate getDueDate() {
        return dueDate;
    }
    // Getter for dueDate in String format
    public String getFormattedDate() {
    String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    String formattedTime = dueTime.format(DateTimeFormatter.ofPattern("HH:mm")); // Assuming time format is "hh:mm AM/PM"
    return formattedDate + " " + formattedTime;
    }

    // Getter method for dueTime
    public LocalTime getDueTime() {
        return dueTime;
    }

    // Getter method for priority
    public String getPriority() {
        return priority.toLowerCase();
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