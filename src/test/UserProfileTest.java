package test;

import model.HealthRecord;
import model.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserProfileTest {
    private UserProfile userProfile;
    private HealthRecord healthRecord;

    @Before
    public void setUp() {
        userProfile = new UserProfile("Personal Info");
        healthRecord = new HealthRecord(1, 70.0f, 36.5f, "120/80", "note", LocalDate.now(), 1);
    }

    @Test
    public void testAddHealthRecord() {
        userProfile.addHealthRecord(healthRecord);
        assertTrue(userProfile.getHealthRecords().contains(healthRecord));
    }

    @Test
    public void testDeleteHealthRecord() {
        userProfile.addHealthRecord(healthRecord);
        userProfile.deleteHealthRecord(healthRecord);
        assertTrue(!userProfile.getHealthRecords().contains(healthRecord));
    }

    @Test
    public void testSetPersonalInfo() {
        String newInfo = "New Personal Info";
        userProfile.setPersonalInfo(newInfo);
        assertEquals(newInfo, userProfile.getPersonalInfo());
    }
}
