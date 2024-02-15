package com.example.Components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DueDate {
    private String dateString = null;
    private LocalDate dueDate;
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String errorMessage;
    

    public DueDate(String promptText) {
        if (!promptText.isEmpty()){
        dateString = promptText;
        }
    }
    public String getErrorMessage(){
        return errorMessage;
    }

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
