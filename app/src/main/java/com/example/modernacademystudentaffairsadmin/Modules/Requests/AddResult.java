package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class AddResult
{
//    `studentid` int(9) unsigned NOT NULL,
//  `subjectid` int(9) unsigned NOT NULL,
//  `result` char(255) NOT NULL

    private int  studentid;
    private String result;
    private long subjectid;

    public AddResult(int studentid, String result, long subjectid) {
        this.studentid = studentid;
        this.result = result;
        this.subjectid = subjectid;
    }

    public int getStudentid() {
        return studentid;
    }

    public String getResult() {
        return result;
    }

    public long getSubjectid() {
        return subjectid;
    }
}
