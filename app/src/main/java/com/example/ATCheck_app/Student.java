package com.example.ATCheck_app;

import java.util.UUID;

public class Student {
    private String id;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private int involvement;
    private int behavior;
    private int social;


    public Student() {
        // required no-argument constructor
    }
    public Student(String firstName, String lastName, String birthDate, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID().toString(); // generate a unique ID for each student

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber= phoneNumber;
    }



    public int getInvolvement() {
        return involvement;
    }

    public void setInvolvement(int involvement) {
        this.involvement = involvement;
    }

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }
}
