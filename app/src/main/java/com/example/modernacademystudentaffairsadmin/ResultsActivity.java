package com.example.modernacademystudentaffairsadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.modernacademystudentaffairsadmin.Custom.StudentRecyclerAdapter;

public class ResultsActivity extends AppCompatActivity {

    private Spinner spinner_year;
    private String studentYear;
    private RecyclerView recycler_students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");

        // TODO: 6/7/2019 el year mara 4 w mara الفرقة الرابعة

        spinner_year = findViewById(R.id.spinner_year);
        recycler_students = findViewById(R.id.recycler_students);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_students.setLayoutManager(layoutManager);
        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.setAdapter(subjectAdapter);
        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentYear = String.valueOf(parent.getSelectedItemId()+1);
                StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter(ResultsActivity.this, studentYear);
                recycler_students.setAdapter(studentRecyclerAdapter);
                recycler_students.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
