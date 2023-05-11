package view;

import controller.UserController;
import controller.HealthRecordController;
import model.User;
import model.HealthRecord;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class is the main view for the My Health Tracker application.
 */
public class MyHealthTrackerView {

    private Stage primaryStage;
    private UserController userController;
    private HealthRecordController healthRecordController;
    private User currentUser;
    private Scene loginScene;
    private Scene homeScene;

    // Additional scenes and UI components
    private Scene profileScene;
    private Scene recordsScene;
    private Scene createRecordScene;
    private Scene editRecordScene;
    private TextField weightField;
    private TextField temperatureField;
    private TextField bloodPressureField;
    private TextArea noteField;
    private TableView<HealthRecord> recordsTable;
    private Label fullNameLabel;
    private TextField firstNameField;
    private TextField lastNameField;
    private Scene registerScene;


    public MyHealthTrackerView(Stage primaryStage, UserController userController, HealthRecordController healthRecordController) {
        this.primaryStage = primaryStage;
        this.userController = userController;
        this.healthRecordController = healthRecordController;
        
        // Set the title for the primary stage
        this.primaryStage.setTitle("My Health Tracker");
    
        // Initialize the scenes
        initLoginScene();
        initRegisterScene();
        initHomeScene();
        initProfileScene();
        initRecordsScene();
        initCreateRecordScene();
        initEditRecordScene();
    }


    // Initialization methods for loginScene, homeScene, and other scenes remain unchanged

