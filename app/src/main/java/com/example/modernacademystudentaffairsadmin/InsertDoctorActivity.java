package com.example.modernacademystudentaffairsadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddDocReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.modernacademystudentaffairsadmin.MainActivity.client;


public class InsertDoctorActivity extends AppCompatActivity {
private EditText insertdoctorname, insertdoctordegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor);
        insertdoctorname = (EditText) findViewById(R.id.insertdocname);
        insertdoctordegree= (EditText)findViewById(R.id.insertdocdegree);

        setTitle("Doctor");
    }

    public void insertDoctor(View view)
    {
        String doctorname = insertdoctorname.getText().toString().trim();
        String doctordegree = insertdoctordegree.getText().toString().trim();

        try {

            if (doctorname.isEmpty()) {
                insertdoctorname.setError("Required!");
            } else if (doctordegree.isEmpty()) {
                insertdoctordegree.setError("Required!");
            } else {

                AddDocReq addDocReq = new AddDocReq(doctorname, doctordegree);   // mesh enta btmla behum da aslan
                Call<Void> call = client.adddoc(addDocReq);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            clearTextFields();
                            Toast.makeText(InsertDoctorActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InsertDoctorActivity.this, "Faillllld!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("AddDocError!!", t.getMessage());

                    }
                });
            }
        }
        catch (Exception e )
        {
            Toast.makeText(this, "Error, All fields are required!", Toast.LENGTH_SHORT).show();

        }



    }

    private void clearTextFields() {
        insertdoctorname.setText("");
        insertdoctordegree.setText("");
    }
}
