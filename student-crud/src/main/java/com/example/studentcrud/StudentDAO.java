package com.example.studentcrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    //create
    public void insertStudent(Student student) throws SQLException
    {
        String sql = "INSERT INTO students(roll, name) VALUES(?, ?)";
        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, student.getRoll());
            ps.setString(2, student.getName());
            ps.executeUpdate();
        }
    }

    //read
    public List<Student> getAllStudents() throws SQLException
    {
        String sql = "SELECT roll, name FROM students ORDER BY roll";
        List<Student> students = new ArrayList<>();
        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())
        {
            while(rs.next())
            {
                students.add(new Student(
                        rs.getInt("roll"),
                        rs.getString("name")
                ));
            }
        }

        return students;
    }

    // UPDATE - oldRoll identifies the original database row
    public void updateStudent(int oldRoll, Student student) throws SQLException {
        String sql = "UPDATE students SET roll = ?, name = ? WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getRoll());
            ps.setString(2, student.getName());
            ps.setInt(3, oldRoll);
            ps.executeUpdate();
        }
    }

    // DELETE
    public void deleteStudent(int roll) throws SQLException {
        String sql = "DELETE FROM students WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.executeUpdate();
        }
    }

    public boolean rollExists(int roll) throws SQLException {
        String sql = "SELECT 1 FROM students WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
