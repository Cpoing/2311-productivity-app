package com.example.Components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Priority is the class used to give priorities to tasks.
 * 
 * priority is the Priority of the task.
 */
public class Priority {
    
    
    private String priority;
    private Label error;

	/**
	 * Constructor for Priority Class
	 * @param prioLevel is the priority level of the task.
	 */
    public Priority(String prioLevel){
        this.priority = prioLevel;
        this.error = new Label();
        this.error.setTextFill(Color.RED);
    }

    /**
     * isValidPriority checks whether the priority that has been input is valid.
     * @param priorityText the priority level that has been input.
     * @return True if the priority is valid, false if it is invalid.
     */
    public boolean isValidPriority(String priorityText) {
        String prioLevel = priorityText.toLowerCase();
        return prioLevel.equals("low") || prioLevel.equals("medium") || prioLevel.equals("high");
    }
   /**
    * getPriority is the getter method for the priority field.
    * @return The priority of the task.
    */
    public String getPriority() {
        return priority;
    }
    
    private void clearError() {
        error.setText("");
    }
    
    public void setError(String errorMessage) {
        error.setText(errorMessage);
    }
    /**
     * setPriority is the setter method for the priority field.
     * @param priority is the priority that the user has input.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    // Creates an HBox that contain the buttons for choosing task priority 
    public HBox createPriorityButtons() {
    	Label priorityLabel = new Label("Priority:");
    	Button lowButton = new Button("Low");
        lowButton.setOnAction(e -> {
            setPriority("Low");
            clearError();
        });

        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(e -> {
            setPriority("Medium");
            clearError();
        });

        Button highButton = new Button("High");
        highButton.setOnAction(e -> {
            setPriority("High");
            clearError();
        });
        
       
        
        HBox priorityButtons = new HBox(priorityLabel,lowButton, mediumButton, highButton,error);
        priorityButtons.setSpacing(10);
        
        
        return priorityButtons;
    }
}
