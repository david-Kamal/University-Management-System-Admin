package com.example.modernacademystudentaffairsadmin;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Custom.RegisteredSubjectDataAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.Data.RegisteredSubjectData;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddResult;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.ReturnRegisteredSubjectsReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentResultActivity extends AppCompatActivity {

    Spinner spinner_subjects;
    EditText resultGrade;
    long subjectId;
    String grade,studentId;
    ArrayList<RegisteredSubjectData> registeredSubjectData;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);

        resultGrade = findViewById(R.id.result_grade);
        spinner_subjects = findViewById(R.id.spinner_subjects);

        grade = resultGrade.getText().toString().trim();

        Bundle extras = getIntent().getExtras();
        if(extras == null)
        {
            System.out.println("onCreate:  extras is null");
            studentId= null;
        } else {
            studentId= extras.getString("studentId");
            System.out.println("onCreate:" + studentId);
        }

        try
        {
            getRegisteredSubjects();
        }catch (Exception ex)
        {
            Log.e("getRegisteredSubjects", ex.getMessage());
        }

    }

    public void addReult(View view)
    {
        grade = resultGrade.getText().toString().trim();
        try
        {
            int studentsId = Integer.parseInt(studentId);
            AddResult addResult = new AddResult(studentsId,grade,subjectId);

            Call<Void> call = MainActivity.client.addresult(addResult);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if(response.isSuccessful())
                    {
                        clearTextField();
                        Toast.makeText(StudentResultActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                }

                private void clearTextField()
                {
                    resultGrade.setText("");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(StudentResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }
        catch(Exception e){
            Log.i("","Error");
        }


    }
    public void getRegisteredSubjects()
    {
        try {
            int studentsId = Integer.parseInt(studentId);

            ReturnRegisteredSubjectsReq returnRegisteredSubjectsReq = new ReturnRegisteredSubjectsReq(studentsId);
            Call<ArrayList<RegisteredSubjectData>> call = MainActivity.client.returnregisteredsubjects(returnRegisteredSubjectsReq);

            call.enqueue(new Callback<ArrayList<RegisteredSubjectData>>() {
                @Override
                public void onResponse(Call<ArrayList<RegisteredSubjectData>> call, Response<ArrayList<RegisteredSubjectData>> response) {
                    Toast.makeText(StudentResultActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    if(response.isSuccessful()) {
                        registeredSubjectData = response.body();

                        if (registeredSubjectData.size() != 0) {
                            RegisteredSubjectDataAdapter subjectsAdapter = new RegisteredSubjectDataAdapter(StudentResultActivity.this, android.R.layout.simple_spinner_dropdown_item, registeredSubjectData);
                            spinner_subjects.setAdapter(subjectsAdapter);
                            spinner_subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    subjectId = parent.getSelectedItemId();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }else
                        {
                            TextView error = (TextView)spinner_subjects.getSelectedView();
                            error.setText("لا يوجد مواد مسجلة لهذا الطالب");
                            error.setTextColor(Color.RED);
                        }
                    }else

                    {
                        showAlert(response.message());
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<RegisteredSubjectData>> call, Throwable t) {
                    showAlert(t.getMessage());
                }
            });



        }
        catch (Exception e ){
            Toast.makeText(StudentResultActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }
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
