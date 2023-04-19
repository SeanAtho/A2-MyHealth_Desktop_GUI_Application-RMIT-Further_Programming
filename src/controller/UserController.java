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
     * Creates a new user profile with the provided user information.
     * @param user the User object containing the user's information
     */
    public void createProfile(User user) {
        users.add(user);
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
