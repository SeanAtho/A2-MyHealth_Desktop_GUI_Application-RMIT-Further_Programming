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

public class Database {

    private Connection connection;

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

    public Connection getConnection() {
        return connection;
    }

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

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }


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
    
    public void updateHealthRecord(HealthRecord record) throws SQLException {
        String sql = "UPDATE health_records SET weight = ?, temperature = ?, bloodPressure = ?, note = ?, date = ?, userId = ? WHERE id = ?";

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
    
    public void deleteHealthRecord(int id) throws SQLException {
        String sql = "DELETE FROM health_records WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
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
