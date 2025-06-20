package com.example.modernacademystudentaffairsadmin.Modules.Data;

public class StudentSubjectData
{

    private int id, stuedntid, subjectid;
    private String date;

    public StudentSubjectData(int id, int stuedntid, int subjectid, String date) {
        this.id = id;
        this.stuedntid = stuedntid;
        this.subjectid = subjectid;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getStuedntid() {
        return stuedntid;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public String getDate() {
        return date;
    }
}
