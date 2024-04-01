package com.example.Components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.Components.User;

import com.example.DB.DB;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Notes is a class representing a stage for taking notes.
 */
public class Notes extends Stage{
    public String id;

    /**
     * Notes is the constructor for creating a Notes object with a specified user ID.
     * 
     * @param id The user ID associated with the notes.
     */
    public Notes(String id) {
        this.id = id;

        // Create UI elements
         TextArea noteTextArea = new TextArea();
         Button saveButton = new Button("Save");
         Button closeButton = new Button("Close");
 
         
         // Handle close button click
         closeButton.setOnAction(event -> close());
 
         // Layout the UI elements
         VBox root = new VBox(noteTextArea, saveButton, closeButton);
         root.setSpacing(10);
 
         // Create scene and set it to the stage
         Scene scene = new Scene(root, 300, 200);
         setScene(scene);

    }
}