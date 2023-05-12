package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.HealthRecord;
import model.User;

/**
 * This class manages the database operations of the application. 
 * It is responsible for creating the database connection, creating tables, and executing CRUD operations.
 */
public class Database {

    private Connection connection;

    /**
     * The constructor for the Database class. It initializes the SQLite database connection 
     * and creates the necessary tables if they do not already exist.
     * 
     * The database file is named 'myhealthtracker.db'.
     *
     * It handles any SQLException that might occur during this process by printing the stack trace.
     */
    public Database() {
        try {
            // Connect to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:myhealthtracker.db");

            // Create tables if they do not exist
            createTablesIfNotExist();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the user and health_records tables in the database if they do not already exist.
     * @throws SQLException if an SQL error occurs
     */
    private void createTablesIfNotExist() throws SQLException {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY," +
                "firstName TEXT," +
                "lastName TEXT," +
                "username TEXT," +
                "password TEXT" +
                ")";

        String createRecordTable = "CREATE TABLE IF NOT EXISTS health_records (" +
                "id INTEGER PRIMARY KEY," +
                "user_id INTEGER," +  // Note: 'userId' -> 'user_id'
                "weight REAL," +
                "temperature REAL," +
                "bloodPressure TEXT," +
                "note TEXT," +
                "date TEXT" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createRecordTable);
        }
    }

    /**
     * Returns the database connection.
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Inserts the specified user into the users table.
     * @param user the user to add
     * @throws SQLException if an SQL error occurs
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (firstName, lastName, username, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
        }
    }

    /**
     * Returns the user with the specified ID from the users table.
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or null if no such user exists
     * @throws SQLException if an SQL error occurs
     */
    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), 
                                rs.getString("password"), rs.getString("firstName"), 
                                rs.getString("lastName"));
            } else {
                return null;
            }
        }
    }

    /**
     * Updates the specified user in the users table.
     * @param user the user to update
     * @throws SQLException if an SQL error occurs
     */
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, username = ?, password = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Deletes the user with the specified ID from the users table.
     * @param id the ID of the user to delete
     * @throws SQLException if an SQL error occurs
     */
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * Inserts the specified health record into the health_records table.
     * @param record the health record to add
     * @throws SQLException if an SQL error occurs
     */
    public void addHealthRecord(HealthRecord record) throws SQLException {
        String sql = "INSERT INTO health_records(user_id, weight, temperature, bloodPressure, note, date) VALUES(?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, record.getUserId());
            pstmt.setFloat(2, record.getWeight());
            pstmt.setFloat(3, record.getTemperature());
            pstmt.setString(4, record.getBloodPressure());
            pstmt.setString(5, record.getNote());
            pstmt.setDate(6, Date.valueOf(record.getDate()));
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Returns the health record with the specified ID from the health_records table.
     * @param id the ID of the health record to retrieve
     * @return the health record with the specified ID, or null if no such record exists
     * @throws SQLException if an SQL error occurs
     */
    public HealthRecord getHealthRecord(int id) throws SQLException {
        String sql = "SELECT * FROM health_records WHERE id = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return new HealthRecord(
                    rs.getInt("id"),  // Add this line if you have an 'id' field in your HealthRecord class
                    rs.getFloat("weight"),
                    rs.getFloat("temperature"),
                    rs.getString("blood_pressure"),  // Note: 'bloodPressure' -> 'blood_pressure'
                    rs.getString("note"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("user_id")  // Note: 'userId' -> 'user_id'
                );
            }
        }
    
        return null;  // Return null if no health record found for the given id
    }
    
    /**
     * Updates the specified health record in the health_records table.
     * @param record the health record to update
     * @throws SQLException if an SQL error occurs
     */
    public void updateHealthRecord(HealthRecord record) throws SQLException {
        String sql = "UPDATE health_records SET weight = ?, temperature = ?, bloodPressure = ?, note = ?, date = ?, user_id = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, record.getWeight());
            pstmt.setFloat(2, record.getTemperature());
            pstmt.setString(3, record.getBloodPressure());
            pstmt.setString(4, record.getNote());
            pstmt.setDate(5, Date.valueOf(record.getDate()));
            pstmt.setInt(6, record.getUserId());
            pstmt.setInt(7, record.getId());  // Assume you have getId() in your HealthRecord class
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Deletes the health record with the specified ID from the health_records table.
     * @param id the ID of the health record to delete
     * @throws SQLException if an SQL error occurs
     */
    public void deleteHealthRecord(int id) throws SQLException {
        String sql = "DELETE FROM health_records WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Retrieves all health records for a given user from the health_records table.
     * @param userId the ID of the user whose health records to retrieve
     * @return a list of health records for the specified user
     * @throws SQLException if an SQL error occurs
     */
    public List<HealthRecord> getAllHealthRecords(int userId) throws SQLException {
        List<HealthRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM health_records WHERE user_id = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                long timestamp = rs.getLong("date");
                java.sql.Date date = new java.sql.Date(timestamp);
                LocalDate localDate = date.toLocalDate();


                
                records.add(new HealthRecord(
                    rs.getInt("id"),
                    rs.getFloat("weight"),
                    rs.getFloat("temperature"),
                    rs.getString("bloodPressure"),
                    rs.getString("note"),
                    localDate,
                    rs.getInt("user_id")
                ));
            }
        }
    
        return records;
    }
    
    /**
     * Retrieves all users from the users table.
     * @return a list of all users
     * @throws SQLException if an SQL error occurs
     */
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), 
                                     rs.getString("password"), rs.getString("firstName"), 
                                     rs.getString("lastName"));
                users.add(user);
            }
        }

        return users;
    }
}
