package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.Components.DueDate;
import com.example.Components.Priority;
import com.example.Components.ScoreCounter;

import javafx.application.Application;
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

public class App extends Application {

    private ListView<CheckBox> toDoList;
    private TextField newItemField;
    private DueDate dueDateComponent;
    private TextField itemPrio;
    private Text newItemErrorText, dueDateErrorText, itemPrioError;
    private ScoreCounter score;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

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

        score = new ScoreCounter();
        Text scoreCount = new Text("Your Score: " + score.getCounter());

        root.setCenter(toDoList);
        root.setRight(addButton);
        root.setLeft(scoreCount);

        HBox date = new HBox(dueDateComponent, dueDateErrorText);
        HBox item = new HBox(newItemField, newItemErrorText);
        HBox prio = new HBox(itemPrio, itemPrioError);

        VBox inputFields = new VBox(item, date, prio);

        inputFields.setSpacing(5);

        root.setBottom(inputFields);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("To-Do List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNewItem() {
        String newItemText = newItemField.getText().trim();
        LocalDate selectedDate = dueDateComponent.getDatePicker().getValue(); // Retrieve selected date
        String priorityText = itemPrio.getText().trim();

        Priority prio = new Priority(priorityText);

        newItemErrorText.setText("");
        dueDateErrorText.setText("");
        itemPrioError.setText("");

        try {
            dueDateComponent.setDate();
            if (!dueDateComponent.isValid()) {
                dueDateErrorText.setText(dueDateComponent.getErrorMessage());
            }
        } catch (Exception e) {
            dueDateErrorText.setText("Invalid date or format, please use YYYY/MM/DD");
        }

        if (newItemText.isEmpty()) {
            newItemErrorText.setText("Item name cannot be empty");
        }        

        if (!prio.isValidPriority(priorityText)) {
            itemPrioError.setText("Priority must be 'Low', 'Medium', or 'High'");
        }

        if (!newItemText.isEmpty() && dueDateComponent.isValid() && prio.isValidPriority(priorityText)) {
            String formattedDate = dueDateComponent.getFormattedDate();
            String combinedText = newItemText + " - Due: " + formattedDate + " - Priority: " + priorityText;

            CheckBox newItemCheckbox = new CheckBox(combinedText);
            newItemCheckbox.selectedProperty().addListener((observable, oldValue, checked) -> {
                updateScoreCounter(priorityText, selectedDate.isBefore(LocalDate.now()), checked);
            });

            int firstCheckedIndex = 0;
            for (CheckBox checkbox : toDoList.getItems()) {
                if (checkbox.isSelected()) {
                    break;
                } else
                    firstCheckedIndex++;
            }

            toDoList.getItems().add(firstCheckedIndex, newItemCheckbox);
            newItemField.clear();
            dueDateComponent.getDatePicker().setValue(null); // Clear selected date
            itemPrio.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

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
        ((BorderPane) newItemField.getScene().getRoot()).setLeft(scoreCount);

    }
}