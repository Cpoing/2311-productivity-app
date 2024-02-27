package com.example;

import com.example.Components.DueDate;
import com.example.Components.Priority;
import com.example.Components.ScoreCounter;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private TextField newItemField;
    private TextField dueDate;
    private TextField itemPrio;
    private Text newItemErrorText, dueDateErrorText, itemPrioError;
    private ScoreCounter score;
    private TimerDisplay disp;
    
    public App(Stage stage) {
    	// root is the Root Border Pane
        BorderPane root = new BorderPane();
        // bottom is border pane for the bottom portion so that elements can be aligned at top-right, top-left, bottom-left, bottom-right
        BorderPane bottom = new BorderPane();
        
        toDoList = new ListView<>();

        newItemField = new TextField();
        newItemField.setPromptText("Enter a new item");
        newItemErrorText = new Text();

        dueDate = new TextField();
        dueDate.setPromptText("YYYY/MM/DD");
        dueDateErrorText = new Text();

        itemPrio = new TextField();
        itemPrio.setPromptText("Priority(Low,Medium,High)");
        itemPrioError = new Text();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItem());

        Button timerButton = new Button("Pomodoro Timer");
        timerButton.setOnAction(e -> updateTime());
        bottom.setRight(timerButton);
        
        score = new ScoreCounter();
        Text scoreCount = new Text("Your Score: " + score.getCounter());

        root.setCenter(toDoList);
        root.setRight(addButton);
        root.setLeft(scoreCount);
        root.setBottom(bottom);
        
        HBox date = new HBox(dueDate, dueDateErrorText);
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
        DueDate date = new DueDate(dueDate.getText().trim()); 
        String priorityText = itemPrio.getText().trim();

        Priority prio = new Priority(priorityText);

        // Instantiate Variables
        newItemErrorText.setText("");
        dueDateErrorText.setText("");
        itemPrioError.setText("");
        
        if (newItemText.isEmpty()) {
            newItemErrorText.setText("Item name cannot be empty");
        }

        if (!date.isValid()) {
            dueDateErrorText.setText(date.getErrorMessage());
        }

        if (!prio.isValidPriority(priorityText)) {
            itemPrioError.setText("Priority must be 'Low', 'Medium', or 'High'");
        }
        
        // If All fields have the correct conditions...
        
        if (!newItemText.isEmpty() && date.isValid() == true && prio.isValidPriority(priorityText) == true) {
            String combinedText = newItemText + " - Due: " + dueDate.getText() + "- Priority: " + priorityText;

            CheckBox newItemCheckbox = new CheckBox(combinedText);
            newItemCheckbox.selectedProperty().addListener((observable, oldValue, checked) -> {
                updateScoreCounter(priorityText, date.isValid(), checked);
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
            dueDate.clear();
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
    private void updateScoreCounter(String priorityText, boolean dueDatePassed, boolean ischecked) {
        if (ischecked) {
            if (dueDatePassed) {
                score.addScore(priorityText);
            } else {
                score.subtractScore(priorityText);
            }
        } else {
            score.subtractScore(priorityText);
        }
        Text scoreCount = new Text("Your Score: " + score.getCounter());
        // Update the score on the GUI
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
}
	