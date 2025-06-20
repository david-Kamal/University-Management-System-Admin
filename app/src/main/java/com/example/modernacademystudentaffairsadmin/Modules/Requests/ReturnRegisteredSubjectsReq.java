package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class ReturnRegisteredSubjectsReq
{

    private int studentId;

    public ReturnRegisteredSubjectsReq(int studentId) {

        this.studentId = studentId;
    }


    public int getId() {
        return studentId;
    }
}
