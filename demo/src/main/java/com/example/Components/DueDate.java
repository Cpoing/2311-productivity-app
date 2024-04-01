package com.example.Components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * The class that represents the due date feature for the tasks.
 * 
 * dateString is the date that the task is due as a String.
 * dueDate is the conversion of that String into the LocalDate attribute
 * currentDate is the currentDate in the LocalDate Attribute's format
 * formatter is the date formatter.
 * errorMessage is the errorMessage to be displayed in the event that the information provided is incorrect.
 */
public class DueDate extends VBox {
    private LocalDate dueDate;
    private LocalTime dueTime;
    private DatePicker datePicker;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String errorMessage;
    private ComboBox<Integer> hourComboBox;
    private ComboBox<Integer> minuteComboBox;
    private ComboBox<String> amPmComboBox;

/**
 * DueDate is the constructor for the DueDate Class
 * 
 * @param promptText is the date provided by the user.
 */    public DueDate() {
        datePicker = new DatePicker();
        datePicker.setPromptText("yyyy/MM/dd");
        hourComboBox = new ComboBox<>();
        minuteComboBox = new ComboBox<>();
        amPmComboBox = new ComboBox<>();

        hourComboBox.setPromptText("Hour");
        minuteComboBox.setPromptText("Minute");
        amPmComboBox.setPromptText("AM/PM");

        for (int hour = 1; hour <= 12; hour++) {
            hourComboBox.getItems().add(hour);
        }
        for (int minute = 0; minute < 60; minute += 5) {
            minuteComboBox.getItems().add(minute);
        }
        
        amPmComboBox.getItems().addAll("AM", "PM");
        // Create an HBox for the date picker
        HBox datePickerBox = new HBox(datePicker);
        datePickerBox.setSpacing(10);

        // Create an HBox for the time components
        HBox timeBox = new HBox(hourComboBox, minuteComboBox, amPmComboBox);
        timeBox.setSpacing(5);

        // Create a VBox to stack the date picker and time components vertically
        VBox dateTimeBox = new VBox(datePickerBox, timeBox);
        dateTimeBox.setSpacing(5);

        // Now, add the VBox containing date picker and time components to the parent VBox
        getChildren().addAll(dateTimeBox);
    }
    
/**
 * getTime is the method that retrieves the selected time from the UI components and converts it to LocalTime.
 * 
 * @return The selected time as LocalTime.
 */
    public LocalTime getTime() {
        int hour = hourComboBox.getValue();
        int minute = minuteComboBox.getValue();
        
        // Adjust hour for 12-hour format
        if (amPmComboBox.getValue().equals("PM") && hour != 12) {
            hour += 12; // Convert to 24-hour format for PM, except if it's 12 PM
        } else if (amPmComboBox.getValue().equals("AM") && hour == 12) {
            hour = 0; // Convert 12 AM to 00 (midnight)
        }

        dueTime = LocalTime.of(hour % 24, minute);
        
        return dueTime; // Ensure hour is within 0-23 range
    }
/**
 * DueDate is the constructor that initializes a DueDate object with a given date string.
 * 
 * @param dateString The date string in "YYYY/MM/DD" format.
 */
    public DueDate(String dateString) {
        try {
            dueDate = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            errorMessage = "Invalid date or format, please use YYYY/MM/DD";
        }
    }
    /**
     * getErrorMessage is the method that gets the error message.
     * @return error message
     */
    public String getErrorMessage(){
        return errorMessage;
    }
    
/**
 * isValid is the method that checks if the due date and time provided are valid.
 * 
 * @return True if the due date and time are valid, otherwise False.
 */
    public LocalDate setDate(){
        dueDate = datePicker.getValue();
        return dueDate;
    }
    /**
     * The isValid method is the method which checks if the information provided is correct.
     * @return True if the information provided is valid and false if it is invalid.
     */
    public boolean isValid() {
        if (dueDate != null) {
            LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);
            if (dueDateTime.isBefore(LocalDateTime.now())) {
                errorMessage = "Date has already passed";
                return false;
            } else {
                return true;
            }
        }
        errorMessage = "Date is invalid or Date is empty";
        return false;
    }

/**
 * getDatePicker is the method that retrieves the DatePicker component associated with the DueDate.
 * 
 * @return The DatePicker component.
 */
    public DatePicker getDatePicker() {
        return datePicker;
    }

    /**
 * getFormattedDate is the method that formats the due date into a human-readable string.
 * 
 * @return The formatted due date as a String.
 */
    public String getFormattedDate() {
        LocalDate date = datePicker.getValue();
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        }
        return ""; // Return empty string if no date selected
    }

    
}
