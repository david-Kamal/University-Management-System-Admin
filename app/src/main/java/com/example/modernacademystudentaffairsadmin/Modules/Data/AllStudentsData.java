package com.example.modernacademystudentaffairsadmin.Modules.Data;

public class AllStudentsData
{
    private String fullname,mail,year,username,password;
    private int age,id;

    public AllStudentsData(String fullname, String mail, String year, String username, String password, int age, int id) {
        this.fullname = fullname;
        this.mail = mail;
        this.year = year;
        this.username = username;
        this.password = password;
        this.age = age;
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMail() {
        return mail;
    }

    public String getYear() {
        return year;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
