package view;

import controller.UserController;
import database.Database;
import controller.HealthRecordController;
import model.User;
import model.HealthRecord;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents the main view of the My Health Tracker application. It contains methods for 
 * initializing various scenes like the login scene, register scene, and home scene, among others. 
 * It also handles user interactions with these scenes.
 */
public class MyHealthTrackerView {

    // Class member variables
    private Stage primaryStage;
    private UserController userController;
    private HealthRecordController healthRecordController;
    private User currentUser;
    private Scene loginScene;
    private Scene homeScene;
    private Database database;
   
    // Additional scenes and UI components for profile, records, and more
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


    /**
     * Constructs a MyHealthTrackerView with the given parameters.
     *
     * @param primaryStage the primary stage for this view
     * @param userController the user controller for handling user-related actions
     * @param healthRecordController the health record controller for handling health record-related actions
     */
    public MyHealthTrackerView(Stage primaryStage, UserController userController, HealthRecordController healthRecordController) {
        this.primaryStage = primaryStage;
        this.userController = userController;
        this.healthRecordController = healthRecordController;
        this.database = new Database();

        // Initialize input fields
        weightField = new TextField();
        temperatureField = new TextField();
        bloodPressureField = new TextField();
        noteField = new TextArea();
        
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
     * This scene allows new users to create an account by entering their username, password, and full name.
     */
    private void initRegisterScene() {
        // Create the UI elements for the register scene
        VBox registerForm = new VBox(10); // Vertical box with 10 pixels spacing between nodes
        TextField usernameField = new TextField(); // Text field for entering username
        PasswordField passwordField = new PasswordField(); // Password field for entering password
        TextField firstNameField = new TextField(); // Text field for entering first name
        TextField lastNameField = new TextField(); // Text field for entering last name
        Button registerButton = new Button("Register"); // Button to submit the registration form
        Button backButton = new Button("Back to Login"); // Button to go back to the login scene

        // Add the UI elements to the VBox
        registerForm.getChildren().addAll(
            new Label("Username:"), usernameField,
            new Label("Password:"), passwordField,
            new Label("First Name:"), firstNameField,
            new Label("Last Name:"), lastNameField,
            registerButton, backButton
        );

        // Set the event handlers for the buttons
        // The registerButton's handler calls the handleRegister method with the text from each input field
        // The backButton's handler changes the scene back to the login scene
        registerButton.setOnAction(e -> handleRegister(
            usernameField.getText(),
            passwordField.getText(),
            firstNameField.getText(),
            lastNameField.getText()
        ));
        backButton.setOnAction(e -> primaryStage.setScene(loginScene));

        // Create the scene with the VBox as the root node and add it to the primary stage
        // The scene has a width of 300 pixels and a height of 275 pixels
        registerScene = new Scene(registerForm, 300, 275);
    }


    /**
    * Initializes the home scene with UI components and event handlers.
    */
    private void initHomeScene() {
        // Initialize user's full name label with an empty string
        fullNameLabel = new Label("");

        // Initialize buttons for different actions
        Button profileButton = new Button("Edit Profile");
        Button recordsButton = new Button("View Records");
        Button exportButton = new Button("Export Records");
        Button logoutButton = new Button("Logout");

        // Set event handlers for the buttons.
        // These handlers define what happens when each button is clicked.
        // For instance, when the "Edit Profile" button is clicked, the profile scene is displayed.
        profileButton.setOnAction(e -> showProfileScene());
        recordsButton.setOnAction(e -> showRecordsScene());
        exportButton.setOnAction(e -> handleExportRecords());
        logoutButton.setOnAction(e -> showLoginScene());

        // Create and configure a VBox layout, which organizes the elements vertically
        VBox vbox = new VBox(10); // 10 pixels of vertical space between elements
        vbox.setAlignment(Pos.CENTER); // The elements are aligned to the center of the VBox
        // Add the label and the buttons to the VBox
        vbox.getChildren().addAll(fullNameLabel, profileButton, recordsButton, exportButton, logoutButton);

        // Set the VBox as the root of the homeScene, and specify the scene's width and height
        homeScene = new Scene(vbox, 300, 200);
    }

    /**
     * Initializes the profile scene.
     * This scene allows users to view and edit their profile information, including their first and last names.
     */
    private void initProfileScene() {
        // Create the UI elements for the profile scene
        firstNameField = new TextField(); // Text field for entering the first name
        lastNameField = new TextField(); // Text field for entering the last name
        Button saveButton = new Button("Save"); // Button to save the updated profile information
        Button backButton = new Button("Back"); // Button to return to the home scene
    
        // Set the event handlers for the buttons
        // The saveButton's handler calls the handleEditProfile method to save the updated profile information
        // The backButton's handler changes the scene back to the home scene
        saveButton.setOnAction(e -> handleEditProfile());
        backButton.setOnAction(e -> showHomeScene());
    
        // Add the UI elements to the GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("First Name:"), 0, 0); // Add the label and text field for the first name
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1); // Add the label and text field for the last name
        grid.add(lastNameField, 1, 1);
        grid.add(saveButton, 0, 2); // Add the save button
        grid.add(backButton, 1, 2); // Add the back button
    
        // Set the GridPane as the root of the profileScene
        // The scene has a width of 300 pixels and a height of 200 pixels
        profileScene = new Scene(grid, 300, 200);
    }
    

