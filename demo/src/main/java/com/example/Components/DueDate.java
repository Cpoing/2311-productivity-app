package com.example.Components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class that represents the due date feature for the tasks.
 * 
 * dateString is the date that the task is due as a String.
 * dueDate is the conversion of that String into the LocalDate attribute
 * currentDate is the currentDate in the LocalDate Attribute's format
 * formatter is the date formatter.
 * errorMessage is the errorMessage to be displayed in the event that the information provided is incorrect.
 */
public class DueDate {
    private String dateString = null;
    private LocalDate dueDate;
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String errorMessage;
    
/**
 * DueDate is the constructor for the DueDate Class
 * 
 * @param promptText is the date provided by the user.
 */
    public DueDate(String promptText) {
        if (!promptText.isEmpty()){
        dateString = promptText;
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
     * The isValid method is the method which checks if the information provided is correct.
     * @return True if the information provided is valid and false if it is invalid.
     */
    public boolean isValid(){
        if (dateString != null){
            try {
            dueDate = LocalDate.parse(dateString, formatter);
            int year = dueDate.getYear();
            int month = dueDate.getMonthValue();
            int day = dueDate.getDayOfMonth();
            if (month < 1 || month > 12 || day < 1 || day > LocalDate.of(year, month, 1).lengthOfMonth()) {
                throw new DateTimeParseException("Invalid date", dateString, 0);
            }
            } catch (DateTimeParseException e) {
            errorMessage = "Invalid date or format, please use YYYY/MM/DD";
            return false;
            }
    
    
        if(currentDate.isAfter(dueDate)){
            errorMessage = "Date has already passed";
            return false;
        } else{
            
            return true;
        }
        
    }
    errorMessage = "Date cannot be empty";
    return false;      
    
    }
}
