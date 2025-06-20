package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class AddExamReq
{
    private long subjectid;
    private String date, time;

    public AddExamReq(long subjectid, String date, String time) {
        this.subjectid = subjectid;
        this.date = date;
        this.time = time;
    }
}
