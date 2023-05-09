package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

    // Implement other methods for database operations (select, insert, update, delete)
}
