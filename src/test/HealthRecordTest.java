package test;

import model.HealthRecord;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class HealthRecordTest {
    private HealthRecord healthRecord;
    private final LocalDate testDate = LocalDate.of(2023, 5, 14);

    @Before
    public void setUp() {
        healthRecord = new HealthRecord(1, 75.0f, 37.0f, "120/80", "Healthy", testDate, 1);
    }

    @Test
    public void testConstructorAndGetterMethods() {
        assertEquals(1, healthRecord.getId());
        assertEquals(75.0f, healthRecord.getWeight(), 0.01);
        assertEquals(37.0f, healthRecord.getTemperature(), 0.01);
        assertEquals("120/80", healthRecord.getBloodPressure());
        assertEquals("Healthy", healthRecord.getNote());
        assertEquals(testDate, healthRecord.getDate());
        assertEquals(1, healthRecord.getUserId());
    }

    @Test
    public void testSetterMethods() {
        healthRecord.setId(2);
        healthRecord.setWeight(80.0f);
        healthRecord.setTemperature(36.5f);
        healthRecord.setBloodPressure("130/90");
        healthRecord.setNote("Feeling unwell");
        LocalDate newDate = LocalDate.of(2023, 5, 15);
        healthRecord.setDate(newDate);
        healthRecord.setUserId(2);

        assertEquals(2, healthRecord.getId());
        assertEquals(80.0f, healthRecord.getWeight(), 0.01);
        assertEquals(36.5f, healthRecord.getTemperature(), 0.01);
        assertEquals("130/90", healthRecord.getBloodPressure());
        assertEquals("Feeling unwell", healthRecord.getNote());
        assertEquals(newDate, healthRecord.getDate());
        assertEquals(2, healthRecord.getUserId());
    }
}
