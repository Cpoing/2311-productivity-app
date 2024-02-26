package com.example;

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
    private TextField dueDate;
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

        dueDate = new TextField();
        dueDate.setPromptText("YYYY/MM/DD");
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

        HBox date = new HBox(dueDate, dueDateErrorText);
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
        DueDate date = new DueDate(dueDate.getText().trim()); 
        String priorityText = itemPrio.getText().trim();

        Priority prio = new Priority(priorityText);

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

            toDoList.getItems().add(firstCheckedIndex, newItemCheckbox);
            newItemField.clear();
            dueDate.clear();
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