package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.Components.ChecklistItem;
import com.example.Components.DueDate;
import com.example.Components.Notes;
import com.example.Components.Priority;
import com.example.Components.ScoreCounter;


import javafx.scene.layout.VBox;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App {
	
	/**
	 * toDoList is a Checkbox list of tasks needed to be completed.
	 * newItemField is a text field for the description of a new task.
	 * dueDate is the text field for the due date of a new task.
	 * itemPrio is the tasks item priority.
	 * newItemErrortext, dueDateErrorText & itemPrioError are the text dialogues for errors.
	 * score is the users' score from the completion of tasks.
	 * disp is a TimerDisplay object which contains the timer hbox and text attributes.
	 */
    private ListView<CheckBox> toDoList;
    private ArrayList<ChecklistItem> checklistItems = new ArrayList<>();
    private TextField newItemField;
    private DueDate dueDateComponent;
    private TextField itemPrio;
    private Text newItemErrorText, dueDateErrorText, itemPrioError;
    private ScoreCounter score;
    private TimerDisplay disp;
    private Timeline timeline; 
    
    public App(Stage stage) {
    	// root is the Root Border Pane
        BorderPane root = new BorderPane();
        // bottom is border pane for the bottom portion so that elements can be aligned at top-right, top-left, bottom-left, bottom-right
        BorderPane bottom = new BorderPane();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateScoresAndColors()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play(); // Start the timeline
    
        toDoList = new ListView<>();

        newItemField = new TextField();
        newItemField.setPromptText("Enter a new item");
        newItemErrorText = new Text();

        dueDateComponent = new DueDate();
        dueDateErrorText = new Text();

        itemPrio = new TextField();
        itemPrio.setPromptText("Priority(Low,Medium,High)");
        itemPrioError = new Text();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItem());

        Button timerButton = new Button("Pomodoro Timer");
        timerButton.setOnAction(e -> updateTime());
        bottom.setRight(timerButton);

        Button noteButton = new Button("Notes");
        noteButton.setOnAction(e -> openNoteWindow());
        bottom.setTop(noteButton);

        // created a VBox to resolve issue of button overlapping
        VBox buttonbox = new VBox(timerButton, noteButton);
        bottom.setRight(buttonbox);

        score = new ScoreCounter();
        Text scoreCount = new Text("Your Score: " + score.getCounter());

        root.setCenter(toDoList);
        root.setRight(addButton);
        root.setLeft(scoreCount);
        root.setBottom(bottom);

        HBox date = new HBox(dueDateComponent, dueDateErrorText);
        HBox item = new HBox(newItemField, newItemErrorText);
        HBox prio = new HBox(itemPrio, itemPrioError);

        VBox inputFields = new VBox(item, date, prio);
        
        inputFields.setSpacing(5);

        bottom.setLeft(inputFields);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("To-Do List App");
        stage.setScene(scene);
        stage.show();
    }
    
   /* private void openPomodoroTimer(Stage timerStage) {
    	BorderPane winTop = new BorderPane();
    	BorderPane winBott = new BorderPane();
    	Scene pomdoroScene = new Scene(winTop, 600, 400);
    	
    	//Button shortBreak = new Button("Short Break"); // 5 Minutes
    	//Button longBreak = new Button("Long Break"); // 10 Minutes
    	
    	final int normalSeconds = 1500; // 25 minutes * 60 seconds = 1500 seconds
    	//final int shortBreakSeconds = 300; // 5 minutes * 60 seconds = 300 seconds
    	//final int longBreakSeconds = 600; // 10 minutes * 60 seconds = 600 seconds
    	
    	Timer myTimer = new Timer();
    	myTimer.scheduleAtFixedRate(myTimerTask, 0, 1000);
    	
    	timerStage.setTitle("Pomodoro Timer");
    	timerStage.setScene(pomdoroScene);
    	timerStage.show();
    }*/
    
    /**
     * addNewItem is the method used to add a new task into the checklist.
     * 
     * newItemText is the text of the task.
     * date is the due date for the task.
     * priorityText describes whether the task is of Low, Medium, or High Priority.
     */
    private void addNewItem() {
        String newItemText = newItemField.getText().trim();
        LocalDate selectedDate = dueDateComponent.getDatePicker().getValue(); // Retrieve selected date
        String priorityText = itemPrio.getText().trim();
        LocalTime selectedTime = dueDateComponent.getTime(); 

        Priority prio = new Priority(priorityText);

        // Instantiate Variables
        newItemErrorText.setText("");
        dueDateErrorText.setText("");
        itemPrioError.setText("");

        dueDateComponent.setDate();
            if (!dueDateComponent.isValid()) {
                dueDateErrorText.setText(dueDateComponent.getErrorMessage());
            }

        if (newItemText.isEmpty()) {
            newItemErrorText.setText("Item name cannot be empty");
        }        

        if (!prio.isValidPriority(priorityText)) {
            itemPrioError.setText("Priority must be 'Low', 'Medium', or 'High'");
        }

        if (!newItemText.isEmpty() && dueDateComponent.isValid() && prio.isValidPriority(priorityText)) {
            String formattedDate = dueDateComponent.getFormattedDate();
            String combinedText = newItemText + " - Due: " + formattedDate + " " + selectedTime + " - Priority: " + priorityText;
            ChecklistItem newItem = new ChecklistItem(newItemText, selectedDate, selectedTime, priorityText);
            checklistItems.add(newItem);
/* 
            CheckBox newItemCheckbox = new CheckBox(combinedText);
            newItemCheckbox.selectedProperty().addListener((observable, oldValue, checked) -> {
                updateScoreCounter(priorityText, selectedDate.isBefore(LocalDate.now()), checked);
            });*/

            CheckBox newItemCheckbox = new CheckBox(combinedText);
        newItemCheckbox.selectedProperty().addListener((observable, oldValue, isChecked) -> {
            if (isChecked) {
                updateScoreCounter(priorityText, selectedDate.isBefore(LocalDate.now()), true);
                newItemCheckbox.setDisable(true); // Disable checkbox once checked
            } else {
                updateScoreCounter(priorityText, selectedDate.isBefore(LocalDate.now()), false);
                newItemCheckbox.setDisable(false); // Enable checkbox if unchecked
            }
        });

            int firstCheckedIndex = 0;
            for (CheckBox checkbox : toDoList.getItems()) {
                if (checkbox.isSelected()) {
                    break;
                } else
                    firstCheckedIndex++;
            }
            
            // Get fields ready for next item
            toDoList.getItems().add(firstCheckedIndex, newItemCheckbox);
            newItemField.clear();
            dueDateComponent.getDatePicker().setValue(null); // Clear selected date
            itemPrio.clear();
        }
    }

    /**
     * updateScoreCounter is the method used to update the users' score
     * 
     * @param priorityText is the String with the corresponding priority level of the task (Low, Medium or High).
     * @param dueDatePassed is the boolean representing whether the due date has passed or not.
     * @param ischecked is the boolean for whether the task is already completed or not to avoid duplicate additions to the score.
     */

     private void updateScoresAndColors() {
        // Update the score counter
        Text scoreCount = new Text("Your Score: " + score.getCounter());
        ((BorderPane) newItemField.getScene().getRoot()).setLeft(scoreCount); // Update the score on the GUI
    
        // Check if any items are overdue and update their appearance
        for (ChecklistItem item : checklistItems) {
            if (!item.isChecked()) { // Check only if the item is not already checked
                LocalDate dueDate = item.getDueDate();
                LocalTime dueTime = item.getDueTime();
                LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);
    
                if (LocalDateTime.now().isAfter(dueDateTime) || LocalDate.now().isAfter(dueDate)) {
                    // Find the corresponding checkbox for the item
                    for (int i = 0; i < toDoList.getItems().size(); i++) {
                        CheckBox checkbox = toDoList.getItems().get(i);
                        if (checkbox.getText().startsWith(item.getDescription())) {
                            checkbox.setStyle("-fx-text-fill: red;"); // Turn the text color of overdue items to red
                            checkbox.setSelected(true); 
                            item.setChecked(true); 
                            score.subtractScore(item.getPriority()); 
                            break;
                        }
                    }
                }
            }
        }
    }

    
    private void updateScoreCounter(String priorityText, boolean dueDatePassed, boolean ischecked) {
        if (ischecked && !dueDatePassed) {
            score.addScore(priorityText); // Add score only if the item is checked and the due date hasn't passed
        } else if (!ischecked && dueDatePassed) {
            score.subtractScore(priorityText); // Deduct score if the item is unchecked and the due date has passed
        }
        Text scoreCount = new Text("Your Score: " + score.getCounter());
    ((BorderPane) newItemField.getScene().getRoot()).setLeft(scoreCount); 
    }
    
    
    /**
     * updateTime is the method used to update the timer.
     * 
     * Task is used to update the timer in a new thread (opting not to use the system thread since it is already being used).
     */
    private void updateTime() {
    	// GUI For Timer
    	disp = new TimerDisplay();
    	Stage pomodoroStage = new Stage();
    	Scene scene = new Scene(disp, 600, 400);
    	
    	// Update Timer Count using Task Class
    	Task<Object> task = new Task<>() {
        	@Override
        	protected Object call(){
        		for(int i = 1500; i >= 0; i--) {
        			try {
        				// String format for time (2 digits per number seperated by :), i/60 is the minutes, i%60 is the seconds.
        				updateMessage(String.format("%02d:%02d", i/60, i%60));
        				Thread.sleep(1000);
        			}
        			catch(Exception e){
        				Thread.currentThread().interrupt();
        			}
        		}
        		return null;
        	}
        };
        
        new Thread(task).start(); // Must use new thread, using System thread causes JavaFX to delay since it is already using Sys thread.
        
        disp.getText().textProperty().bind(task.messageProperty()); //Get the text from task and bind it to the time variable in class TimerDisplay
        
        pomodoroStage.setTitle("Pomodoro Timer");
    	pomodoroStage.setScene(scene);
    	pomodoroStage.show();
    }
    /**
     * TimerDisplay is the class for text attributes for the pomodoro timer.
     * 
     * time is the text that displays time
     * 
     * getText() returns the time as a Text attribute
     */
    public class TimerDisplay extends HBox{
    	private Text time;
    	public TimerDisplay() {
    		time = new Text("");
    		getChildren().add(time);
    		setAlignment(Pos.CENTER);
    	}
    	public Text getText() {
    		return time;
    	}
    }    
    /**
     * openNoteWindow is the class for the note feature
     * 
     * creates a text area for user to input notes
     * 
     * creates save and close buttons, saves notes to terminal (database future)
     * 
     */

    private void openNoteWindow() {
        Stage noteStage = new Stage();
        BorderPane noteLayout = new BorderPane();

        
        TextArea noteTextArea = new TextArea(); 
        noteLayout.setCenter(noteTextArea);
        
        Button saveButton = new Button("Save");
        Button closeButton = new Button("Close");

       
        HBox buttonBox = new HBox(saveButton, closeButton); 
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT); 

        
        noteLayout.setBottom(buttonBox); 

        saveButton.setOnAction(e -> {
         
            String noteContent = noteTextArea.getText(); 

            System.out.println("Note saved: " + noteContent); 
        });

      
        closeButton.setOnAction(e -> noteStage.close()); 

        Scene noteScene = new Scene(noteLayout, 400, 300);
        noteStage.setScene(noteScene);
        noteStage.setTitle("Notes");
        noteStage.show();
    }

}

	