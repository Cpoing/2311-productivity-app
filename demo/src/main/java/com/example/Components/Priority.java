package com.example.Components;
/**
 * Priority is the class used to give priorities to tasks.
 * 
 * priority is the Priority of the task.
 */
public class Priority {
    
    
    private String priority;

	/**
	 * Constructor for Priority Class
	 * @param prioLevel is the priority level of the task.
	 */
    public Priority(String prioLevel){
        priority = prioLevel;
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
    /**
     * setPriority is the setter method for the priority field.
     * @param priority is the priority that the user has input.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
