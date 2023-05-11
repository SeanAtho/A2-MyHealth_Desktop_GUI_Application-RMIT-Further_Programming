package controller;

import java.sql.SQLException;
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

    public void addHealthRecord(User user, HealthRecord record) {
        user.addHealthRecord(record);
        healthRecords.add(record);
        try {
            database.addHealthRecord(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHealthRecord(HealthRecord record) {
        healthRecords.remove(record);
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
        }
        return new ArrayList<>();
    }
    

    /**
     * Searches for a health record with the given date.
     *
     * @param date the date to search for
     * @return the health record with the given date, or null if not found
     */
    public HealthRecord searchRecord(LocalDate date) {
        return database.getRecordByDate(date);
    }


    
}
    
