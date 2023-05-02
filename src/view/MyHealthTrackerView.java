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
        loginScene = createLoginScene();
        primaryStage.setScene(loginScene);
    
        // show the primary stage
        primaryStage.show();
    }
    
    
    public void showHomeScene() {
        // Create UI components for home scene
        Label welcomeLabel = new Label("Welcome, " + currentUser.getFirstName() + "!");
        Button viewProfileButton = new Button("View Profile");
        Button viewRecordsButton = new Button("View Records");
        Button logoutButton = new Button("Logout");
    
        // Set event handlers for UI components
        viewProfileButton.setOnAction(e -> handleViewProfile());
        viewRecordsButton.setOnAction(e -> handleViewRecords());
        logoutButton.setOnAction(e -> handleLogout());
    
        // Create layout for home scene
        VBox homeLayout = new VBox(20);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.getChildren().addAll(welcomeLabel, viewProfileButton, viewRecordsButton, logoutButton);
    
        // Set the home scene as the primary stage scene
        Scene scene = new Scene(homeLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showProfileScene() {
        // Create UI components for profile scene
        Label profileLabel = new Label("Profile");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label ageLabel = new Label("Age:");
        Label genderLabel = new Label("Gender:");
        Label heightLabel = new Label("Height (in cm):");
        Label weightLabel = new Label("Weight (in kg):");
    
        TextField firstNameField = new TextField();
        firstNameField.setEditable(false);
        firstNameField.setText(currentUser.getFirstName());
    
        TextField lastNameField = new TextField();
        lastNameField.setEditable(false);
        lastNameField.setText(currentUser.getLastName());
    
        TextField ageField = new TextField();
        ageField.setEditable(false);
        ageField.setText(Integer.toString(currentUser.getAge()));
    
        TextField genderField = new TextField();
        genderField.setEditable(false);
        genderField.setText(currentUser.getGender().toString());
    
        TextField heightField = new TextField();
        heightField.setEditable(false);
        heightField.setText(Double.toString(currentUser.getHeight()));
    
        TextField weightField = new TextField();
        weightField.setEditable(false);
        weightField.setText(Double.toString(currentUser.getWeight()));
    
        Button backButton = new Button("Back");
    
        // Set event handler for back button
        backButton.setOnAction(e -> showHomeScene());
    
        // Create layout for profile scene
        GridPane profileLayout = new GridPane();
        profileLayout.setAlignment(Pos.CENTER);
        profileLayout.setVgap(20);
        profileLayout.setHgap(20);
        profileLayout.add(profileLabel, 0, 0, 2, 1);
        profileLayout.add(firstNameLabel, 0, 1);
        profileLayout.add(firstNameField, 1, 1);
        profileLayout.add(lastNameLabel, 0, 2);
        profileLayout.add(lastNameField, 1, 2);
        profileLayout.add(ageLabel, 0, 3);
        profileLayout.add(ageField, 1, 3);
        profileLayout.add(genderLabel, 0, 4);
        profileLayout.add(genderField, 1, 4);
        profileLayout.add(heightLabel, 0, 5);
        profileLayout.add(heightField, 1, 5);
        profileLayout.add(weightLabel, 0, 6);
        profileLayout.add(weightField, 1, 6);
        profileLayout.add(backButton, 0, 7, 2, 1);
    
        // Set the profile scene as the primary stage scene
        Scene scene = new Scene(profileLayout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public void showRecordsScene() {
        // Get all health records for the current user
        List<HealthRecord> healthRecords = healthRecordController.getHealthRecordsForUser(currentUser);
    
        // Create UI components for records scene
        Label titleLabel = new Label("Health Records");
        TableView<HealthRecord> recordsTable = new TableView<>();
        TableColumn<HealthRecord, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<HealthRecord, String> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        TableColumn<HealthRecord, String> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        TableColumn<HealthRecord, String> bpColumn = new TableColumn<>("Blood Pressure");
        bpColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressure"));
        recordsTable.getColumns().addAll(dateColumn, weightColumn, heightColumn, bpColumn);
        ObservableList<HealthRecord> data = FXCollections.observableArrayList(healthRecords);
        recordsTable.setItems(data);
    
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showHomeScene());
    
        // Create layout for records scene
        VBox recordsLayout = new VBox(20);
        recordsLayout.setAlignment(Pos.CENTER);
        recordsLayout.getChildren().addAll(titleLabel, recordsTable, backButton);
    
        // Set the records scene as the primary stage scene
        Scene scene = new Scene(recordsLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
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
