package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class LoginResponse
{
    private String status;
    private String message;

    public LoginResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
