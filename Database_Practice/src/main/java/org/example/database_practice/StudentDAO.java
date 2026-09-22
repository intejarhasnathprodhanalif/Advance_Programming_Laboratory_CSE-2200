package org.example.database_practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    //create
    public void addDataList(int roll, String name) throws SQLException
    {
        if(rollExists(roll))
        {
            System.out.println("A student with this roll already exists");
            return;
        }

        String sql = "INSERT INTO DataList(roll, name) VALUES(?, ?)";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    //Read
    public void viewDataList() throws SQLException
    {
        String sql = "SELECT roll, name FROM DataList";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();)
        {
            while(rs.next())
            {
                int roll = rs.getInt("roll");
                String name = rs.getString("name");
                System.out.println(roll + " - " + name);
            }
        }
    }

    //Update
    public void updateDataList(int roll, String newName) throws SQLException
    {
        String sql = "UPDATE DataList SET name = ? WHERE roll = ?";

        try(Connection conn = Database.connect();
        PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setString(1, newName);
            ps.setInt(2, roll);
            ps.executeUpdate();
        }

    }

    //delete
    public void deleteDataList(int roll) throws SQLException {
        String sql = "DELETE FROM DataList WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No student found with roll " + roll);
            } else {
                System.out.println("Student deleted.");
            }
        }
    }

    // Helper — checks whether a roll already exists (used by Create and Update)
    public boolean rollExists(int roll) throws SQLException {
        String sql = "SELECT 1 FROM DataList WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT roll, name FROM DataList";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("roll"), rs.getString("name")));
            }
        }
        return list;
    }//GUI na dile ei method lagbe na

}
