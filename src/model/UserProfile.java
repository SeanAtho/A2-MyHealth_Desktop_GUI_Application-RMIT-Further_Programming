package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a user's profile, which contains personal information and health records.
 */
public class UserProfile {
    private String personalInfo; // The user's personal information.
    private List<HealthRecord> healthRecords; // The health records associated with the user.

    /**
     * Creates a UserProfile instance with the provided personal information.
     * Initializes an empty list of HealthRecord instances.
     *
     * @param personalInfo the user's personal information.
     */
    public UserProfile(String personalInfo) {
        this.personalInfo = personalInfo;
        healthRecords = new ArrayList<>();
    }

    /**
     * Sets the user's personal information.
     *
     * @param personalInfo the new personal information for the user.
     */
    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    /**
     * Adds a new HealthRecord to the user's profile.
     *
     * @param record the HealthRecord instance to add.
     */
    public void addHealthRecord(HealthRecord record) {
        healthRecords.add(record);
    }

    /**
     * Removes a specified HealthRecord from the user's profile.
     *
     * @param record the HealthRecord instance to remove.
     */
    public void deleteHealthRecord(HealthRecord record) {
        healthRecords.remove(record);
    }

    /**
     * Returns a list of all the user's HealthRecords.
     *
     * @return the list of HealthRecord instances.
     */
    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }


    /**
     * Returns the user's personal information.
     *
     * @return the personal information of the user.
     */
    public String getPersonalInfo() {
        return personalInfo;
    }
}

