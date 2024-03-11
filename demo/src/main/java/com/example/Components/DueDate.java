package com.example.Components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
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
        HBox dueDateBox = new HBox(datePicker,hourComboBox, minuteComboBox, amPmComboBox);
        dueDateBox.setSpacing(10);
        getChildren().addAll(dueDateBox);
    }

    public LocalTime getTime() {
        int hour = hourComboBox.getValue();
        if (amPmComboBox.getValue().equals("PM")) {
            hour += 12; // Convert to 24-hour format for PM
        }
        int minute = minuteComboBox.getValue();
        return LocalTime.of(hour % 24, minute); // Ensure hour is within 0-23 range
    }

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
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(dueDate)) {
                errorMessage = "Date has already passed";
                return false;
            } else {
                return true;
            }
        }
        errorMessage = "Date is invalid or Date is empty";
        return false;
    }

    

    public DatePicker getDatePicker() {
        return datePicker;
    }
    public String getFormattedDate() {
        LocalDate date = datePicker.getValue();
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        }
        return ""; // Return empty string if no date selected
    }

    
}
