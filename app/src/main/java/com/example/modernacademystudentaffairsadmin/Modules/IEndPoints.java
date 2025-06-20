package com.example.modernacademystudentaffairsadmin.Modules;

import com.example.modernacademystudentaffairsadmin.Modules.Data.AddSubjectReq;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllDoctorsData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllStudentsData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.RegisteredSubjectData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.StudentSubjectData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.SubjectsData;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddDocReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddDoctorSubjectReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddExamReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddResult;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddStudentReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddUserReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.LoginReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.ReturnRegisteredSubjectsReq;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.SubjectIdReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IEndPoints
{
    @POST("adddoctorsubject.php")
    Call<Void> adddoctorsubject (@Body AddDoctorSubjectReq addDoctorSubjectReq);
    @POST("alwanadduser.php")
    Call<Void> alwanadduser (@Body AddStudentReq addStudentReq);
    @POST("dadduser.php")
    Call<Void> dadduser (@Body AddUserReq addUserReq);
    @POST("addexam.php")
    Call<Void> addexam (@Body AddExamReq addExamReq);
    @POST("adddoc.php")
    Call<Void> adddoc (@Body AddDocReq addDocReq);
    @POST("addresult.php")
    Call<Void> addresult (@Body AddResult addResult);

    @POST("addsubject.php")
    Call<Void> addsubject (@Body AddSubjectReq addSubjectReq);

    @POST("returnallsubjects.php")
    Call<ArrayList<SubjectsData>> returnallsubjects (@Body String s);

    @POST("returnregisteredsubjects.php")
    Call<ArrayList<RegisteredSubjectData>> returnregisteredsubjects (@Body ReturnRegisteredSubjectsReq returnRegisteredSubjectsReq);

    @POST("returnalldoctors.php")
    Call<ArrayList<AllDoctorsData>> returnalldoctors (@Body String s);

    @POST("returnstudents.php")
    Call<ArrayList<AllStudentsData>> returnstudents (@Body String s);

    @POST("anotheradminlogin.php")
    Call<Void> loginreq (@Body LoginReq loginReq);

    @POST("getSubjectStudents.php")
    Call<ArrayList<StudentSubjectData>> getSubjectStudents (@Body SubjectIdReq subjectIdReq);




}
