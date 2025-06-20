package com.example.modernacademystudentaffairsadmin.Modules.Data;

public class AddSubjectReq
{
    private String subjectname,date, date2;
    private int hours;

    public AddSubjectReq(String subjectname, String date, String date2, int hours) {
        this.subjectname = subjectname;
        this.date = date;
        this.date2 = date2;
        this.hours = hours;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getDate() {
        return date;
    }

    public String getDate2() {
        return date2;
    }

    public int getHours() {
        return hours;
    }
}