    /**
     * Initializes the records scene.
     * This scene displays a table of health records and provides options to add, edit, delete records and return to the home scene.
     */
    private void initRecordsScene() {
        // Create a TableView to display the health records
        recordsTable = new TableView<>();
    
        // Create columns for the TableView, setting the cell value factory for each column
        TableColumn<HealthRecord, Float> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
    
        TableColumn<HealthRecord, Float> temperatureColumn = new TableColumn<>("Temperature");
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
    
        TableColumn<HealthRecord, String> bloodPressureColumn = new TableColumn<>("Blood Pressure");
        bloodPressureColumn.setCellValueFactory(new PropertyValueFactory<>("bloodPressure"));
    
        TableColumn<HealthRecord, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
    
        TableColumn<HealthRecord, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    
        // Add columns to the table
        recordsTable.getColumns().addAll(weightColumn, temperatureColumn, bloodPressureColumn, noteColumn, dateColumn);
        
        // Create buttons for adding, editing, deleting records, and returning to the home scene
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");
    
        // Set event handlers for the buttons
        addButton.setOnAction(e -> handleAddRecord()); // Add a new record
        editButton.setOnAction(e -> handleEditRecord()); // Edit an existing record
        deleteButton.setOnAction(e -> handleDeleteRecord()); // Delete an existing record
        backButton.setOnAction(e -> showHomeScene()); // Return to the home scene
    
        // Create and configure the VBox layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(recordsTable, addButton, editButton, deleteButton, backButton);
    
        // Set the VBox as the root of the recordsScene
        recordsScene = new Scene(vbox, 400, 300); // Set the size of the scene
    }
    


    /**
     * Initializes the create record scene.
     * This scene allows the user to input new health record data and save it.
     * There are also options to clear input fields and return to the records scene.
     */
    private void initCreateRecordScene() {
        // Clear previous input fields
        weightField.clear();
        temperatureField.clear();
        bloodPressureField.clear();
        noteField.clear();

        // Create a "Save" button and set its event handler to save the record when clicked
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> handleSaveRecord());

