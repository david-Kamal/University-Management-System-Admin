package com.example.modernacademystudentaffairsadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Modules.Requests.AddUserReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.modernacademystudentaffairsadmin.MainActivity.client;

public class AddUserActivity extends AppCompatActivity {

    private EditText mAdminName, mAdminPass,mAdminUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Add Admin");

        mAdminName = (EditText) findViewById(R.id.adminNameEditText);
        mAdminUser = (EditText) findViewById(R.id.adminUserEditText);
        mAdminPass = (EditText) findViewById(R.id.adminPassEditText);


    }

    public void addUser(View view)
    {
        String adminName = mAdminName.getText().toString().trim();
        String adminPass = mAdminPass.getText().toString().trim();
        String adminUser = mAdminUser.getText().toString().trim();

        try {
            if (adminName.isEmpty()) {
                mAdminName.setError("Required!");
            }else if (adminPass.isEmpty()) {
                mAdminPass.setError("Required!");
            }else if (adminUser.isEmpty())
            {
                mAdminUser.setError("Required");
            }
            else {


                AddUserReq addUserReq = new AddUserReq(adminName, adminUser, adminPass);


                    Call<Void> call = client.dadduser(addUserReq);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                clearTextFields();
                                Toast.makeText(AddUserActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddUserActivity.this, "Faillllld!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("AddUserError!!", t.getMessage());
                        }
                    });

            }
        }catch(Exception e)
        {
            Toast.makeText(this, "Error, All fields are required!", Toast.LENGTH_SHORT).show();

        }
    }

    private void clearTextFields() {
        mAdminName.setText("");
        mAdminPass.setText("");
        mAdminUser.setText("");
    }

}
