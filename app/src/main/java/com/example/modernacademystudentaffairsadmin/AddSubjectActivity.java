package com.example.modernacademystudentaffairsadmin;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Custom.SubjectsCustomAdapter;
import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AddSubjectReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubjectActivity extends AppCompatActivity{

    private AlertDialog.Builder dialog;
    private Spinner prequisitSpinner;
    private long subjectId;
    private int subjectHours;
    private EditText et_subject_name, et_subject_date, et_subject_date2, et_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        setTitle("Add Subject");

        prequisitSpinner = (Spinner) findViewById(R.id.prequisitSpinner);
        et_subject_name = findViewById(R.id.et_subject_name);
        et_subject_date = findViewById(R.id.et_subject_date);
        et_subject_date2 = findViewById(R.id.et_subject_date2);
        et_hours = findViewById(R.id.et_hours);

        SubjectsCustomAdapter adapter = new SubjectsCustomAdapter(this, android.R.layout.simple_spinner_dropdown_item, CommonModule.getSubjectsDataArrayList());
        prequisitSpinner.setAdapter(adapter);
        prequisitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectId = parent.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void addSubject(View view)
    {
        String subjectName = et_subject_name.getText().toString().trim();
        String subjectDate = et_subject_date.getText().toString().trim();
        String subjectDate2 = et_subject_date2.getText().toString().trim();
        String hours = et_hours.getText().toString().trim();

        if (subjectName.isEmpty())
        {
            et_subject_name.setError("Required!");
        }else if (subjectDate.isEmpty())
        {
            et_subject_date.setError("Required!");
        }else if (subjectDate2.isEmpty())
        {
            et_subject_date2.setError("Required!");
        }else if (hours.isEmpty() || hours.equals("0"))
        {
            et_hours.setError("Enter a valid value!!");
        }else
        {
            try
            {
                subjectHours = Integer.parseInt(hours) ;
            }catch (Exception e)
            {
                et_hours.setError("Enter Numbers only!");
            }

            AddSubjectReq addSubjectReq = new AddSubjectReq(subjectName,subjectDate, subjectDate2, subjectHours);
            Call<Void> call = MainActivity.client.addsubject(addSubjectReq);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (response.isSuccessful())
                    {
                        clearTextFields();
                        Toast.makeText(AddSubjectActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        showAlert(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    showAlert(t.getMessage());
                }
            });
        }

    }

    private void clearTextFields()
    {
        et_subject_name.setText("");
        et_subject_date.setText("");
        et_subject_date2.setText("");
        et_hours.setText("");
    }

    private void showAlert(String msg)
    {
        dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setTitle("Error");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
