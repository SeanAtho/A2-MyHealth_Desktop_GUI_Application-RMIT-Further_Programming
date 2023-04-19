package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void testUser() {
        String username = "john123";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        
        User user = new User(username, password, firstName, lastName);
        
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
    }
    
    @Test
    public void testSetters() {
        String username = "john123";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        
        User user = new User(username, password, firstName, lastName);
        
        String newUsername = "jane456";
        String newPassword = "newpassword";
        String newFirstName = "Jane";
        String newLastName = "Smith";
        
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        
        assertEquals(newUsername, user.getUsername());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newFirstName, user.getFirstName());
        assertEquals(newLastName, user.getLastName());
    }
    
    @Test
    public void testEquals() {
        String username = "john123";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        
        User user1 = new User(username, password, firstName, lastName);
        User user2 = new User(username, password, firstName, lastName);
        
        assertTrue(user1.equals(user2));
    }
    
    @Test
    public void testHashCode() {
        String username = "john123";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        
        User user1 = new User(username, password, firstName, lastName);
        User user2 = new User(username, password, firstName, lastName);
        
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
