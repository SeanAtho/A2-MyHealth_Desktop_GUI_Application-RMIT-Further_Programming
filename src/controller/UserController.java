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
     */
    public UserController(Database database) {
        this.database = database;
    }

    /**
     * Returns the User object with the provided username.
     *
     * @param username The username of the user to find.
     * @return The User object with the provided username, or null if not found.
     */
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Registers a new user with the given username and password.
     *
     * @param username  The desired username for the new user.
     * @param password  The desired password for the new user.
     * @param firstName The first name of the new user.
     * @param lastName  The last name of the new user.
     * @return The newly registered User object or null if the registration failed.
     */
    public User register(String username, String password, String firstName, String lastName) {
        User user = getUserByUsername(username);

        if (user != null) {
            // A user with the same username already exists, so registration fails
            return null;
        }

        String sql = "INSERT INTO users(username, password, firstName, lastName) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getUserByUsername(username);
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
     * @param updatedUser The User object containing the updated user information.
     */
    public void updateUser(User updatedUser) {
        String sql = "UPDATE users SET username = ?, password = ?, firstName = ?, lastName = ? WHERE id = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, updatedUser.getUsername());
            pstmt.setString(2, updatedUser.getPassword());
            pstmt.setString(3, updatedUser.getFirstName());
            pstmt.setString(4, updatedUser.getLastName());
            pstmt.setInt(5, updatedUser.getId());
            pstmt.executeUpdate();
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
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of all users.
     *
     * @return a list of all users
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement stmt = database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Logs out the currently logged in user.
     */
    public void logout() {
        // implementation details depend on the specifics of your application
    }
}
