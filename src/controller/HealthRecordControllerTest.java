package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.HealthRecord;

public class HealthRecordControllerTest {

    private HealthRecordController controller;
    private HealthRecord record1;
    private HealthRecord record2;

    @Before
    public void setUp() {
        controller = new HealthRecordController();
        record1 = new HealthRecord(70.0f, 36.5f, "120/80", "Patient is healthy", LocalDate.now());
        record2 = new HealthRecord(75.0f, 37.0f, "130/90", "Patient has a slight fever", LocalDate.now().minusDays(1));
    }

    @Test
    public void testAddHealthRecord() {
        controller.addHealthRecord(record1);
        controller.addHealthRecord(record2);
        assertEquals(2, controller.getHealthRecords().size());
    }

    @Test
    public void testDeleteHealthRecord() {
        controller.addHealthRecord(record1);
        controller.addHealthRecord(record2);
        controller.deleteHealthRecord(record1);
        assertEquals(1, controller.getHealthRecords().size());
    }

    @Test
    public void testGetHealthRecords() {
        controller.addHealthRecord(record1);
        controller.addHealthRecord(record2);
        assertNotNull(controller.getHealthRecords());
        assertEquals(2, controller.getHealthRecords().size());
    }

    @Test
    public void testSearchRecord() {
        controller.addHealthRecord(record1);
        controller.addHealthRecord(record2);
        HealthRecord foundRecord = controller.searchRecord(LocalDate.now());
        assertNotNull(foundRecord);
        assertEquals(record1.getTemperature(), foundRecord.getTemperature(), 0.001f);
    }

    @Test
    public void testSearchRecordNotFound() {
        controller.addHealthRecord(record1);
        controller.addHealthRecord(record2);
        HealthRecord foundRecord = controller.searchRecord(LocalDate.now().minusDays(2));
        assertNull(foundRecord);
    }
}
