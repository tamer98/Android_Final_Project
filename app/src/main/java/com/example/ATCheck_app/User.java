package com.example.ATCheck_app;

import java.util.ArrayList;

public class User {

    //The user of the system.
    public String name, uid;
    private ArrayList<Student> students;

    public User() {
    }

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
        this.students = new ArrayList<Student>();

    }

    //getters and setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", students=" + students +
                '}';
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
