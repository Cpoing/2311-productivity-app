package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private ListView<CheckBox> toDoList;
    private TextField newItemField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        toDoList = new ListView<>();

        newItemField = new TextField();
        newItemField.setPromptText("Enter a new item");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNewItem());

        root.setCenter(toDoList);
        root.setBottom(newItemField);
        root.setRight(addButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("To-Do List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNewItem() {
        String newItemText = newItemField.getText().trim();
        if (!newItemText.isEmpty()) {
            CheckBox newItemCheckbox = new CheckBox(newItemText);
            toDoList.getItems().add(newItemCheckbox);
            newItemField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}