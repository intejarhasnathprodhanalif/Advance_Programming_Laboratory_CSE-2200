package com.example.studentcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:students.db";

    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase()
    {
        String sql = """
                CREATE TABLE IF NOT EXISTS students ( roll INTEGER PRIMARY KEY, name TEXT NOT NULL)
                """;
        try(Connection conn = connect();
        Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
            System.out.println("Database Ready");
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
