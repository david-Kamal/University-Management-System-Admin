package com.example.modernacademystudentaffairsadmin.Custom;

public class ExamData
{
//    CREATE TABLE `examschedule` (
//        `id` int(9) UNSIGNED NOT NULL,
//  `subjectid` int(9) UNSIGNED NOT NULL,
//  `date` char(255) NOT NULL,
//  `time` char(9) NOT NULL);

    private int subjectId;
    private String date, time;

    public ExamData(int subjectId, String date, String time) {
        this.subjectId = subjectId;
        this.date = date;
        this.time = time;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
