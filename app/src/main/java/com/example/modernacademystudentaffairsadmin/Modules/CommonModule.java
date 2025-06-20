package com.example.modernacademystudentaffairsadmin.Modules;

import android.util.Log;

import com.example.modernacademystudentaffairsadmin.MainActivity;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllDoctorsData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllStudentsData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.SubjectsData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonModule
{
    private static ArrayList<SubjectsData> subjectsDataArrayList;
    private static ArrayList<AllDoctorsData> allDoctorsDataArrayList;
    private static ArrayList<AllStudentsData> allStudentsDataArrayList;

    public static ArrayList<SubjectsData> getSubjectsDataArrayList() {
        return subjectsDataArrayList;
    }

    public static ArrayList<AllDoctorsData> getAllDoctorsDataArrayList() {
        return allDoctorsDataArrayList;
    }

    public static ArrayList<AllStudentsData> getAllStudentsDataArrayList() {
        return allStudentsDataArrayList;
    }

    public void getSubjectsData(IRetrofitResponseListener iRetrofitResponseListener)
    {
        Call<ArrayList<SubjectsData>> call = MainActivity.client.returnallsubjects(" ");
        try
        {
            call.enqueue(new Callback<ArrayList<SubjectsData>>() {
                @Override
                public void onResponse(Call<ArrayList<SubjectsData>> call, Response<ArrayList<SubjectsData>> response)
                {
                    System.out.println(response.isSuccessful());
                    if (response.isSuccessful())
                    {
                        subjectsDataArrayList = new ArrayList<>(response.body());
                        iRetrofitResponseListener.onSuccess();
                    }else
                    {
                        System.out.println(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<SubjectsData>> call, Throwable t)
                {
                    Log.e("getSubjectsData", t.getMessage());
                    iRetrofitResponseListener.onFailure();
                }
            });

        }catch (Exception ex)
        {
            Log.e("getSubjectsData", ex.getMessage());
        }
    }
    public void getAllDoctorData()
    {
        Call<ArrayList<AllDoctorsData>> call = MainActivity.client.returnalldoctors(" ");
        try
        {
            call.enqueue(new Callback<ArrayList<AllDoctorsData>>() {
                @Override
                public void onResponse(Call<ArrayList<AllDoctorsData>> call, Response<ArrayList<AllDoctorsData>> response)
                {
                    System.out.println(response.isSuccessful());
                    if (response.isSuccessful())
                    {
                        allDoctorsDataArrayList = new ArrayList<>(response.body());
                    }else
                    {
                        System.out.println(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<AllDoctorsData>> call, Throwable t)
                {
                    Log.e("getAllDoctorsData", t.getMessage());
                }
            });

        }catch (Exception ex)
        {
            Log.e("getAllDoctorsData", ex.getMessage());
        }
    }
    //returnstudents
    public void returnstudents()
    {
        Call<ArrayList<AllStudentsData>> call = MainActivity.client.returnstudents(" ");
        try
        {
            call.enqueue(new Callback<ArrayList<AllStudentsData>>() {
                @Override
                public void onResponse(Call<ArrayList<AllStudentsData>> call, Response<ArrayList<AllStudentsData>> response)
                {
                    System.out.println(response.isSuccessful());
                    if (response.isSuccessful())
                    {
                        allStudentsDataArrayList = new ArrayList<>(response.body());
                    }else
                    {
                        System.out.println(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<AllStudentsData>> call, Throwable t)
                {
                    Log.e("getAllStudentsData", t.getMessage());
                }
            });

        }catch (Exception ex)
        {
            Log.e("getAllStudentsData", ex.getMessage());
        }
    }



}
