package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.HealthRecord;
import model.User;
import database.Database;

public class HealthRecordController {
    private Database database; // Add a Database attribute

    public HealthRecordController(Database database) {
        this.database = database; // Initialize the Database attribute
    }

    public void addHealthRecord(User user, HealthRecord record) {
        try {
            record.setUserId(user.getId()); // Set the user id of the record
            database.addHealthRecord(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteHealthRecord(HealthRecord record) {
        try {
            database.deleteHealthRecord(record.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HealthRecord> getHealthRecordsForUser(User user) {
        try {
            return database.getAllHealthRecords(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Searches for a health record with the given date for a specific user.
     *
     * @param user the user to search for
     * @param date the date to search for
     * @return the health record with the given date, or null if not found
     */
    public HealthRecord searchRecord(User user, LocalDate date) {
        try {
            return database.getRecordByDate(user.getId(), date);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
