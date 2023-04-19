package model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class UserProfileTest {
    private UserProfile userProfile;

    @Before
    public void setUp() {
        userProfile = new UserProfile("John Doe");
    }

    @Test
    public void testSetPersonalInfo() {
        userProfile.setPersonalInfo("Jane Doe");
        assertEquals("Jane Doe", userProfile.getPersonalInfo());
    }

    @Test
    public void testAddHealthRecord() {
        LocalDate today = LocalDate.now();
        HealthRecord record = new HealthRecord(70.0f, 37.0f, "120/80", "Normal", today);
        userProfile.addHealthRecord(record);
        assertEquals(1, userProfile.getHealthRecords().size());
    }

    @Test
    public void testDeleteHealthRecord() {
        LocalDate today = LocalDate.now();
        HealthRecord record = new HealthRecord(70.0f, 37.0f, "120/80", "Normal", today);
        userProfile.addHealthRecord(record);
        assertEquals(1, userProfile.getHealthRecords().size());
        userProfile.deleteHealthRecord(record);
        assertEquals(0, userProfile.getHealthRecords().size());
    }
}
