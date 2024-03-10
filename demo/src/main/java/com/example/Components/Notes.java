package com.example.Components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Notes extends Stage{
    public Notes() {

         TextArea noteTextArea = new TextArea();
         Button saveButton = new Button("Save");
         Button closeButton = new Button("Close");
 
         // Handle save button click
         saveButton.setOnAction(event -> {
             String note = noteTextArea.getText(); //save this note to database
             System.out.println("Note saved: " + note);
             close();
         });
 
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
