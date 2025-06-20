package com.example.modernacademystudentaffairsadmin;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddStudentReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.modernacademystudentaffairsadmin.MainActivity.client;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mStudentName, mStudentAge,mStudentEmail,mStudentUsername,mStudentPassword;
    private Spinner mStudentYear;
    private String studentYear;
    int studentAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle("Registration");

        mStudentName = (EditText) findViewById(R.id.registrationStudentName);
        mStudentAge = (EditText) findViewById(R.id.registrationStudentAge);
        mStudentYear = (Spinner) findViewById(R.id.registrationStudentYear);
        mStudentEmail = (EditText) findViewById(R.id.registrationStudentEmail);
        mStudentUsername = (EditText) findViewById(R.id.registrationStudentUsername);
        mStudentPassword = (EditText) findViewById(R.id.registrationStudentPassword);


        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStudentYear.setAdapter(subjectAdapter);
        mStudentYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentYear = mStudentYear.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void AddStudent(View view) {
        String studentName, studentEmail, studentUsername, studentPassword = null;

        String val;

        studentName = mStudentName.getText().toString().trim();
        studentEmail = mStudentEmail.getText().toString().trim();
        studentUsername = mStudentUsername.getText().toString().trim();
        studentPassword = mStudentPassword.getText().toString().trim();
        val = mStudentAge.getText().toString();


        try {
            if (studentName.isEmpty()) {
                mStudentName.setError("Required!");
            }else if (studentEmail.isEmpty()) {
                mStudentEmail.setError("Required!");
            }else if (studentUsername.isEmpty())
            {
                mStudentUsername.setError("Required");
            }else if (studentPassword.isEmpty())
            {
                mStudentPassword.setError("Required");
            }else if (val.isEmpty())
            {
                mStudentAge.setError("Required");
                studentAge = Integer.valueOf(val);
            }else{
                AddStudentReq addStudentReq = new AddStudentReq(studentName, studentAge, studentEmail, studentYear, studentUsername, studentPassword);
                Call<Void> call = client.alwanadduser(addStudentReq);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful())
                        {
                            clearTextFields();
                            Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e( "RegistrationActivity", t.getMessage());
                    }
                });
            }
        } catch (Exception e)
        {
            Toast.makeText(this, "Error, All fields are required!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearTextFields()
    {
        mStudentName.setText("");
        mStudentAge.setText("");
        mStudentEmail.setText("");
        mStudentUsername.setText("");
        mStudentPassword.setText("");
    }

}
