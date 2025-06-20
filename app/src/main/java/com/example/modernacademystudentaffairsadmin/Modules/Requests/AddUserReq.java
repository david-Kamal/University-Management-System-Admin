package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class AddUserReq
{
    private String username, password, name;

    public AddUserReq(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
