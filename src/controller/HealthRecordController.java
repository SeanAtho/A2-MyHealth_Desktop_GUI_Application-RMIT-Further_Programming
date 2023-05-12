package controller;

import java.sql.SQLException;
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

    public void updateHealthRecord(HealthRecord record) {
        try {
            database.updateHealthRecord(record);
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

}
