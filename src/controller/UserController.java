package controller;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * The UserController class is responsible for managing the user accounts in the system.
 */
public class UserController {

    // List of all registered users
    private List<User> users;

    /**
     * Constructor for UserController class.
     */
    public UserController() {
        users = new ArrayList<>();
    }

    /**
     * Returns the User object with the provided username.
     * @param username The username of the user to find.
     * @return The User object with the provided username, or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }


    /**
     * Registers a new user with the given username and password.
     *
     * @param username The desired username for the new user.
     * @param password The desired password for the new user.
     * @return The newly registered User object or null if the registration failed.
     */
    public User register(String username, String password, String firstName, String lastName) {
        // Check if a user with the given username already exists
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(username)) {
                // A user with the same username already exists, so registration fails
                return null;
            }
        }

        // If the username is not taken, create a new User object and add it to the users list
        User newUser = new User(username, password, firstName, lastName);
        users.add(newUser);

        // Return the newly registered User object
        return newUser;
    }

    /**
     * Authenticates a user with the provided username and password.
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return true if the user is authenticated, false otherwise
     */
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the information of the given user.
     * @param updatedUser The User object containing the updated user information.
     */
    public void updateUser(User updatedUser) {
        // Implement the logic to update the user's information.
        // This could involve searching for the user in the list of users, then updating the user object with the new information.
        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            if (currentUser.getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
    }


    /**
     * Edits the profile information of an existing user.
     * @param user the updated User object containing the new user information
     */
    public void editProfile(User user) {
        for (User u : users) {
            if (u.equals(user)) {
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
            }
        }
    }

    /**
     * Deletes the profile of an existing user.
     * @param user the User object to be deleted
     */
    public void deleteProfile(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }
    

    /**
     * Logs out the currently logged in user.
     */
    public void logout() {
        // implementation details
    }
}
