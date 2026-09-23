package org.example.database_practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

    // Import from JSON
    public void importFromJson(String filePath) throws SQLException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<StudentJson> students = mapper.readValue(
                new File(filePath),
                new TypeReference<List<StudentJson>>() {}
        );

        for (StudentJson s : students) {
            addDataList(s.getId(), s.getName());
        }
    }//eta lagbe json er jonno

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

    // Enroll a student in a course
    public void addEnrollment(int studentId, String courseCode) throws SQLException {
        if (!rollExists(studentId)) {
            System.out.println("No student found with roll " + studentId + ". Enrollment not added.");
            return;
        }

        String sql = "INSERT INTO enrollments(student_id, course_code) VALUES(?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setString(2, courseCode);
            ps.executeUpdate();
            System.out.println("Enrollment added.");
        }
    }

    public void findByRoll(int roll) throws SQLException {
        String sql = "SELECT roll, name FROM DataList WHERE roll = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roll);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Student Name: " + rs.getString("name") + ", Roll: " + rs.getInt("roll"));
                } else {
                    System.out.println("No student found with roll " + roll);
                }
            }
        }
    }

    public void findByName(String name) throws SQLException {
        String sql = "SELECT roll FROM DataList WHERE name = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                List<Integer> rolls = new ArrayList<>();
                while (rs.next()) {
                    rolls.add(rs.getInt("roll"));
                }

                if (rolls.isEmpty()) {
                    System.out.println("No student found with name " + name);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < rolls.size(); i++) {
                        if (i > 0) sb.append(", ");
                        sb.append(rolls.get(i));
                    }
                    System.out.println(sb.toString());
                }
            }
        }
    }

}
