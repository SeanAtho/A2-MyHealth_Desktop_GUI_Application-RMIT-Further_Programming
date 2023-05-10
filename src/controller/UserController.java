package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.User;

/**
 * The UserController class is responsible for managing the user accounts in the system.
 */
public class UserController {

    private Database database;

    /**
     * Constructor for UserController class.
     *
     * @param database the Database object to be used by this controller
     */
    public UserController(Database database) {
        this.database = database;
    }

    /**
     * Returns the User object with the provided username.
     *
     * @param username the username of the user to find
     * @return the User object with the provided username, or null if not found
     */
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), 
                                rs.getString("password"), rs.getString("firstName"), 
                                rs.getString("lastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Registers a new user with the given username and password.
     *
     * @param username  the desired username for the new user
     * @param password  the desired password for the new user
     * @param firstName the first name of the new user
     * @param lastName  the last name of the new user
     * @return the newly registered User object or null if the registration failed
     */
    public User register(String username, String password, String firstName, String lastName) {
        User user = getUserByUsername(username);

        if (user != null) {
            // A user with the same username already exists, so registration fails
            return null;
        }

        try {
            user = new User(-1, username, password, firstName, lastName);
            database.addUser(user);
            user = getUserByUsername(username);  // Get the user again to retrieve the assigned ID
        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        }

        return user;
    }

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return true if the user is authenticated, false otherwise
     */
    public boolean login(String username, String password) {
        User user = getUserByUsername(username);

        return user != null && user.getPassword().equals(password);
    }

    /**
     * Updates the information of the given user.
     *
     * @param updatedUser the User object containing the updated user information
     */
    public void updateUser(User updatedUser) {
        try {
            database.updateUser(updatedUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the profile of an existing user.
     *
     * @param user the User object to be deleted
     */
    public void deleteProfile(User user) {
        try {
            database.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of all users in the database.
     *
     * @return a list of all User objects
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try
        {
            users = database.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Logs out the currently logged in user.
     * Implementation details depend on the specifics of your application.
     */
    public void logout() {
        // implementation details depend on the specifics of your application
    }
}
