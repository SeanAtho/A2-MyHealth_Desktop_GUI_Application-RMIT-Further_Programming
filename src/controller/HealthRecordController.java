package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.HealthRecord;
import model.User;
import database.Database;

public class HealthRecordController {
    private List<HealthRecord> healthRecords;
    private Database database; // Add a Database attribute

    public HealthRecordController(Database database) {
        this.healthRecords = new ArrayList<>();
        this.database = database; // Initialize the Database attribute
    }

    /**
     * Adds a health record to the list of health records.
     *
     * @param record the health record to add
     */
    public void addHealthRecord(HealthRecord record) {
        healthRecords.add(record);
    }

    /**
     * Deletes a health record from the list of health records.
     *
     * @param record the health record to delete
     */
    public void deleteHealthRecord(HealthRecord record) {
        healthRecords.remove(record);
    }

    /**
     * Returns a list of all health records.
     *
     * @return the list of all health records
     */
    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    /**
     * Searches for a health record with the given date.
     *
     * @param date the date to search for
     * @return the health record with the given date, or null if not found
     */
    public HealthRecord searchRecord(LocalDate date) {
        for (HealthRecord record : healthRecords) {
            if (record.getDate().equals(date)) {
                return record;
            }
        }
        return null;
    }

    /**
     * Gets the health records for the specified user.
     *
     * @param user the user whose health records are to be retrieved
     * @return a list of health records for the specified user
     */
    public List<HealthRecord> getHealthRecordsForUser(User user) {
        return healthRecords.stream()
                .filter(record -> record.getUserId() == user.getId())
                .collect(Collectors.toList());
    }

    
}
    
