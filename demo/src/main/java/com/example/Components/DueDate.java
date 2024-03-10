package com.example.Components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class DueDate extends VBox {
    private DatePicker datePicker;
    private LocalDate dueDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String errorMessage;

    public DueDate() {
        datePicker = new DatePicker();
        datePicker.setPromptText("yyyy/MM/dd");

        getChildren().addAll(datePicker);
    }

    public DueDate(String dateString) {
        try {
            dueDate = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            errorMessage = "Invalid date or format, please use YYYY/MM/DD";
        }
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDate setDate(){
        dueDate = datePicker.getValue();
        return dueDate;
    }

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
        errorMessage = "Date cannot be empty";
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
