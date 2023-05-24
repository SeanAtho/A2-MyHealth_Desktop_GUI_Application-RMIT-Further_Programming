package test;

import model.HealthRecord;
import model.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test class for the UserProfile model.
 */
public class UserProfileTest {
    // UserProfile instance that will be tested
    private UserProfile userProfile;

    // HealthRecord instance that will be used in test cases
    private HealthRecord healthRecord;

    /**
     * This method is executed before each test. It's used to set up the testing environment.
     */
    @Before
    public void setUp() {
        // Initialize a new UserProfile instance before each test
        userProfile = new UserProfile("Personal Info");

        // Initialize a new HealthRecord instance before each test
        healthRecord = new HealthRecord(1, 70.0f, 36.5f, "120/80", "note", LocalDate.now(), 1);
    }

    /**
     * Test to verify that the addHealthRecord method in UserProfile works correctly.
     */
    @Test
    public void testAddHealthRecord() {
        // Add the HealthRecord instance to the UserProfile instance
        userProfile.addHealthRecord(healthRecord);

        // Assert that the HealthRecord instance was correctly added to the UserProfile instance
        assertTrue(userProfile.getHealthRecords().contains(healthRecord));
    }

    /**
     * Test to verify that the deleteHealthRecord method in UserProfile works correctly.
     */
    @Test
    public void testDeleteHealthRecord() {
        // Add the HealthRecord instance to the UserProfile instance
        userProfile.addHealthRecord(healthRecord);

        // Delete the HealthRecord instance from the UserProfile instance
        userProfile.deleteHealthRecord(healthRecord);

        // Assert that the HealthRecord instance was correctly deleted from the UserProfile instance
        assertTrue(!userProfile.getHealthRecords().contains(healthRecord));
    }

    /**
     * Test to verify that the setPersonalInfo method in UserProfile works correctly.
     */
    @Test
    public void testSetPersonalInfo() {
        // Create a new string for the personal information
        String newInfo = "New Personal Info";

        // Set the personal information in the UserProfile instance to the new string
        userProfile.setPersonalInfo(newInfo);

        // Assert that the personal information was correctly set in the UserProfile instance
        assertEquals(newInfo, userProfile.getPersonalInfo());
    }
}
