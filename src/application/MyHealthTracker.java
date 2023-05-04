package application;

import controller.HealthRecordController;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

/**
 * The main class for the MyHealthTracker application.
 */
public class MyHealthTracker extends Application {
    
    // Attributes
    private UserController userController;              // Controller for user-related actions
    private HealthRecordController healthRecordController;  // Controller for health record-related actions
    private Database database;                          // Database for storing user and health record information
    private User currentUser;                           // The currently logged in user
    
    /**
     * The entry point for the application. Starts the JavaFX GUI.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO: implement the GUI and connect it to the controllers and database
    }
    
    /**
     * The main method for the application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
