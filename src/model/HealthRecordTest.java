package model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

public class HealthRecordTest {

    private HealthRecord record;

    @Before
    public void setUp() {
        record = new HealthRecord(70.0f, 37.0f, "120/80", "Good", LocalDate.of(2022, 4, 10));
    }

    @Test
    public void testGetWeight() {
        assertEquals(70.0f, record.getWeight(), 0.001f);
    }

    @Test
    public void testGetTemperature() {
        assertEquals(37.0f, record.getTemperature(), 0.001f);
    }

    @Test
    public void testGetBloodPressure() {
        assertEquals("120/80", record.getBloodPressure());
    }

    @Test
    public void testGetNote() {
        assertEquals("Good", record.getNote());
    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.of(2022, 4, 10), record.getDate());
    }

    @Test
    public void testSetWeight() {
        record.setWeight(75.0f);
        assertEquals(75.0f, record.getWeight(), 0.001f);
    }

    @Test
    public void testSetTemperature() {
        record.setTemperature(38.0f);
        assertEquals(38.0f, record.getTemperature(), 0.001f);
    }

    @Test
    public void testSetBloodPressure() {
        record.setBloodPressure("130/90");
        assertEquals("130/90", record.getBloodPressure());
    }

    @Test
    public void testSetNote() {
        record.setNote("OK");
        assertEquals("OK", record.getNote());
    }

    @Test
    public void testSetDate() {
        record.setDate(LocalDate.of(2023, 4, 10));
        assertEquals(LocalDate.of(2023, 4, 10), record.getDate());
    }
}
