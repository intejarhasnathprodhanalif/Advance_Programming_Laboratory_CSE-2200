package org.example.database_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    static final String URL = "jdbc:sqlite:StudentData.db";

    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase()
    {
        String sql = "CREATE TABLE IF NOT EXISTS DataList (" +
                "roll INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL)"; // EXISTS er pore okhane table er nam dite hobe

        try(Connection conn = connect();
            Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
            System.out.println("org.example.database_practice.Database Ready");
        }

        catch(SQLException e)
        {
            System.out.println("Table Creation Failed: " + e.getMessage());
        }
    }
}
