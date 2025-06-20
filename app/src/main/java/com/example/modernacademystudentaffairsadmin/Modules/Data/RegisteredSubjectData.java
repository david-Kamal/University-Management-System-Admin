package com.example.modernacademystudentaffairsadmin.Modules.Data;

public class RegisteredSubjectData
{
    private int id;
    private String subjectname;


    public RegisteredSubjectData(int id, String subjectname) {
        this.id = id;
        this.subjectname = subjectname;
    }

    public int getId() {
        return id;
    }

    public String getSubjectname() {
        return subjectname;
    }
}
