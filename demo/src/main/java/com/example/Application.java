package com.example;

import javafx.stage.Stage;
import java.io.IOException;

import com.example.DB.DBConnect;

public class Application extends javafx.application.Application {
   static LoginController loginController = new LoginController();

   @Override
   public void start(Stage stage) throws IOException {
      LoginView login = new LoginView(loginController);
      login.start(stage);
   }

   public static void main(String[] args) {
      DBConnect db = new DBConnect();
      db.init();
      launch();
   }
}