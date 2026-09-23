package org.example.database_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    static final String URL = "jdbc:sqlite:StudentData.db";

    public static Connection connect() throws SQLException
    {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    public static void initializeDatabase()
    {
        String studentsSql = "CREATE TABLE IF NOT EXISTS DataList (" +
                "roll INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL)";

        String enrollmentsSql = "CREATE TABLE IF NOT EXISTS enrollments (" +
                "student_id INTEGER, " +
                "course_code TEXT, " +
                "FOREIGN KEY (student_id) REFERENCES DataList(roll))";

        try(Connection conn = connect();
            Statement stmt = conn.createStatement())
        {
            stmt.execute(studentsSql);
            stmt.execute(enrollmentsSql);
            System.out.println("Database Ready");
        }

        catch(SQLException e)
        {
            System.out.println("Table Creation Failed: " + e.getMessage());
        }
    }
}
