package com.example.modernacademystudentaffairsadmin.Modules.Requests;

public class AddDoctorSubjectReq
{
    private long doctorid, subjectid;

    public AddDoctorSubjectReq(long doctorid, long subjectid)
    {
        this.doctorid = doctorid;
        this.subjectid = subjectid;
    }
}
