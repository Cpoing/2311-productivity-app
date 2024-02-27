package com.example;

import com.example.Components.login;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class checkes if the user id and the password is matching. If matching then user can access to the app
 */

public class LoginController {

    RegisterController regcon = new RegisterController(new login());

    protected EventHandler<ActionEvent> onLoginButtonClick(Text actionTarget, TextField userTextField, PasswordField pwBox, Stage stage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = userTextField.getText();
                String password = pwBox.getText();

                //check if the login information and the register information is matching.
                try{
                    if(regcon.getLogin().loginTo(username, password)){
                        actionTarget.setFill(javafx.scene.paint.Color.GREEN);
                            actionTarget.setText("Login successful");
    
                            // change scene
                            App todolist = new App(stage);
                    } else {
                        actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                        actionTarget.setText("Login failed");
                    }
                } catch (IllegalArgumentException e){
                    actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                    actionTarget.setText("Login failed");
                }
            }
        };
    }

    protected EventHandler<ActionEvent> onRegisterButtonClick(Stage stage){
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RegisterView reg = new RegisterView(stage);
            }
        };
    }

    protected ChangeListener<? super String> onUserNameTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }

    protected ChangeListener<? super String> onPasswordTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }
}