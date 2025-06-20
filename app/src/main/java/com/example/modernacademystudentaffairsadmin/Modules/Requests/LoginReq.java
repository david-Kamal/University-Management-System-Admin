package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class LoginReq
{
    private String username, password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }
}
