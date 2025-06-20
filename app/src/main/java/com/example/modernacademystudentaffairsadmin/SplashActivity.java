package com.example.modernacademystudentaffairsadmin;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.IRetrofitResponseListener;
import com.example.modernacademystudentaffairsadmin.Modules.SessionManager;

public class SplashActivity extends AppCompatActivity {


    CommonModule commonModule;

    AlertDialog.Builder alertDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //CreatePhotoDirectory();

        sessionManager = new SessionManager(SplashActivity.this);
        commonModule = new CommonModule();


        if(isWorkingInternetPersent()){
            splash();
        }
        else{
            runTask();
        }

    }
    //***by create file f el DCIM 3shan law 3aiz t save feh ay 7aga***

//    private void CreatePhotoDirectory()
//    {
//
////        File file = new File(Environment.getExternalStoragePublicDirectory(
////                Environment.DIRECTORY_PICTURES), "Mohassil");
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                //Permission is granted
//                file.mkdir();
//            }else
//            {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
//                Toast.makeText(this, "يجب منح اذن الوصول للمتابعة", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public void splash()
    {

        checkIfLoggedIn();
    }

    public boolean isWorkingInternetPersent()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public void runTask ()
    {
        if(isWorkingInternetPersent())
        {
            splash();
        } else {
            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Internet Connection");
            alertDialog.setMessage("Sorry there was an error getting data from the Internet.\nNetwork Unavailable!");

            alertDialog.setPositiveButton("Retry", (dialog, which) -> {
                dialog.dismiss();
                runTask();
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    private void checkIfLoggedIn()
    {
        if (sessionManager.isLoggedIn())
        {
            System.out.println("logged in");

            commonModule.getSubjectsData(new IRetrofitResponseListener() {
                @Override
                public void onSuccess()
                {
                    try {
                        commonModule.getAllDoctorData();
                        commonModule.returnstudents();
                       // law 3erf y connect 3la el server 3ady w gab data hykml y call ba2y el ws
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }catch (Exception ex)
                    {
                        showAlertDialog(ex.getMessage());
                    }
                }

                @Override
                public void onFailure()
                {
                    alertDialog = new AlertDialog.Builder(SplashActivity.this);
                    alertDialog.setMessage("خطأ في الاتصال");

                    alertDialog.setPositiveButton("حاول مرة أخرى", (dialog, which) -> {
                        dialog.dismiss();
                        checkIfLoggedIn();
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                }
            });

        }else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    //fullscreen setup
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showAlertDialog(String message)
    {
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        commonModule = new CommonModule();
//
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },4000);
//
//
//
//
//    }
