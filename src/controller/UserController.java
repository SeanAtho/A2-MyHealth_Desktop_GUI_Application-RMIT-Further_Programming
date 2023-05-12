package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.HealthRecord;
import model.User;

/**
 * The UserController class is responsible for managing the user accounts in the system.
 */
public class UserController {
    // Attributes

    // The currently logged-in user
    private User currentUser;

    // The database object for handling data persistence
    private Database database;

    // The controller for handling health record-related actions
    private HealthRecordController healthRecordController;

    /**
     * Constructs a UserController with a given database and health record controller.
     *
     * @param database the database for storing user information
     * @param healthRecordController the controller for handling health record-related actions
     */
    public UserController(Database database, HealthRecordController healthRecordController) {
        this.database = database;
        this.healthRecordController = healthRecordController;
    }


    /**
     * Retrieves a user from the database by their username.
     *
     * @param username the username of the user to retrieve
     * @return the user with the specified username, or null if no such user exists
     */
    public User getUserByUsername(String username) {
        // Initialize User object to hold retrieved user information
        User user = null;

        // SQL query string to find user with given username
        String sql = "SELECT * FROM users WHERE username = ?";

        // Try-with-resources statement to automatically close the resources after use
        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {

            // Set the username in the prepared statement
            pstmt.setString(1, username);

            // Execute the SQL query and get the result set
            ResultSet rs = pstmt.executeQuery();

            // If a user with the given username exists, create a new User object with the retrieved data
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), 
                                rs.getString("password"), rs.getString("firstName"), 
                                rs.getString("lastName"));
            }

             // Catch any SQL exceptions and print the stack trace for debugging
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the User object if a user was found, or null otherwise
        return user;
    }

    /**
     * Registers a new user with the specified username, password, and name.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @param firstName the first name of the new user
     * @param lastName the last name of the new user
     * @return the newly registered user, or null if the username is already taken
     */
    public User register(String username, String password, String firstName, String lastName) {
        // Attempt to retrieve a user with the specified username from the database
        User user = getUserByUsername(username);

        if (user != null) {
            // A user with the same username already exists, so registration fails
            return null;
        }

        try {
            // Create a new User object with the specified username, password, and name
            user = new User(-1, username, password, firstName, lastName);
            // Add the new user to the database
            database.addUser(user);
            // Retrieve the user from the database again to get the user ID assigned by the database
            user = getUserByUsername(username);  // Get the user again to retrieve the assigned ID
        } catch (SQLException e) {
            // If a SQLException occurs, print the stack trace and set the user to null
            e.printStackTrace();
            user = null;
        }
        
        // Return the newly registered user, or null if a user with the specified username already exists or if a SQLException occurs
        return user;
    }

    /**
     * Authenticates a user with the specified username and password.
     *
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return true if the user is successfully authenticated, false otherwise
     */
    public boolean login(String username, String password) {

        // Attempt to retrieve the user with the provided username from the database
        User user = getUserByUsername(username);

         // Check if the user exists and if the provided password matches the user's password
        if (user != null && user.getPassword().equals(password)) {

            // If the user is authenticated, set the currentUser attribute to the authenticated user
            this.currentUser = user;

            // Return true to indicate successful authentication
            return true;
        }
        // If the user does not exist or the password does not match, return false to indicate failed authentication
        return false;
    }

    /**
     * Adds a new health record to the current user's health records.
     *
     * This function calls the addHealthRecord method of the healthRecordController 
     * to add the provided HealthRecord object to the current user's health records 
     * in the database.
     *
     * @param record the HealthRecord object to be added to the current user's health records
     */
    public void addHealthRecordToCurrentUser(HealthRecord record) {
        // Call the addHealthRecord method of the healthRecordController to add the provided
        // HealthRecord object to the current user's health records in the database
        healthRecordController.addHealthRecord(currentUser, record);
    }

    /**
     * Updates the data of a user in the database.
     *
     * This method is used to update the information of an existing user in the 
     * database. It makes a call to the updateUser method of the Database class, 
     * passing the updated user information to it. If an SQL exception occurs during 
     * this operation, the exception's stack trace is printed.
     *
     * @param updatedUser a User object that contains the updated information of the user
     */
    public void updateUser(User updatedUser) {
        try {
             // Call the updateUser method of the Database class, passing the updated user information to it
            database.updateUser(updatedUser);
        } catch (SQLException e) {
            // Print the stack trace of any SQLException that may occur
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user's profile from the database.
     *
     * This method is responsible for removing a user's profile from the database. 
     * It does so by calling the deleteUser method of the Database class and passing 
     * the ID of the user to be deleted. If a SQLException occurs during this process, 
     * the exception's stack trace is printed.
     *
     * @param user a User object representing the user whose profile is to be deleted
     */
    public void deleteProfile(User user) {
        try {
            // Call the deleteUser method of the Database class, passing the ID of the user to be deleted
            database.deleteUser(user.getId());
        } catch (SQLException e) {
            // Print the stack trace of any SQLException that may occur
            e.printStackTrace();
        }
    }


    /**
     * Retrieves all users from the database.
     *
     * This method queries the database for all users and returns a list of User objects. 
     * It calls the getAllUsers method of the Database class, which retrieves the information 
     * for all users from the database. If a SQLException occurs during this process, 
     * the exception's stack trace is printed and an empty list of users is returned.
     *
     * @return a List of User objects representing all users in the database. 
     *         If a SQLException occurs, an empty list is returned.
     */
    public List<User> getUsers() {
        // Initialize an empty list of User objects
        List<User> users = new ArrayList<>();
        try
        {
            // Call the getAllUsers method of the Database class to retrieve all users
            users = database.getAllUsers();
        } catch (SQLException e) {
            // If a SQLException occurs, print the stack trace
            e.printStackTrace();
        }
        // Return the list of users
        return users;
    }

    /**
     * Logs out the currently logged-in user.
     */
    public void logout() {
      
    }
}
