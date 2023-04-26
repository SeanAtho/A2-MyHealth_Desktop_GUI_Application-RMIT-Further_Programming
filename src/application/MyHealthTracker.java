package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyHealthTracker extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Health Tracker");
        primaryStage.show();
    }
}