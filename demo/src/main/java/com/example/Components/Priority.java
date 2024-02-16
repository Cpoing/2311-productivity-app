package com.example.Components;

public class Priority {
    
    
    private String priority;


    public Priority(String prioLevel){
        priority = prioLevel;
    }


    public boolean isValidPriority(String priorityText) {
        String prioLevel = priorityText.toLowerCase();
        return prioLevel.equals("low") || prioLevel.equals("medium") || prioLevel.equals("high");
    }
   
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    
    


}