        // Create a "Back" button and set its event handler to return to the records scene when clicked
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showRecordsScene());

        // Create and configure the GridPane layout
        GridPane grid = new GridPane();
        // Add the labels and input fields to the grid
        grid.add(new Label("Weight:"), 0, 0);
        grid.add(weightField, 1, 0);
        grid.add(new Label("Temperature:"), 0, 1);
        grid.add(temperatureField, 1, 1);
        grid.add(new Label("Blood Pressure:"), 0, 2);
        grid.add(bloodPressureField, 1, 2);
        grid.add(new Label("Note:"), 0, 3);
        grid.add(noteField, 1, 3);
        // Add the buttons to the grid
        grid.add(saveButton, 0, 4);
        grid.add(backButton, 1, 4);

        // Set the GridPane as the root of the createRecordScene and set its size
        createRecordScene = new Scene(grid, 400, 300);
    }

    /**
     * Initializes the edit record scene.
     * This scene allows the user to modify an existing health record and save the changes.
     * The input fields are first cleared to ensure that previous data doesn't persist.
     * There are also options to clear input fields and return to the records scene.
     */
    private void initEditRecordScene() {
        // Clear previous input fields
        weightField.clear();
        temperatureField.clear();
        bloodPressureField.clear();
        noteField.clear();
        
        // Create a "Save" button and set its event handler to save the edited record when clicked
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> handleSaveEditedRecord());

        // Create a "Back" button and set its event handler to return to the records scene when clicked
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showRecordsScene());

        // Create and configure the GridPane layout using the helper method createRecordGridPane()
        GridPane grid = createRecordGridPane();
        // Add the buttons to the grid
        grid.add(saveButton, 0, 4);
        grid.add(backButton, 1, 4);

        // Set the GridPane as the root of the editRecordScene and set its size
        editRecordScene = new Scene(grid, 400, 300);
    }


    /**
     * Creates a GridPane layout for adding or editing a health record.
     * This layout includes labels and input fields for recording health data.
     * The GridPane is used in both createRecordScene and editRecordScene.
     * This method helps to avoid duplication of code, improving maintainability.
     *
     * @return A GridPane with labels and input fields for health record data.
     */
    private GridPane createRecordGridPane() {
        // Create a new GridPane layout
        GridPane grid = new GridPane();
        // Center align the elements in the GridPane
        grid.setAlignment(Pos.CENTER);
        // Set horizontal and vertical gaps between the elements
        grid.setHgap(10);
        grid.setVgap(10);

        // Add labels and corresponding text fields for weight, temperature, blood pressure, and note to the GridPane
        grid.add(new Label("Weight:"), 0, 0);
        grid.add(weightField, 1, 0);
        grid.add(new Label("Temperature:"), 0, 1);
        grid.add(temperatureField, 1, 1);
        grid.add(new Label("Blood Pressure:"), 0, 2);
        grid.add(bloodPressureField, 1, 2);
        grid.add(new Label("Note:"), 0, 3);
        grid.add(noteField, 1, 3);

        // Return the constructed GridPane
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
        initCreateRecordScene();
        primaryStage.setScene(createRecordScene);
    }


    /**
     * This method populates the fields of the 'editRecordScene' with the data from the provided health record. 
     * It then sets the primary stage to show the 'editRecordScene'.
     * It is used when the user wishes to edit a specific health record.
     *
     * @param record The health record whose data is to be edited. This is passed to populate the fields 
     *               in the 'editRecordScene'.
     */
    private void showEditRecordScene(HealthRecord record) {
        // Populate the weight field with the record's weight
        weightField.setText(String.valueOf(record.getWeight()));
        // Populate the temperature field with the record's temperature
        temperatureField.setText(String.valueOf(record.getTemperature()));
        // Populate the blood pressure field with the record's blood pressure
        bloodPressureField.setText(String.valueOf(record.getBloodPressure()));
        // Populate the note field with the record's note
        noteField.setText(record.getNote());

        // Set the scene of the primary stage to the 'editRecordScene'
        primaryStage.setScene(editRecordScene);
    }


    /**
     * This method generates and displays an error alert dialog box with a specified message.
     * It is used when an error occurs in the application and the user needs to be notified.
     *
     * @param message The specific error message that is to be displayed in the alert dialog.
     */
    private void showErrorAlert(String message) {
        // Create a new Alert of type ERROR
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Set the title of the Alert dialog box
        alert.setTitle("Error");
        // Set the header of the Alert dialog box to null. This means no header text will be displayed.
        alert.setHeaderText(null);
        // Set the content of the Alert dialog box to the provided error message
        alert.setContentText(message);
        // Display the Alert dialog box and wait for the user to close it before returning to the application
        alert.showAndWait();
    }

    /**
     * This method is responsible for updating the records table view to display the current user's health records.
     * The records are retrieved from the healthRecordController. If the recordsTable, healthRecordController, 
     * or the currentUser is null, a relevant error message is printed to the console and the method returns without
     * updating the records table.
     */
    private void updateRecordTable() {
        // Check if recordsTable is null and print an error message if so
        if (recordsTable == null) {
            System.out.println("recordsTable is null");
            return;
        }
        // Check if healthRecordController is null and print an error message if so
        if (healthRecordController == null) {
            System.out.println("healthRecordController is null");
            return;
        }
        // Check if currentUser is null and print an error message if so
        if (currentUser == null) {
            System.out.println("currentUser is null");
            return;
        }
        // Retrieve the current user's health records
        List<HealthRecord> healthRecords = healthRecordController.getHealthRecordsForUser(currentUser);
        // Check if the retrieved records list is null and print an error message if so
        if (healthRecords == null) {
            System.out.println("getHealthRecordsForUser returned null");
            return;
        }
        // Update the recordsTable items with the retrieved health records, converting the list to an ObservableList
        recordsTable.setItems(FXCollections.observableArrayList(healthRecords));
    }
    


    /**
     * This method is responsible for handling user login. It attempts to login using the 
     * provided username and password by delegating to the userController.
     * If the login is successful, the currentUser is set as the user with the provided username, 
     * and the home scene is shown on the stage.
     * If the login is not successful, an error alert dialog is shown with a message indicating 
     * an invalid username or password.
     *
     * @param username The username provided by the user in the login form.
     * @param password The password provided by the user in the login form.
     */
    private void handleLogin(String username, String password) {
        // Attempt to login with the provided username and password
        boolean loginSuccessful = userController.login(username, password);
        if (loginSuccessful) {
            // If login is successful, retrieve the user with the provided username and show the home scene
            currentUser = userController.getUserByUsername(username);
            showHomeScene();
        } else {
            // If login is not successful, show an error alert dialog indicating an invalid username or password
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
        // Validate the input fields.
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            showErrorAlert("All fields must be filled out.");
            return;
        }

        User user = userController.register(username, password, firstName, lastName);
        if (user != null) {
            currentUser = user;
            showHomeScene();
        } else {
            showErrorAlert("Registration failed. Please try again.");
        }
    }



    /**
     * This method handles the action of editing a user's profile. It retrieves the new first name and 
     * last name from their respective fields. If either field is empty, it displays an error alert.
     * Otherwise, it updates the currentUser's first name and last name, saves the changes through 
     * the userController, and then shows the home scene.
     * 
     * @post If the first name and last name fields are not empty, the currentUser's first name and 
     *       last name will be updated, and the home scene will be displayed.
     * @post If the first name or last name fields are empty, an error alert will be displayed.
     */
    private void handleEditProfile() {
        // Retrieve the new first name and last name from the respective fields
        String newFirstName = firstNameField.getText();
        String newLastName = lastNameField.getText();
        
        // If either field is empty, show an error alert
        if (newFirstName.isEmpty() || newLastName.isEmpty()) {
            showErrorAlert("First name and last name fields cannot be empty.");
        } else {
            // Otherwise, update the currentUser's first name and last name, save the changes, and show the home scene
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
     * Counts the number of words in a string.
     *
     * @param text The string to count words in.
     * @return The number of words in the string.
     */
    private int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] words = text.split("\\s+");
        return words.length;
    }


    /**
     * This method handles saving a health record. It retrieves the data from the input fields, including
     * weight, temperature, blood pressure, and a note. If the weight and temperature fields are empty, they
     * default to 0. It then creates a new HealthRecord object with the retrieved data and the current date 
     * and user ID. The new record is then added to the database. Upon successful completion, the input 
     * fields are cleared, and the home scene is displayed. If an SQL exception occurs when accessing the 
     * database, or a NumberFormatException occurs when converting the strings to floats, the exceptions 
     * are caught and printed to the console.
     * 
     * @post The new health record is added to the database, the input fields are cleared, and the home 
     *       scene is displayed.
     * @throws SQLException if an error occurs when adding the new record to the database.
     * @throws NumberFormatException if the weight or temperature fields contain non-numeric values.
     */
    private void handleSaveRecord() {
        try {
            // Get data from input fields
            String weightText = weightField.getText();
            String temperatureText = temperatureField.getText();
            String bloodPressure = bloodPressureField.getText();
            String note = noteField.getText();
        
            // Convert the strings to floats
            float weight = weightText.isEmpty() ? 0 : Float.parseFloat(weightText);
            float temperature = temperatureText.isEmpty() ? 0 : Float.parseFloat(temperatureText);
            
            LocalDate date = LocalDate.now(); // or get this from an input field if you have one
            int userId = currentUser.getId(); // or however you're keeping track of the current user

            // Limit the note field to 50 words
            int noteWordCount = note.split("\\s+").length;
            if (noteWordCount > 50) {
                showErrorAlert("Note should be within 50 words.");
                return;
            }

    
            // Create a new HealthRecord object
            HealthRecord newRecord = new HealthRecord(0, weight, temperature, bloodPressure, note, date, userId);
    
            // Use the Database class to add the new record to the database
            database.addHealthRecord(newRecord);

            // Clear the fields after successfully saving the record
            weightField.clear();
            temperatureField.clear();
            bloodPressureField.clear();
            noteField.clear();
    
            // Optionally, switch back to the previous scene or clear the input fields
            showHomeScene();
        } catch (SQLException e) {
            // Handle any errors that might occur when trying to access the database
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle any errors that might occur when converting the strings to floats
            e.printStackTrace();
        }
    }
    
    /**
     * This method handles saving changes made to an existing health record. It retrieves the updated data 
     * from the input fields, including weight, temperature, blood pressure, and a note. It then retrieves 
     * the currently selected health record's ID. If no record is selected, a message is printed to the 
     * console and the method returns. A new HealthRecord object is then created with the updated data, 
     * maintaining the original record's date and user ID. The record is then updated in the database. Upon 
     * successful completion, the input fields are cleared and the home scene is displayed. If a 
     * NumberFormatException occurs when converting the weight or temperature fields to floats, the 
     * exception is caught and a message is printed to the console.
     * 
     * @post The selected health record in the database is updated with the new data, the input fields are 
     *       cleared, and the home scene is displayed.
     * @throws NumberFormatException if the weight or temperature fields contain non-numeric values.
     */
    private void handleSaveEditedRecord() {
        try {
            // Get the updated data from the fields
            float updatedWeight = Float.parseFloat(weightField.getText());
            float updatedTemperature = Float.parseFloat(temperatureField.getText());
            String updatedBloodPressure = bloodPressureField.getText();
            String updatedNote = noteField.getText();
    
            // Get the selected record's id
            HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
            if (selectedRecord == null) {
                System.out.println("No record selected.");
                return;
            }
            int recordId = selectedRecord.getId();
    
            // Create a new HealthRecord object with the updated data
            HealthRecord updatedRecord = new HealthRecord(recordId, updatedWeight, updatedTemperature, updatedBloodPressure, updatedNote, selectedRecord.getDate(), selectedRecord.getUserId());
    
            // Update the record in the database
            healthRecordController.updateHealthRecord(updatedRecord);
    
            // Clear the fields after successfully updating the record
            weightField.clear();
            temperatureField.clear();
            bloodPressureField.clear();
            noteField.clear();
    
            // Switch to the home scene
            showHomeScene();
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Invalid weight or temperature input.");
        }
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
                for (HealthRecord record : healthRecordController.getHealthRecordsForUser(currentUser)) {
                    writer.write(record.toString() + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                showErrorAlert("Failed to export records: " + e.getMessage());
            }
        }
    }
    
    


}