package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HealthRecord;
import model.User;
import database.Database;

/**
 * This class represents a controller for health records in the application.
 * It provides methods for adding, deleting, updating, and retrieving health records from the database.
 */
public class HealthRecordController {
    
    // The database object that handles data persistence for the application
    private Database database; 

    /**
     * Constructs a HealthRecordController with a given database.
     *
     * @param database the database for storing health records
     */
    public HealthRecordController(Database database) {
        // Initialize the database attribute with the provided database
        this.database = database; 
    }

    /**
     * Adds a health record for a user in the database.
     *
     * @param user the user to add the health record for
     * @param record the health record to add
     */
    public void addHealthRecord(User user, HealthRecord record) {
        try {
            // Set the user id of the health record to the id of the given user
            record.setUserId(user.getId());
            
            // Add the health record to the database
            database.addHealthRecord(record);
        } catch (SQLException e) {
            // Print the stack trace for any SQLExceptions
            e.printStackTrace();
        }
    }

    /**
     * Deletes a health record from the database.
     *
     * @param record the health record to delete
     */
    public void deleteHealthRecord(HealthRecord record) {
        try {
            // Delete the health record from the database using its id
            database.deleteHealthRecord(record.getId());
        } catch (SQLException e) {
            // Print the stack trace for any SQLExceptions
            e.printStackTrace();
        }
    }

    /**
     * Updates a health record in the database.
     *
     * @param record the health record to update
     */
    public void updateHealthRecord(HealthRecord record) {
        try {
            // Update the health record in the database
            database.updateHealthRecord(record);
        } catch (SQLException e) {
            // Print the stack trace for any SQLExceptions
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all health records for a user from the database.
     *
     * @param user the user to retrieve the health records for
     * @return a list of health records for the user
     */
    public List<HealthRecord> getHealthRecordsForUser(User user) {
        try {
            // Return all health records for the user from the database
            return database.getAllHealthRecords(user.getId());
        } catch (SQLException e) {
            // Print the stack trace for any SQLExceptions and return an empty list
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
