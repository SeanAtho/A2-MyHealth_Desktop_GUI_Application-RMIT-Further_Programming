package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import model.HealthRecord;
import model.User;

/**
 * Unit test class for the User model.
 */
public class UserTest {
    // User instance that will be tested
    private User user;
    
    // HealthRecord instance that will be used in test cases
    private HealthRecord healthRecord;

    /**
     * This method is executed before each test. It's used to set up the testing environment.
     */
    @Before
    public void setUp() {
        // Initialize a new User instance before each test
        user = new User(1, "username", "password", "firstName", "lastName");

        // Initialize a new HealthRecord instance before each test
        healthRecord = new HealthRecord(1, 70.0f, 36.5f, "120/80", "note", LocalDate.now(), 1);
    }

    /**
     * Test to verify the constructor and getter methods in User class.
     */
    @Test
    public void testConstructor() {
        assertEquals(1, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertTrue(user.getHealthRecords().isEmpty());
    }

    /**
     * Test to verify the setter methods in User class.
     */
    @Test
    public void testSetters() {
        user.setId(2);
        user.setUsername("newUsername");
        user.setPassword("newPassword");
        user.setFirstName("newFirstName");
        user.setLastName("newLastName");
        
        assertEquals(2, user.getId());
        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("newFirstName", user.getFirstName());
        assertEquals("newLastName", user.getLastName());
    }

    /**
     * Test to verify that the addHealthRecord method in User class works correctly.
     */
    @Test
    public void testAddHealthRecord() {
        user.addHealthRecord(healthRecord);
        assertTrue(user.getHealthRecords().contains(healthRecord));
    }

    /**
     * Test to verify the toString method in User class.
     */
    @Test
    public void testToString() {
        String expectedString = "User{id='1', username='username', password='password', firstName='firstName', lastName='lastName'}";
        assertEquals(expectedString, user.toString());
    }

    /**
     * Test to verify the equals and hashCode methods in User class.
     */
    @Test
    public void testEqualsAndHashCode() {
        User sameUser = new User(1, "username", "password", "firstName", "lastName");
        assertTrue(user.equals(sameUser) && sameUser.equals(user));
        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    /**
     * Test to verify the equals method returns false when comparing to an instance of a different class.
     */
    @Test
    public void testNotEqualsToDifferentClass() {
        assertFalse(user.equals("A String"));
    }

    /**
     * Test to verify the equals method returns false when comparing to null.
     */
    @Test
    public void testNotEqualsToNull() {
        assertFalse(user.equals(null));
    }
}
