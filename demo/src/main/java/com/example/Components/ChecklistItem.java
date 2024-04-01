package com.example.Components;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ChecklistItem is a class representing an item in a checklist.
 */
public class ChecklistItem {
    private String description;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private String priority;
    private boolean isChecked;
    private boolean onTime;

    /**
     * ChecklistItem is the constructor for creating a new ChecklistItem object.
     * 
     * @param description The description of the checklist item.
     * @param dueDate The due date of the checklist item.
     * @param dueTime The due time of the checklist item.
     * @param priority The priority level of the checklist item.
     * @param isChecked A boolean indicating whether the checklist item is checked or not.
     * @param onTime A boolean indicating whether the checklist item is on time or not.
     */
    public ChecklistItem(String description, LocalDate dueDate, LocalTime dueTime, String priority, boolean isChecked, boolean onTime) {
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.priority = priority;
        this.isChecked = isChecked;
        this.onTime = onTime;
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
    public String getFormattedDateandTime() {
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

    // Getter method for onTime
    public boolean getOnTime() {
        return onTime;
    }

    //Setter method for onTime
    public void setOnTime(boolean status){
        onTime = status;
    }
}