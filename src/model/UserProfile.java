package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a user's profile, which contains personal information and health records.
 */
public class UserProfile {
    private String personalInfo;
    private List<HealthRecord> healthRecords;

    /**
     * Constructs a UserProfile object with the given personal information and an empty list of health records.
     *
     * @param personalInfo the personal information to set for the user
     */
    public UserProfile(String personalInfo) {
        this.personalInfo = personalInfo;
        healthRecords = new ArrayList<>();
    }

    /**
     * Sets the personal information for the user.
     *
     * @param personalInfo the personal information to set
     */
    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Adds a health record to the user's profile.
     *
     * @param record the health record to add
     */
    public void addHealthRecord(HealthRecord record) {
        healthRecords.add(record);
    }

    /**
     * Deletes a health record from the user's profile.
     *
     * @param record the health record to delete
     */
    public void deleteHealthRecord(HealthRecord record) {
        healthRecords.remove(record);
    }

    /**
     * Returns a list of all the health records in the user's profile.
     *
     * @return the list of health records
     */
    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }


    /**
     * Returns the personal information of the user.
     *
     * @return the personal information
     */
    public String getPersonalInfo() {
        return personalInfo;
    }
}

