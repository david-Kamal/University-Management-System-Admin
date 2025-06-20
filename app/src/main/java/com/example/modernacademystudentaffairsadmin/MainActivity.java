package com.example.modernacademystudentaffairsadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.modernacademystudentaffairsadmin.Modules.IEndPoints;
import com.example.modernacademystudentaffairsadmin.Modules.RetrofitClient;
import com.example.modernacademystudentaffairsadmin.Modules.SessionManager;

public class MainActivity extends AppCompatActivity {

    private  static final String BASE_URL = "http://10.0.2.2/gradproj/api/mobileapis/";
//    private  static final String BASE_URL = "ftp://admin%2540mamtest.nowology.net@ftp.niqabatalashraaf.com/";
    public static IEndPoints client = RetrofitClient.getInstance(BASE_URL).create(IEndPoints.class);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.optoin_logOut)
        {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.setLogin(false);
            sessionManager.setUsername("");
            sessionManager.setPassword("");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show();
            return true;
        }else
        {
            return false;
        }
    }

    public void StudentRegistration(View view)
    {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }


    public void TimeTable(View view)
    {
        Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
        startActivity(intent);
    }


    public void Exams(View view)
    {
        Intent intent = new Intent(MainActivity.this, ExamsActivity.class);
        startActivity(intent);
    }

    public void Attendance(View view)
    {
        Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
        startActivity(intent);
    }

    public void Doctor(View view)
    {
        Intent intent = new Intent(MainActivity.this, InsertDoctorActivity.class);
        startActivity(intent);
    }

    public void InsertSubject(View view)
    {
        Intent intent = new Intent(MainActivity.this,InsertDoctorSubjectActivity.class);
        startActivity(intent);
    }

    public void AddSubjects(View view)
    {
        Intent intent = new Intent(MainActivity.this,AddSubjectActivity.class);
        startActivity(intent);
    }

    public void Results(View view)
    {
        Intent intent = new Intent(MainActivity.this,ResultsActivity.class);
        startActivity(intent);
    }

    public void AddAdmin(View view)
    {
        Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
        startActivity(intent);
    }
}
