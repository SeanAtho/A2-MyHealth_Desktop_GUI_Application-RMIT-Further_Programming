package model;

import java.util.Objects;

/**
 * The User class represents a user in the system.
 * It contains attributes such as a username, password, first name, and last name.
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String id;

    /**
     * Constructor for the User class.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     */
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets the username of the user.
     * @param username the new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     * @param password the new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the first name of the user.
     * @param firstName the new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName the new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the username of the user.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the first name of the user.
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the user.
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns a string representation of the user.
     * @return a string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * Determines whether two User objects are equal based on their username, password, first name, and last name.
     *
     * @param o the other object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    /**
     * Returns a hash code for the User
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName);
    }
    
    /**
     * Gets the ID of the user.
     * @return The user ID as a string.
     */
    public String getId() {
        return id;
    }

}