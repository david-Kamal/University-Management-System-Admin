package com.example.modernacademystudentaffairsadmin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.example.modernacademystudentaffairsadmin.Custom.StudentCustomAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.Data.StudentSubjectData;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.SubjectIdReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectAttendanceActivity extends AppCompatActivity
{
    Spinner spinner_student_subject;
    ArrayList<StudentSubjectData> studentSubjectDataArrayList;
    private AlertDialog.Builder builder;
    StudentCustomAdapter studentCustomAdapter;
    private String subjectIdString;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_attendance);
        spinner_student_subject = findViewById(R.id.spinner_student_subject);
        Bundle extras = getIntent().getExtras();
        if(extras == null)
        {
            System.out.println("onCreate:  extras is null");
            subjectIdString= null;
        } else {
            subjectIdString= extras.getString("subjectIdString");
        }
        try
        {
            int subjectId = Integer.parseInt(subjectIdString);
            getSubjectStudent(subjectId);
        }catch (Exception ex)
        {
            showAlert(ex.getMessage());
        }

    }

    private void getSubjectStudent(int subjectid)
    {
        SubjectIdReq subjectIdReq = new SubjectIdReq(subjectid);

        Call<ArrayList<StudentSubjectData>> call = MainActivity.client.getSubjectStudents(subjectIdReq);

        call.enqueue(new Callback<ArrayList<StudentSubjectData>>()
        {
            @Override
            public void onResponse(Call<ArrayList<StudentSubjectData>> call, Response<ArrayList<StudentSubjectData>> response) {
                if (response.isSuccessful())
                {
                    studentSubjectDataArrayList = response.body();
                    studentCustomAdapter = new StudentCustomAdapter(SubjectAttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, studentSubjectDataArrayList);
                    spinner_student_subject.setAdapter(studentCustomAdapter);
                }else
                {
                    showAlert(response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<StudentSubjectData>> call, Throwable t)
            {
                showAlert(t.getMessage());
            }
        });

    }

    private void showAlert(String msg)
    {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("Error");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