        /**
     * Initializes the login scene with UI components and event handlers.
     */
    private void initLoginScene() {
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button goToRegisterButton = new Button("Register");

        // Set event handlers for the buttons
        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));
        goToRegisterButton.setOnAction(e -> primaryStage.setScene(registerScene));

        // Create and configure the GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 0, 2);
        grid.add(goToRegisterButton, 1, 2);

        // Set the GridPane as the root of the loginScene
        loginScene = new Scene(grid, 300, 175); // Adjusted the height for fewer fields
    }

    /**
     * Initializes the register scene.
     */
    private void initRegisterScene() {
        // Create the UI elements for the register scene
        VBox registerForm = new VBox(10);
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back to Login");

        // Add the UI elements to the VBox
        registerForm.getChildren().addAll(
            new Label("Username:"), usernameField,
            new Label("Password:"), passwordField,
            new Label("First Name:"), firstNameField,
            new Label("Last Name:"), lastNameField,
            registerButton, backButton
        );

        // Set the event handlers for the buttons
        registerButton.setOnAction(e -> handleRegister(
            usernameField.getText(),
            passwordField.getText(),
            firstNameField.getText(),
            lastNameField.getText()
        ));
        backButton.setOnAction(e -> primaryStage.setScene(loginScene));

        // Create the scene and add it to the primary stage
        registerScene = new Scene(registerForm, 300, 275);
    }



    /**
    * Initializes the home scene with UI components and event handlers.
    */
    private void initHomeScene() {
        fullNameLabel = new Label("");
        Button profileButton = new Button("Edit Profile");
        Button recordsButton = new Button("View Records");
        Button exportButton = new Button("Export Records");
        Button logoutButton = new Button("Logout");

        // Set event handlers for the buttons
        profileButton.setOnAction(e -> showProfileScene());
        recordsButton.setOnAction(e -> showRecordsScene());
        exportButton.setOnAction(e -> handleExportRecords());
        logoutButton.setOnAction(e -> showLoginScene());

        // Create and configure the VBox layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(fullNameLabel, profileButton, recordsButton, exportButton, logoutButton);

        // Set the VBox as the root of the homeScene
        homeScene = new Scene(vbox, 300, 200);
    }

    /**
    * Initializes the profile scene with UI components and event handlers.
    */
    private void initProfileScene() {
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        // Set event handlers for the buttons
        saveButton.setOnAction(e -> handleEditProfile());
        backButton.setOnAction(e -> showHomeScene());

        // Create and configure the GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(saveButton, 0, 2);
        grid.add(backButton, 1, 2);

        // Set the GridPane as the root of the profileScene
        profileScene = new Scene(grid, 300, 200);
    }

    /**
    * Initializes the records scene with UI components and event handlers.
    */
    private void initRecordsScene() {
        recordsTable = new TableView<>();
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        // Set event handlers for the buttons
        addButton.setOnAction(e -> handleAddRecord());
        editButton.setOnAction(e -> handleEditRecord());
        deleteButton.setOnAction(e -> handleDeleteRecord());
        backButton.setOnAction(e -> showHomeScene());

        // Create and configure the VBox layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(recordsTable, addButton, editButton, deleteButton, backButton);

        // Set the VBox as the root of the recordsScene
        recordsScene = new Scene(vbox, 400, 300);
    }


    /**
     * Initializes the create record scene with UI components and event handlers.
     */
    private void initCreateRecordScene() {
        weightField = new TextField();
        temperatureField = new TextField();
        bloodPressureField = new TextField();
        noteField = new TextArea();
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        saveButton.setOnAction(e -> handleAddRecord());
        backButton.setOnAction(e -> showRecordsScene());

        GridPane grid = createRecordGridPane();
        grid.add(saveButton, 0, 4);
        grid.add(backButton, 1, 4);

        createRecordScene = new Scene(grid, 400, 300);
    }

    /**
     * Initializes the edit record scene with UI components and event handlers.
     */
    private void initEditRecordScene() {
        weightField = new TextField();
        temperatureField = new TextField();
        bloodPressureField = new TextField();
        noteField = new TextArea();
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        saveButton.setOnAction(e -> handleEditRecord());
        backButton.setOnAction(e -> showRecordsScene());

        GridPane grid = createRecordGridPane();
        grid.add(saveButton, 0, 4);
        grid.add(backButton, 1, 4);

        editRecordScene = new Scene(grid, 400, 300);
    }

    /** * Creates a GridPane with UI components for adding/editing a health record.
    *
    * @return a GridPane with labels and input fields for health record data
    */
    private GridPane createRecordGridPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Weight:"), 0, 0);
        grid.add(weightField, 1, 0);
        grid.add(new Label("Temperature:"), 0, 1);
        grid.add(temperatureField, 1, 1);
        grid.add(new Label("Blood Pressure:"), 0, 2);
        grid.add(bloodPressureField, 1, 2);
        grid.add(new Label("Note:"), 0, 3);
        grid.add(noteField, 1, 3);

        return grid;
    }

    /**
    * Sets the current scene to the login scene.
    */
    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

        /**
     * Sets the current scene to the home scene.
     */
    private void showHomeScene() {
        primaryStage.setScene(homeScene);
        updateHomeScene();
    }

    /**
     * Updates the home scene with the current user's information.
     */
    private void updateHomeScene() {
        // Assuming you have a Label named fullNameLabel as an instance variable
        fullNameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
    }


    /**
     * Sets the current scene to the profile scene.
     */
    private void showProfileScene() {
        primaryStage.setScene(profileScene);
        updateProfileFields();
    }

    /**
     * Updates the profile text fields with the current user's information.
     */
    private void updateProfileFields() {
        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
    }

    /**
     * Sets the current scene to the records scene.
     */
    private void showRecordsScene() {
        primaryStage.setScene(recordsScene);
        updateRecordTable();
    }


    /**
    * Displays the create record scene on the primary stage.
    */
    private void showCreateRecordScene() {
        weightField.clear();
        temperatureField.clear();
        bloodPressureField.clear();
        noteField.clear();
        primaryStage.setScene(createRecordScene);
    }

    /**
     * Displays the edit record scene on the primary stage with the given record's data.
     *
     * @param record the health record to edit
     */
    private void showEditRecordScene(HealthRecord record) {
        weightField.setText(String.valueOf(record.getWeight()));
        temperatureField.setText(String.valueOf(record.getTemperature()));
        bloodPressureField.setText(String.valueOf(record.getBloodPressure()));
        noteField.setText(record.getNote());
        primaryStage.setScene(editRecordScene);
    }

    /**
     * Displays an error alert with the specified message.
     *
     * @param message The error message to display.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Updates the records table with the current user's health records.
     */
    private void updateRecordTable() {
        if (recordsTable == null) {
            System.out.println("recordsTable is null");
            return;
        }
        if (healthRecordController == null) {
            System.out.println("healthRecordController is null");
            return;
        }
        if (currentUser == null) {
            System.out.println("currentUser is null");
            return;
        }
        List<HealthRecord> healthRecords = healthRecordController.getHealthRecordsForUser(currentUser);
        if (healthRecords == null) {
            System.out.println("getHealthRecordsForUser returned null");
            return;
        }
        recordsTable.setItems(FXCollections.observableArrayList(healthRecords));
    }
    


    /**
     * Handles user login with the provided username and password.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    private void handleLogin(String username, String password) {
        boolean loginSuccessful = userController.login(username, password);
        if (loginSuccessful) {
            currentUser = userController.getUserByUsername(username);
            showHomeScene();
        } else {
            showErrorAlert("Invalid username or password.");
        }
    }
    

    /**
     * Handles user registration with the provided information.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @param firstName The first name entered by the user.
     * @param lastName The last name entered by the user.
     */
    private void handleRegister(String username, String password, String firstName, String lastName) {
        User user = userController.register(username, password, firstName, lastName);
        if (user != null) {
            currentUser = user;
            showHomeScene();
        } else {
            showErrorAlert("Registration failed. Please try again.");
        }
    }




    /**
     * Handles the edit profile action by updating the user's first and last name and saving the changes.
     */
    private void handleEditProfile() {
        String newFirstName = firstNameField.getText();
        String newLastName = lastNameField.getText();

        if (newFirstName.isEmpty() || newLastName.isEmpty()) {
            showErrorAlert("First name and last name fields cannot be empty.");
        } else {
            currentUser.setFirstName(newFirstName);
            currentUser.setLastName(newLastName);
            userController.updateUser(currentUser);
            showHomeScene();
        }
    }

    /**
     * Handles the Add Record button click event, showing the Create Record scene.
     */
    private void handleAddRecord() {
        showCreateRecordScene();
    }

    
    /**
     * Handles the Edit Record button click event, showing the Edit Record scene.
     */
    private void handleEditRecord() {
        HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
        if (selectedRecord != null) {
            showEditRecordScene(selectedRecord);
        } else {
            showErrorAlert("Please select a record to edit.");
        }
    }

    /**
     * Handles the Delete Record button click event, deleting the selected record.
     */
    private void handleDeleteRecord() {
        HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
        if (selectedRecord != null) {
            healthRecordController.deleteHealthRecord(selectedRecord);
            updateRecordTable();
        } else {
            showErrorAlert("Please select a record to delete.");
        }
    }


    /**
     * Handles exporting the health records of the current user to a text file.
     */
    private void handleExportRecords() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Records");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                for (HealthRecord record : healthRecordController.getHealthRecords()) {
                    writer.write(record.toString() + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                showErrorAlert("Failed to export records: " + e.getMessage());
            }
        }
    }


}