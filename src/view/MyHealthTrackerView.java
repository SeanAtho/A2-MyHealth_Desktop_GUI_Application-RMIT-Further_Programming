package view;

import controller.HealthRecordController;
import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

public class MyHealthTrackerView {
    
    // attributes
    private Stage primaryStage;
    private UserController userController;
    private HealthRecordController healthRecordController;
    private User currentUser;
    private Scene loginScene;
    private Scene homeScene;
    private Scene profileScene;
    private Scene recordsScene;
    private Scene recordDetailsScene;
    private Alert errorAlert;
    
    // constructor
    public MyHealthTrackerView(Stage primaryStage, UserController userController,
        HealthRecordController healthRecordController) {
        this.primaryStage = primaryStage;
        this.userController = userController;
        this.healthRecordController = healthRecordController;
        // initialize scenes and alert
    }
    
    // methods
    public void start() {
        // initialize login scene and set it as the primary stage scene
        // show the primary stage
    }
    
    public void showHomeScene() {
        // switch to home scene
    }
    
    public void showProfileScene() {
        // switch to profile scene
    }
    
    public void showRecordsScene() {
        // switch to records scene
    }
    
    public void showRecordDetailsScene() {
        // switch to record details scene
    }
    
    public void showErrorAlert(String message) {
        // show an error alert with the given message
    }
    
    public void clearErrorAlert() {
        // clear the error alert
    }
    
    public void handleLogin(String username, String password) {
        // handle the login action with the given username and password
    }
    
    public void handleLogout() {
        // handle the logout action
    }
    
    public void handleCreateProfile() {
        // handle the create profile action
    }
    
    public void handleEditProfile() {
        // handle the edit profile action
    }
    
    public void handleDeleteProfile() {
        // handle the delete profile action
    }
    
    public void handleAddRecord() {
        // handle the add record action
    }
    
    public void handleEditRecord() {
        // handle the edit record action
    }
    
    public void handleDeleteRecord() {
        // handle the delete record action
    }
    
    public void handleViewRecordDetails() {
        // handle the view record details action
    }
    
    public void updateRecordTable() {
        // update the record table in the records scene
    }
    
    public void updateProfileFields() {
        // update the profile fields in the profile scene
    }
    
}
