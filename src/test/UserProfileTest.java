package test;

import model.UserProfile;
import model.HealthRecord;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileTest {
    private UserProfile profile;
    private HealthRecord record1;
    private HealthRecord record2;

    @Before
    public void setup() {
        profile = new UserProfile("John Doe");
        record1 = new HealthRecord("2023-04-10", 75, 37, "120/80", "Feeling good");
        record2 = new HealthRecord("2023-04-11", 76, 37.5, "120/80", "Feeling okay");
    }

    @Test
    public void testAddHealthRecord() {
        profile.addHealthRecord(record1);
        assertEquals(1, profile.getHealthRecords().size());
        assertTrue(profile.getHealthRecords().contains(record1));
    }

    @Test
    public void testDeleteHealthRecord() {
        profile.addHealthRecord(record1);
        profile.addHealthRecord(record2);
        profile.deleteHealthRecord(record1);
        assertEquals(1, profile.getHealthRecords().size());
        assertFalse(profile.getHealthRecords().contains(record1));
    }

    @Test
    public void testGetPersonalInfo() {
        assertEquals("John Doe", profile.getPersonalInfo());
    }

    @Test
    public void testSetPersonalInfo() {
        profile.setPersonalInfo("Jane Doe");
        assertEquals("Jane Doe", profile.getPersonalInfo());
    }
}
