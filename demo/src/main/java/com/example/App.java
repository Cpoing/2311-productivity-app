package com.example;

import com.example.Components.DueDate;
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
    private Text newItemErrorText;
    private Text dueDateErrorText;

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

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItem());

        ScoreCounter score = new ScoreCounter();
        Text scoreCount = new Text("Your Score: " + score.getCounter());

        root.setCenter(toDoList);
        root.setRight(addButton);
        root.setLeft(scoreCount);
        
        
        HBox date = new HBox(dueDate, dueDateErrorText);
        HBox item = new HBox(newItemField, newItemErrorText);
        
        VBox inputFields = new VBox(item, date);
        
        inputFields.setSpacing(5);

        root.setBottom(inputFields);
        


        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("To-Do List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNewItem() {
        String newItemText = newItemField.getText().trim();
        DueDate date = new DueDate(dueDate.getText()); 

        newItemErrorText.setText("");
        dueDateErrorText.setText("");

        if (newItemText.isEmpty()) {
            newItemErrorText.setText("Item name cannot be empty");
        }
        
        if (!date.isValid()) {
            dueDateErrorText.setText(date.getErrorMessage());
        }

        if (!newItemText.isEmpty() && date.isValid() == true) {
            String combinedText = newItemText + " - Due: " + dueDate.getText();
            
            CheckBox newItemCheckbox = new CheckBox(combinedText);

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
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}