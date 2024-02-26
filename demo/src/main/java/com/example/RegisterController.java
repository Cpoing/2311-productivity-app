package com.example;

import com.example.Components.User;
import com.example.Components.login;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This class allows user to create their username and the password
 */
public class RegisterController {

    static LoginController loginController = new LoginController();

    protected EventHandler<ActionEvent> onRegisterButtonClick(Text actionTarget, TextField userTextField, TextField pwBox, Stage stage){
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                login login = new login();

                try{
                    User user = new User(username,password);
                    login.register(user);
                    actionTarget.setFill(javafx.scene.paint.Color.GREEN);
                    actionTarget.setText("Register successful");

                    LoginView loginpage = new LoginView(loginController);
                    loginpage.start(stage);
                    
                } catch (IllegalArgumentException e){
                    actionTarget.setFill(javafx.scene.paint.Color.RED);
                    actionTarget.setText("Register unsuccessful");
                }
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