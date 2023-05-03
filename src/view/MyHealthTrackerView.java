package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import controller.HealthRecordController;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HealthRecord;
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
    
    public void showRecordDetailsScene(HealthRecord record) {
        // Create UI components for record details scene
        Label titleLabel = new Label("Record Details");
        Label dateLabel = new Label("Date: " + record.getDate().toString());
        Label weightLabel = new Label("Weight: " + record.getWeight() + " lbs");
        Label heightLabel = new Label("Height: " + record.getHeight() + " in");
        Label bpLabel = new Label("Blood Pressure: " + record.getBloodPressure());
        Button backButton = new Button("Back");
    
        // Set event handler for back button
        backButton.setOnAction(e -> handleBack());
    
        // Create layout for record details scene
        VBox recordDetailsLayout = new VBox(20);
        recordDetailsLayout.setAlignment(Pos.CENTER);
        recordDetailsLayout.getChildren().addAll(titleLabel, dateLabel, weightLabel, heightLabel, bpLabel, backButton);
    
        // Set the record details scene as the primary stage scene
        Scene scene = new Scene(recordDetailsLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    public void clearErrorAlert() {
        errorAlert.setText("");
    }
    
    
    public void handleLogin(String username, String password) {
        try {
            // Use the UserController to log in with the given credentials
            currentUser = userController.login(username, password);
            
            // If the login was successful, show the home scene
            showHomeScene();
        } catch (InvalidCredentialsException e) {
            // If the login failed, show an error alert
            showErrorAlert("Invalid username or password");
        }
    }
    
    
    public void handleLogout() {
        // set the current user to null and show the login scene
        currentUser = null;
        showLoginScene();
    }
    
    
    public void handleCreateProfile() {
        // Create UI components for create profile scene
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        Label heightLabel = new Label("Height (in):");
        TextField heightField = new TextField();
        Label weightLabel = new Label("Weight (lbs):");
        TextField weightField = new TextField();
        Button createButton = new Button("Create");
        Button cancelButton = new Button("Cancel");
    
        // Set event handlers for UI components
        createButton.setOnAction(e -> {
            // Validate input fields
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            int age = parseIntField(ageField);
            int height = parseIntField(heightField);
            int weight = parseIntField(weightField);
    
            if (firstName.isEmpty() || lastName.isEmpty() || age == -1 || height == -1 || weight == -1) {
                showErrorAlert("Please fill in all fields with valid values.");
                return;
            }
    
            // Create the new user and set it as the current user
            User newUser = userController.createUser(firstName, lastName, age, height, weight);
            if (newUser == null) {
                showErrorAlert("Failed to create user. Please try again.");
                return;
            }
            currentUser = newUser;
    
            // Switch to home scene
            showHomeScene();
            clearErrorAlert();
        });
        cancelButton.setOnAction(e -> {
            // Switch to login scene
            showLoginScene();
            clearErrorAlert();
        });
    
        // Create layout for create profile scene
        VBox createProfileLayout = new VBox(20);
        createProfileLayout.setAlignment(Pos.CENTER);
        createProfileLayout.getChildren().addAll(
            firstNameLabel, firstNameField,
            lastNameLabel, lastNameField,
            ageLabel, ageField,
            heightLabel, heightField,
            weightLabel, weightField,
            createButton, cancelButton
        );
    
        // Set the create profile scene as the primary stage scene
        Scene scene = new Scene(createProfileLayout, 400, 400);
        primaryStage.setScene(scene);
    }
    
    
    public void handleEditProfile() {
        // Create UI components for profile edit scene
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTextField = new TextField(currentUser.getFirstName());
    
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTextField = new TextField(currentUser.getLastName());
    
        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField(String.valueOf(currentUser.getAge()));
    
        Label heightLabel = new Label("Height (cm):");
        TextField heightTextField = new TextField(String.valueOf(currentUser.getHeight()));
    
        Label weightLabel = new Label("Weight (kg):");
        TextField weightTextField = new TextField(String.valueOf(currentUser.getWeight()));
    
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
    
        // Set event handlers for UI components
        saveButton.setOnAction(e -> {
            try {
                String firstName = firstNameTextField.getText().trim();
                String lastName = lastNameTextField.getText().trim();
                int age = Integer.parseInt(ageTextField.getText().trim());
                double height = Double.parseDouble(heightTextField.getText().trim());
                double weight = Double.parseDouble(weightTextField.getText().trim());
    
                userController.updateUser(currentUser.getUsername(), firstName, lastName, age, height, weight);
    
                currentUser = userController.getUser(currentUser.getUsername());
    
                showHomeScene();
            } catch (NumberFormatException ex) {
                showErrorAlert("Please enter a valid age, height, and weight.");
            } catch (Exception ex) {
                showErrorAlert("An error occurred while updating your profile.");
            }
        });
    
        cancelButton.setOnAction(e -> showHomeScene());
    
        // Create layout for profile edit scene
        GridPane editProfileLayout = new GridPane();
        editProfileLayout.setAlignment(Pos.CENTER);
        editProfileLayout.setHgap(10);
        editProfileLayout.setVgap(10);
        editProfileLayout.setPadding(new Insets(25, 25, 25, 25));
    
        editProfileLayout.add(firstNameLabel, 0, 0);
        editProfileLayout.add(firstNameTextField, 1, 0);
    
        editProfileLayout.add(lastNameLabel, 0, 1);
        editProfileLayout.add(lastNameTextField, 1, 1);
    
        editProfileLayout.add(ageLabel, 0, 2);
        editProfileLayout.add(ageTextField, 1, 2);
    
        editProfileLayout.add(heightLabel, 0, 3);
        editProfileLayout.add(heightTextField, 1, 3);
    
        editProfileLayout.add(weightLabel, 0, 4);
        editProfileLayout.add(weightTextField, 1, 4);
    
        editProfileLayout.add(saveButton, 0, 5);
        editProfileLayout.add(cancelButton, 1, 5);
    
        // Set the profile edit scene as the primary stage scene
        Scene scene = new Scene(editProfileLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private void handleDeleteProfile() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Profile");
        alert.setHeaderText("Are you sure you want to delete your profile?");
        alert.setContentText("This action cannot be undone.");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // delete the current user's profile
            userController.deleteUser(currentUser.getUsername());
    
            // reset the current user to null
            currentUser = null;
    
            // show the login scene
            showLoginScene();
        }
    }
    
    
    public void handleAddRecord() {
        // Create UI components for add record form
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker();
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea();
        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");
    
        // Set event handlers for UI components
        submitButton.setOnAction(e -> {
            String title = titleField.getText();
            LocalDate date = datePicker.getValue();
            String description = descriptionArea.getText();
            if (title.isEmpty() || date == null || description.isEmpty()) {
                showErrorAlert("Please fill in all fields.");
            } else {
                try {
                    healthRecordController.addHealthRecord(currentUser, title, date, description);
                    showRecordsScene();
                } catch (DuplicateRecordException ex) {
                    showErrorAlert("A record with the same title and date already exists.");
                }
            }
        });
        cancelButton.setOnAction(e -> showRecordsScene());
    
        // Create layout for add record form
        GridPane addRecordLayout = new GridPane();
        addRecordLayout.setAlignment(Pos.CENTER);
        addRecordLayout.setHgap(10);
        addRecordLayout.setVgap(10);
        addRecordLayout.setPadding(new Insets(25, 25, 25, 25));
        addRecordLayout.add(titleLabel, 0, 0);
        addRecordLayout.add(titleField, 1, 0);
        addRecordLayout.add(dateLabel, 0, 1);
        addRecordLayout.add(datePicker, 1, 1);
        addRecordLayout.add(descriptionLabel, 0, 2);
        addRecordLayout.add(descriptionArea, 1, 2);
        addRecordLayout.add(submitButton, 0, 3);
        addRecordLayout.add(cancelButton, 1, 3);
    
        // Set the add record form as the primary stage scene
        Scene scene = new Scene(addRecordLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private void handleEditRecord(HealthRecord record) {
        // Create UI components for the edit record dialog
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField(record.getTitle());
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea(record.getDescription());
        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker(record.getDate());
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
    
        // Set event handlers for UI components
        saveButton.setOnAction(e -> {
            // Update the record with the new information
            record.setTitle(titleField.getText());
            record.setDescription(descriptionArea.getText());
            record.setDate(datePicker.getValue());
    
            // Update the record in the controller
            healthRecordController.updateRecord(record);
    
            // Show the records scene
            showRecordsScene();
        });
        cancelButton.setOnAction(e -> showRecordDetailsScene(record));
    
        // Create layout for the edit record dialog
        GridPane editLayout = new GridPane();
        editLayout.setHgap(10);
        editLayout.setVgap(10);
        editLayout.setPadding(new Insets(10, 10, 10, 10));
        editLayout.add(titleLabel, 0, 0);
        editLayout.add(titleField, 1, 0);
        editLayout.add(descriptionLabel, 0, 1);
        editLayout.add(descriptionArea, 1, 1);
        editLayout.add(dateLabel, 0, 2);
        editLayout.add(datePicker, 1, 2);
        editLayout.add(saveButton, 0, 3);
        editLayout.add(cancelButton, 1, 3);
    
        // Show the edit record dialog
        Scene scene = new Scene(editLayout);
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setScene(scene);
        dialog.show();
    }
    
    
    private void handleDeleteRecord() {
        // Get the selected record
        HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
    
        if (selectedRecord == null) {
            showErrorAlert("Please select a record to delete.");
            return;
        }
    
        // Show confirmation dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this record?");
        alert.setContentText(selectedRecord.toString());
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the record
            boolean deleted = healthRecordController.deleteRecord(selectedRecord);
            if (deleted) {
                clearErrorAlert();
                showRecordsScene();
            } else {
                showErrorAlert("Error deleting record.");
            }
        }
    }
    
    
    public void handleViewRecordDetails() {
        // get the selected record from the records table
        HealthRecord selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
    
        // if a record is selected, switch to the record details scene and display its details
        if (selectedRecord != null) {
            showRecordDetailsScene(selectedRecord);
        } else {
            showErrorAlert("Please select a record.");
        }
    }
    
    
    public void updateRecordTable() {
        ObservableList<HealthRecord> records = FXCollections.observableArrayList(healthRecordController.getAllRecords(currentUser));
        recordTable.setItems(records);
    }
    
    
    public void updateProfileFields() {
        if (currentUser != null) {
            // Update the text fields with the user's information
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
            ageField.setText(String.valueOf(currentUser.getAge()));
            heightField.setText(String.valueOf(currentUser.getHeight()));
            weightField.setText(String.valueOf(currentUser.getWeight()));
            genderField.setText(currentUser.getGender().toString());
        }
    }
    
    
}
