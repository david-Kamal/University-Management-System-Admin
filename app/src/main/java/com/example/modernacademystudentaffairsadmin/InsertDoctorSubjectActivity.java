package com.example.modernacademystudentaffairsadmin;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Custom.DoctorsCustomAdapter;
import com.example.modernacademystudentaffairsadmin.Custom.SubjectsCustomAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddDoctorSubjectReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.modernacademystudentaffairsadmin.MainActivity.client;

public class InsertDoctorSubjectActivity extends AppCompatActivity {

    private Spinner subjectSpinner, doctorSpinner;
    private long subjectId;
    private long DoctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor_subject);

        setTitle("Insert Doctor's Subject");


        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        doctorSpinner = (Spinner) findViewById(R.id.doctorSpinner);


        SubjectsCustomAdapter adapter = new SubjectsCustomAdapter(this, android.R.layout.simple_spinner_dropdown_item, CommonModule.getSubjectsDataArrayList());
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DoctorsCustomAdapter doctorsAdapter = new DoctorsCustomAdapter(this, android.R.layout.simple_spinner_dropdown_item, CommonModule.getAllDoctorsDataArrayList());
        doctorSpinner.setAdapter(doctorsAdapter);
        doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DoctorId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(this, R.array.subject_array, android.R.layout.simple_spinner_item);
//        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        subjectSpinner.setAdapter(subjectAdapter);
//
//        subjectSpinner.setOnItemSelectedListener(this);



    }

    public void Insertdoctorsubject(View view)
    {
        AddDoctorSubjectReq addDoctorSubjectReq = new AddDoctorSubjectReq(DoctorId,subjectId);
        Call<Void> call = client.adddoctorsubject(addDoctorSubjectReq);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful())
                {
                    Toast.makeText(InsertDoctorSubjectActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
