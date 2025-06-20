package com.example.modernacademystudentaffairsadmin;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Custom.ExamData;
import com.example.modernacademystudentaffairsadmin.Custom.SubjectsCustomAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddExamReq;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamsActivity extends AppCompatActivity  {

    ArrayList<ExamData> data = new ArrayList<>();
    private ExamData examdata;


    private EditText  mSubjectDate,mSubjectTime;
    private Spinner mSubjectSpinner;
    private long subjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        setTitle("Exams");

        mSubjectSpinner = (Spinner) findViewById(R.id.examsSubjectSpinner);
        mSubjectDate = (EditText) findViewById(R.id.subjectDateEditText);
        mSubjectTime = (EditText) findViewById(R.id.subjectTimeEditText);

        SubjectsCustomAdapter subjectsAdapter = new SubjectsCustomAdapter(this, android.R.layout.simple_spinner_dropdown_item, CommonModule.getSubjectsDataArrayList());
        mSubjectSpinner.setAdapter(subjectsAdapter);
        mSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    mSubjectDate.setText(current);
                    mSubjectDate.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mSubjectDate.addTextChangedListener(tw);
        // Time edittext





    }




    public void addExam(View view)
    {
        String subjectDate = mSubjectDate.getText().toString().trim();
        String subjectTime = mSubjectTime.getText().toString().trim();

        try {
            if (subjectDate.isEmpty()) {
                mSubjectDate.setError("Required!");
            } else if (subjectTime.isEmpty()) {
                mSubjectTime.setError("Required!");
            } else {


                AddExamReq addExamReq = new AddExamReq(subjectId, subjectDate, subjectTime);

                Call<Void> call = MainActivity.client.addexam(addExamReq);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            clearTextFields();
                            Toast.makeText(ExamsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExamsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("AddResult", t.getMessage());
                    }
                });
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error, All fields are required!", Toast.LENGTH_SHORT).show();

        }
    }

    private void clearTextFields() {
        mSubjectDate.setText("");
        mSubjectTime.setText("");
    }
}
