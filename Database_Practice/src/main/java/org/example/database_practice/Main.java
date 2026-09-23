package org.example.database_practice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        Database.initializeDatabase();

        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Import from JSON");
            System.out.println("6. Search by Roll");
            System.out.println("7. Search by Name");
            System.out.println("8. Add Enrollment");
            System.out.println("9. Exit");
            System.out.println("Enter Choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine();
                System.out.print("Roll: ");
                int roll = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                dao.addDataList(roll, name);

            } else if (choice == 2) {
                dao.viewDataList();

            } else if (choice == 3) {
                System.out.print("Roll: ");
                int roll = sc.nextInt();
                sc.nextLine();
                System.out.print("New name: ");
                String newName = sc.nextLine();
                dao.updateDataList(roll, newName);

            } else if (choice == 4) {
                System.out.print("Roll: ");
                int roll = sc.nextInt();
                dao.deleteDataList(roll);
            } else if (choice == 5) {
                try {
                    dao.importFromJson("students.json");
                    System.out.println("Import successful.");
                } catch (IOException e) {
                    System.out.println("Could not read JSON file: " + e.getMessage());
                }
            } else if (choice == 6) {
        System.out.print("Enter Student Roll: ");
        int roll = sc.nextInt();
        dao.findByRoll(roll);

    } else if (choice == 7) {
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        dao.findByName(name);
    } else if (choice == 8) {
        System.out.print("Student Roll: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Course Code: ");
        String courseCode = sc.nextLine();
        dao.addEnrollment(studentId, courseCode);
    }
        } while(choice != 9);
    }
}
//enrollment alada ekta table. er jonno o method implement kora jay. addEnrollment() kore deya ache, bakigulo previous method gulor implement dekhe kore fela jabe
