package application;

import controller.HealthRecordController;
import controller.UserController;
import controller.Database;
import view.MyHealthTrackerView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the MyHealthTracker application.
 */
public class MyHealthTracker extends Application {
    
    // Attributes
    private UserController userController;              // Controller for user-related actions
    private HealthRecordController healthRecordController;  // Controller for health record-related actions
    private Database database;                          // Database for storing user and health record information
    
    /**
     * The entry point for the application. Starts the JavaFX GUI.
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize the controllers, the database, and the view
        database = new Database();
        userController = new UserController(database);
        healthRecordController = new HealthRecordController(database);
        MyHealthTrackerView view = new MyHealthTrackerView(primaryStage, userController, healthRecordController);

        // Show the login scene
        view.showLoginScene();
    }
    
    /**
     * The main method for the application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
