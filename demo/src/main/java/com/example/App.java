package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.Components.*;
import com.example.DB.DB;

import java.awt.Toolkit;
import javafx.scene.layout.VBox;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class App {

    /**
     * toDoList is a Checkbox list of tasks needed to be completed.
     * newItemField is a text field for the description of a new task.
     * dueDate is the text field for the due date of a new task.
     * itemPrio is the tasks item priority.
     * newItemErrortext, dueDateErrorText & itemPrioError are the text dialogues for
     * errors.
     * score is the users' score from the completion of tasks.
     * disp is a TimerDisplay object which contains the timer hbox and text
     * attributes.
     */
    private ListView<CheckBox> toDoList;
    // private ArrayList<ChecklistItem> checklistItems = new ArrayList<>();
    private TextField newItemField;
    private DueDate dueDateComponent;
    private TextField itemPrio;
    private DB db;
    private Priority priority;
    private Text newItemErrorText, dueDateErrorText, prioErrorText;
    private ScoreCounter score;
    private TimerDisplay disp;
    private String username;
    private Timeline timeline;
    private BorderPane root;
    private BorderPane bottom;
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    public App(Stage stage, String username) {
        // root is the Root Border Pane
        this.root = new BorderPane();
        // bottom is border pane for the bottom portion so that elements can be aligned
        // at top-right, top-left, bottom-left, bottom-right
        this.bottom = new BorderPane();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateScoresAndColors(checklistItems)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Start the timeline
        this.username = username;

        toDoList = new ListView<>();
        db = new DB();

        checklistItems = db.retrieveTasks(username);
        populateChecklist(checklistItems);
        // updateScoresAndColors(tasks);

        newItemField = new TextField();
        newItemField.setPromptText("Enter a new item");

        newItemErrorText = new Text();

        dueDateComponent = new DueDate();
        dueDateErrorText = new Text();

        this.priority = new Priority(null);
        prioErrorText = new Text();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItem());

        Button timerButton = new Button("Pomodoro Timer");
        timerButton.setOnAction(e -> updateTime());
        this.bottom.setRight(timerButton);

        Button noteButton = new Button("Notes");
        noteButton.setOnAction(e -> openNoteWindow());
        this.bottom.setTop(noteButton);

        this.score = new ScoreCounter(this.username);
        Text leftText = new Text(
                this.username + "\nYour Score: " + this.score.getCounter() + "\nRank: " + this.score.rankScore());
        if (score.rankScore() == "Rookie") {
            this.root.setStyle("-fx-background-color: #51f5ae;");
            this.bottom.setStyle("-fx-background-color: #51f5ae;");
        } else if (score.rankScore() == "Intermediate") {
            this.root.setStyle("-fx-background-color: #5edaff;");
            this.bottom.setStyle("-fx-background-color: #5edaff;");
        } else if (score.rankScore() == "Master") {
            this.root.setStyle("-fx-background-color: #ff5e84;");
            this.bottom.setStyle("-fx-background-color: #ff5e84;");
        } else if (score.rankScore() == "Grand Master") {
            this.root.setStyle("-fx-background-color: #f5ee6c;");
            this.bottom.setStyle("-fx-background-color: #f5ee6c;");
        } else if (score.rankScore() == "Legendary") {
            this.root.setStyle("-fx-background-color: #bc6cf5;");
            this.bottom.setStyle("-fx-background-color: #bc6cf5;");
        }

        this.root.setCenter(toDoList);
        this.root.setRight(addButton);
        this.root.setLeft(leftText);
        this.root.setBottom(this.bottom);

        HBox date = new HBox(dueDateComponent, dueDateErrorText);
        HBox item = new HBox(newItemField, newItemErrorText);
        HBox prioButtons = priority.createPriorityButtons();

        VBox inputFields = new VBox(item, date, prioButtons);

        inputFields.setSpacing(5);

        this.bottom.setLeft(inputFields);

        Scene scene = new Scene(this.root, 600, 400);
        stage.setTitle("To-Do List App");
        stage.setScene(scene);
        stage.show();

        ScoreChartWindow scoreChartWindow = new ScoreChartWindow(score, this.username);

        Button openChartButton = new Button("Open Score Chart");
        openChartButton.setOnAction(event -> {
            scoreChartWindow.show();
        });

        Button musicP = new Button("Open Music Player");
        musicP.setOnAction(event -> openMP());
        // created a VBox to resolve issue of button overlapping
        VBox buttonbox = new VBox(timerButton, noteButton, openChartButton,musicP);
        this.bottom.setRight(buttonbox);

    }

    /*
     * private void openPomodoroTimer(Stage timerStage) {
     * BorderPane winTop = new BorderPane();
     * BorderPane winBott = new BorderPane();
     * Scene pomdoroScene = new Scene(winTop, 600, 400);
     * 
     * //Button shortBreak = new Button("Short Break"); // 5 Minutes
     * //Button longBreak = new Button("Long Break"); // 10 Minutes
     * 
     * final int normalSeconds = 1500; // 25 minutes * 60 seconds = 1500 seconds
     * //final int shortBreakSeconds = 300; // 5 minutes * 60 seconds = 300 seconds
     * //final int longBreakSeconds = 600; // 10 minutes * 60 seconds = 600 seconds
     * 
     * Timer myTimer = new Timer();
     * myTimer.scheduleAtFixedRate(myTimerTask, 0, 1000);
     * 
     * timerStage.setTitle("Pomodoro Timer");
     * timerStage.setScene(pomdoroScene);
     * timerStage.show();
     * }
     */

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
        LocalTime selectedTime = dueDateComponent.getTime();

        String prio = priority.getPriority();

        // Instantiate Variables
        newItemErrorText.setText("");
        dueDateErrorText.setText("");

        dueDateComponent.setDate();
        if (!dueDateComponent.isValid()) {
            dueDateErrorText.setText(dueDateComponent.getErrorMessage());
        }

        if (newItemText.isEmpty()) {
            newItemErrorText.setText("Item name cannot be empty");
        }

        if (prio == null) {
            priority.setError("Please select a priority");
            return;
        }

        if (!newItemText.isEmpty() && dueDateComponent.isValid()) {
            String formattedDate = dueDateComponent.getFormattedDate();
            String combinedText = newItemText + " - Due: " + formattedDate + " " + selectedTime + " - Priority: "
                    + prio;
            ChecklistItem newItem = new ChecklistItem(newItemText, selectedDate, selectedTime, prio, false, true);
            db.insertTask(username, newItemText, selectedDate, selectedTime, prio, false, true);
            checklistItems.add(newItem);

            /*
             * CheckBox newItemCheckbox = new CheckBox(combinedText);
             * newItemCheckbox.selectedProperty().addListener((observable, oldValue,
             * checked) -> {
             * updateScoreCounter(priorityText, selectedDate.isBefore(LocalDate.now()),
             * checked);
             * });
             */

            CheckBox newItemCheckbox = new CheckBox(combinedText);
            newItemCheckbox.selectedProperty().addListener((observable, oldValue, isChecked) -> {
                LocalDateTime itemDueDateTime = newItem.getDueDate().atTime(newItem.getDueTime());
                if (isChecked) {
                    updateScoreCounter(prio, itemDueDateTime.isBefore(LocalDateTime.now()), true);
                    db.updateTask(username, newItemText, selectedDate, selectedTime, true, true);
                    newItem.setChecked(true);
                    newItemCheckbox.setDisable(true); // Disable checkbox once checked

                } else {
                    updateScoreCounter(prio, itemDueDateTime.isBefore(LocalDateTime.now()), false);
                    newItem.setChecked(false);
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
            priority.setPriority(null);

        }
    }

    private void updateScoresAndColors(List<ChecklistItem> checklistItems) {
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
                        String checkboxText = checkbox.getText();
                        if (checkboxText.startsWith(item.getDescription())
                                && checkboxText.contains(item.getFormattedDateandTime()) && !checkbox.isSelected()) {
                            checkbox.setStyle("-fx-text-fill: red;"); // Turn the text color of overdue items to red
                            checkbox.setSelected(true);
                            checkbox.setDisable(true);
                            item.setChecked(true);
                            item.setOnTime(false);
                            db.updateTask(username, item.getDescription(), dueDate, dueTime, true, false);
                        }
                    }
                }
            }

        }
    }

    /**
     * updateScoreCounter is the method used to update the users' score
     * 
     * @param priorityText  is the String with the corresponding priority level of
     *                      the task (Low, Medium or High).
     * @param dueDatePassed is the boolean representing whether the due date has
     *                      passed or not.
     * @param ischecked     is the boolean for whether the task is already completed
     *                      or not to avoid duplicate additions to the score.
     */
    private void updateScoreCounter(String priorityText, boolean dueDatePassed, boolean ischecked) {
        if (dueDatePassed) {
            this.score.subtractScore(priorityText);
        } else {
            if (ischecked) {
                this.score.addScore(priorityText);
            } else {
                this.score.subtractScore(priorityText);
            }
        }

        Text scoreCount = new Text(
                this.username + "\nYour Score: " + this.score.getCounter() + "\nRank: " + this.score.rankScore());
        // Update the score on the GUI
        ((BorderPane) newItemField.getScene().getRoot()).setLeft(scoreCount);
        if (score.rankScore() == "Rookie") {
            this.root.setStyle("-fx-background-color: #51f5ae;");
            this.bottom.setStyle("-fx-background-color: #51f5ae;");
        } else if (score.rankScore() == "Intermediate") {
            this.root.setStyle("-fx-background-color: #5edaff;");
            this.bottom.setStyle("-fx-background-color: #5edaff;");
        } else if (score.rankScore() == "Master") {
            this.root.setStyle("-fx-background-color: #ff5e84;");
            this.bottom.setStyle("-fx-background-color: #ff5e84;");
        } else if (score.rankScore() == "Grand Master") {
            this.root.setStyle("-fx-background-color: #f5ee6c;");
            this.bottom.setStyle("-fx-background-color: #f5ee6c;");
        } else if (score.rankScore() == "Legendary") {
            this.root.setStyle("-fx-background-color: #bc6cf5;");
            this.bottom.setStyle("-fx-background-color: #bc6cf5;");
        }

    }

    /**
     * updateTime is the method used to update the timer.
     * 
     * Task is used to update the timer in a new thread (opting not to use the
     * system thread since it is already being used).
     */
    private void updateTime() {
        // GUI For Timer
        disp = new TimerDisplay();
        Stage pomodoroStage = new Stage();
        Scene scene = new Scene(disp, 600, 400);
        disp.setStyle("-fx-background-color: #3A3B3C;");
        // Update Timer Count using Task Class
        Task<Object> task = new Task<>() {
            @Override
            protected Object call() {
                for (int i = 1500; i >= 0; i--) {
                    // If the timer has run out then play the default OS beep.
                    if (i == 0) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    try {
                        // String format for time (2 digits per number seperated by :), i/60 is the
                        // minutes, i%60 is the seconds.
                        updateMessage(String.format("%02d:%02d", i / 60, i % 60));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
                return null;
            }
        };

        new Thread(task).start(); // Must use new thread, using System thread causes JavaFX to delay since it is
                                  // already using Sys thread.

        disp.getText().textProperty().bind(task.messageProperty()); // Get the text from task and bind it to the time
                                                                    // variable in class TimerDisplay

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
    public class TimerDisplay extends HBox {
        private Text time;

        public TimerDisplay() {
            time = new Text("");
            time.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 40));
            time.setFill(Color.WHITE);
            getChildren().add(time);
            setAlignment(Pos.CENTER);
        }

        public Text getText() {
            return time;
        }
    }

    private void openMP(){
        Stage musicPlayerStage = new Stage();
        MusicPlayer musicPlayerComponent = new MusicPlayer();
        Scene musicPlayerScene = new Scene(musicPlayerComponent);

        musicPlayerStage.setScene(musicPlayerScene);
        musicPlayerStage.setTitle("Music Player");
        musicPlayerStage.show();

        musicPlayerStage.setOnCloseRequest(event -> {
            musicPlayerComponent.onClose();

        });
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
        openNotesFromDB(noteTextArea);

        Button saveButton = new Button("Save");
        Button closeButton = new Button("Close");

        HBox buttonBox = new HBox(saveButton, closeButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        noteLayout.setBottom(buttonBox);

        saveButton.setOnAction(e -> {

            String noteContent = noteTextArea.getText();
            saveNoteToDB(noteContent);

            System.out.println("Note saved: " + noteContent);

        });

        closeButton.setOnAction(e -> noteStage.close());

        Scene noteScene = new Scene(noteLayout, 400, 300);
        noteStage.setScene(noteScene);
        noteStage.setTitle("Notes");
        noteStage.show();
    }

    private void saveNoteToDB(String note) {
        DB db = new DB();
        db.init();
        db.insertNote(this.username, note);
    }

    private void openNotesFromDB(TextArea noteTextArea) {
        DB db = new DB();
        db.init();

        String notes = db.getNotes(this.username);
        noteTextArea.setText(notes);
    }

    private void populateChecklist(List<ChecklistItem> tasks) {
        // Populate the checklist with tasks
        for (ChecklistItem item : tasks) {
            String description = item.getDescription();
            String priority = item.getPriority();
            boolean isChecked = item.isChecked();

            // Create formatted date and time strings
            String formattedDateandTime = item.getFormattedDateandTime();

            // Create combined text for the checklist item
            String combinedText = description + " - Due: " + formattedDateandTime + " - Priority: " + priority;

            // Create a new CheckBox with combinedText
            CheckBox newItemCheckBox = new CheckBox(combinedText);
            newItemCheckBox.setSelected(isChecked); // Set its checked state
            newItemCheckBox.setDisable(isChecked);
            if (!item.getOnTime()) {
                newItemCheckBox.setStyle("-fx-text-fill: red;");
            }
            // Add the new CheckBox to the checklist
            toDoList.getItems().add(newItemCheckBox);

            // Add listener to handle score addition when item is checked
            newItemCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                LocalDateTime itemDueDateTime = item.getDueDate().atTime(item.getDueTime());
                if (newValue) {
                    // Update the score counter and database when the item is checked
                    updateScoreCounter(item.getPriority(), itemDueDateTime.isBefore(LocalDateTime.now()), true);
                    item.setChecked(true);
                    item.setOnTime(false);
                    db.updateTask(username, item.getDescription(), item.getDueDate(), item.getDueTime(), true, false);
                    newItemCheckBox.setDisable(true);
                }
            });
        }
    }
}
