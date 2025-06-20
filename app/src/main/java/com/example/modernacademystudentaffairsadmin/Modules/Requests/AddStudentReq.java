package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class AddStudentReq
{
    private String fullname, mail, year, username, password;
    private int age;

    public AddStudentReq(String fullname, int age, String mail, String year, String username, String password) {
        this.fullname = fullname;
        this.age = age;
        this.mail = mail;
        this.year = year;
        this.username = username;
        this.password = password;
    }
}
