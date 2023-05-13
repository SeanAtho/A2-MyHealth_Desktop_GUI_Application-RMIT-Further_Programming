package application;

import controller.HealthRecordController;
import controller.UserController;
import database.Database;
import view.MyHealthTrackerView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represents the main application for the MyHealthTracker.
 * It initializes and sets up the necessary controllers, database and view for the application.
 */
public class MyHealthTracker extends Application {
    
    // The controller that handles user-related tasks and interactions
    private UserController userController; 
    
    // The controller that handles health record-related tasks and interactions
    private HealthRecordController healthRecordController;

    // The database object that handles data persistence for the application
    private Database database;                          
    
    /**
     * This method is the entry point of the JavaFX application.
     * It sets up the necessary controllers, database, and view, and starts the application GUI.
     *
     * @param primaryStage the main window for the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize the database object
        database = new Database();

        // Initialize the health record controller, providing it with the database object for data persistence
        healthRecordController = new HealthRecordController(database);

        // Initialize the user controller, providing it with the database object and the health record controller
        userController = new UserController(database, healthRecordController);

        // Initialize the view for the application, providing it with the primary stage and controllers
        MyHealthTrackerView view = new MyHealthTrackerView(primaryStage, userController, healthRecordController);

        // Display the login scene as the first scene
        view.showLoginScene();
    }
    
    /**
     * The main method for the application, which launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Start the JavaFX application
        launch(args);
    }
}
