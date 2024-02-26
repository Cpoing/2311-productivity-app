package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is GUI for user register page
 */

public class RegisterView {
    
    static RegisterController registerController = new RegisterController();

    public RegisterView(Stage stage){
        stage.setTitle("Register");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(25);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Create New Account");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1, 2,1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        TextField pwBox = new TextField();
        grid.add(pwBox, 1, 2, 2, 1);

        Button regbutton = new Button("Register");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(regbutton);
        grid.add(hbBtn1, 2, 4);

        Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        regbutton.setOnAction(registerController.onRegisterButtonClick(actionTarget,userTextField,pwBox,stage));
        
        Scene scene = new Scene(grid, 500, 450 );
        stage.setScene(scene);
        stage.show();
    }
}