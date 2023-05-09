package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                "userId INTEGER," +
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
