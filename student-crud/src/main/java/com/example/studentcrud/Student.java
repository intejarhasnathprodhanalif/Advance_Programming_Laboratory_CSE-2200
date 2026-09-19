package com.example.studentcrud;

public class Student {
    private int roll;
    private String name;

    public Student(int roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }
}
