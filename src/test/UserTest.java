package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import model.HealthRecord;
import model.User;

public class UserTest {
    private User user;
    private HealthRecord healthRecord;

    @Before
    public void setUp() {
        user = new User(1, "username", "password", "firstName", "lastName");
        healthRecord = new HealthRecord(1, 70.0f, 36.5f, "120/80", "note", LocalDate.now(), 1);
    }
    

    @Test
    public void testConstructor() {
        assertEquals(1, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertTrue(user.getHealthRecords().isEmpty());
    }

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

    @Test
    public void testAddHealthRecord() {
        user.addHealthRecord(healthRecord);
        assertTrue(user.getHealthRecords().contains(healthRecord));
    }

    @Test
    public void testToString() {
        String expectedString = "User{id='1', username='username', password='password', firstName='firstName', lastName='lastName'}";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        User sameUser = new User(1, "username", "password", "firstName", "lastName");
        assertTrue(user.equals(sameUser) && sameUser.equals(user));
        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    @Test
    public void testNotEqualsToDifferentClass() {
        assertFalse(user.equals("A String"));
    }

    @Test
    public void testNotEqualsToNull() {
        assertFalse(user.equals(null));
    }
}
