package test;

import model.HealthRecord;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Unit test class for the HealthRecord model.
 */
public class HealthRecordTest {
    // HealthRecord instance that will be tested
    private HealthRecord healthRecord;

    // Test date to be used in test cases
    private final LocalDate testDate = LocalDate.of(2023, 5, 14);

    /**
     * This method is executed before each test. It's used to set up the testing environment.
     */
    @Before
    public void setUp() {
        // Initialize a new HealthRecord instance before each test
        healthRecord = new HealthRecord(1, 75.0f, 37.0f, "120/80", "Healthy", testDate, 1);
    }

    /**
     * Test to verify that the HealthRecord constructor and getter methods work correctly.
     */
    @Test
    public void testConstructorAndGetterMethods() {
        // Assert that each getter method returns the correct value that was set in the constructor
        assertEquals(1, healthRecord.getId());
        assertEquals(75.0f, healthRecord.getWeight(), 0.01);
        assertEquals(37.0f, healthRecord.getTemperature(), 0.01);
        assertEquals("120/80", healthRecord.getBloodPressure());
        assertEquals("Healthy", healthRecord.getNote());
        assertEquals(testDate, healthRecord.getDate());
        assertEquals(1, healthRecord.getUserId());
    }

    /**
     * Test to verify that the setter methods in HealthRecord work correctly.
     */
    @Test
    public void testSetterMethods() {
        // Call each setter method to update the value of each attribute in the HealthRecord instance
        healthRecord.setId(2);
        healthRecord.setWeight(80.0f);
        healthRecord.setTemperature(36.5f);
        healthRecord.setBloodPressure("130/90");
        healthRecord.setNote("Feeling unwell");
        LocalDate newDate = LocalDate.of(2023, 5, 15);
        healthRecord.setDate(newDate);
        healthRecord.setUserId(2);

        // Assert that each getter method now returns the new value that was set by the setter methods
        assertEquals(2, healthRecord.getId());
        assertEquals(80.0f, healthRecord.getWeight(), 0.01);
        assertEquals(36.5f, healthRecord.getTemperature(), 0.01);
        assertEquals("130/90", healthRecord.getBloodPressure());
        assertEquals("Feeling unwell", healthRecord.getNote());
        assertEquals(newDate, healthRecord.getDate());
        assertEquals(2, healthRecord.getUserId());
    }
}
