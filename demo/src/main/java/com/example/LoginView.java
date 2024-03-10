package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is GUI for Login page
 */

public class LoginView {
    static LoginController loginController;

    public LoginView(LoginController loginController) {
        LoginView.loginController = loginController;
    }

    public void start(Stage stage) {
        stage.setTitle("Login");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(25);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1, 2,1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2, 2, 1);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 4);

        Button regbutton = new Button("Register");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn1.getChildren().add(regbutton);
        grid.add(hbBtn1, 1, 4);

        Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        btn.setOnAction(loginController.onLoginButtonClick(actionTarget, userTextField, pwBox, stage));
        regbutton.setOnAction(loginController.onRegisterButtonClick(stage));
        userTextField.textProperty().addListener(loginController.onUserNameTextChange(actionTarget));
        pwBox.textProperty().addListener(loginController.onPasswordTextChange(actionTarget));
        
        Scene scene = new Scene(grid, 500, 450 );
        stage.setScene(scene);
        stage.show();
    }
}