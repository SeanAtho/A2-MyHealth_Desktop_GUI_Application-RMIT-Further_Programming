package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.HealthRecord;

public class HealthRecordController {
    private List<HealthRecord> healthRecords;

    public HealthRecordController() {
        this.healthRecords = new ArrayList<>();
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

}
    
