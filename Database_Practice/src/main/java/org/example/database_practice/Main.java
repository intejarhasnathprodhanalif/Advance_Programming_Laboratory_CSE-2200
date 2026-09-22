package org.example.database_practice;

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
            System.out.println("5. Exit");
            System.out.print("Choose: ");
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
            }
        } while(choice != 5);
    }
}
