package com.example.modernacademystudentaffairsadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.modernacademystudentaffairsadmin.Custom.SubjectsAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;

public class AttendanceActivity extends AppCompatActivity
{

    RecyclerView recycler_subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        setTitle("Attendance");

        recycler_subjects = findViewById(R.id.recycler_subjects);
        recycler_subjects.setLayoutManager(new LinearLayoutManager(this));
        if(CommonModule.getSubjectsDataArrayList().size() != 0)
        {
            SubjectsAdapter adapter = new SubjectsAdapter(AttendanceActivity.this);
            recycler_subjects.setAdapter(adapter);
        }

    }
}
