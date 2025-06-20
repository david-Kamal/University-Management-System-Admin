package com.example.modernacademystudentaffairsadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.IRetrofitResponseListener;
import com.example.modernacademystudentaffairsadmin.Modules.Requests.LoginReq;
import com.example.modernacademystudentaffairsadmin.Modules.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.modernacademystudentaffairsadmin.MainActivity.client;

public class LoginActivity extends AppCompatActivity {

//    private Button loginBtn;
    private View parent_view;
    private EditText mgetUserName,mgetPassword;
    private SessionManager sessionManager;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parent_view = findViewById(android.R.id.content);
//        loginBtn = (Button) findViewById(R.id.loginbtn);
        sessionManager = new SessionManager(this);
        mgetUserName = (EditText) findViewById(R.id.getUserName);
        mgetPassword = (EditText) findViewById(R.id.getPassword);


//        fab_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//
//
//                attemptLogin();
//
//
//
//                if (attemptLogin()) {
//                    openMainActivity();
//                    Toast.makeText(LoginActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//                    finish();
//
//
//                } else {
//                    Snackbar.make(parent_view, "ERROR", Snackbar.LENGTH_SHORT).show();
//
//                }
//
//            }
//        });
    }


    public  void AttemptLogin (View view)
    {
        String username = mgetUserName.getText().toString().trim();
        String password = mgetPassword.getText().toString().trim();

        login(username, password);


    }

    private void login(String username, String password)
    {
        LoginReq loginReq = new LoginReq(username, password);
        Call<Void> call = client.loginreq(loginReq);
        try {
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    sessionManager.setLogin(true);
                    sessionManager.setUsername(username);
                    sessionManager.setPassword(password);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    getDataFromWs();
                    openMainActivity();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    sessionManager.setLogin(false);
                    sessionManager.setUsername("");
                    sessionManager.setPassword("");
                    showAlert(t.getMessage());
                }
            });
        }catch (Exception e)
        {
            showAlert(e.getMessage());
        }
    }


    public void openMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
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

    private void getDataFromWs()
    {
        CommonModule commonModule = new CommonModule();

        commonModule.getSubjectsData(new IRetrofitResponseListener() {
            @Override
            public void onSuccess()
            {
                try {
                    commonModule.getAllDoctorData();
                    commonModule.returnstudents();
                    // law 3erf y connect 3la el server 3ady w gab data hykml y call ba2y el ws
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }catch (Exception ex)
                {
                    showAlert(ex.getMessage());
                }
            }

            @Override
            public void onFailure()
            {
                AlertDialog.Builder alertDialog;

                alertDialog = new AlertDialog.Builder(LoginActivity.this);
                alertDialog.setMessage("خطأ في الاتصال");

                alertDialog.setPositiveButton("حاول مرة أخرى", (dialog, which) -> {
                    getDataFromWs();
                    dialog.dismiss();
                });
                AlertDialog dialog = alertDialog.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }
        });
    }


//    private boolean isPasswordValid(String password) {
//        return password.length() >= 6;
//    }
}
