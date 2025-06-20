package com.example.modernacademystudentaffairsadmin.Custom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllStudentsData;
import com.example.modernacademystudentaffairsadmin.R;
import com.example.modernacademystudentaffairsadmin.StudentResultActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    TextView tv_student_name,tv_student_id;
    Context context;
    String year;
    List<AllStudentsData> data;

    public StudentRecyclerAdapter(Context context, String year)
    {
        this.context = context;
        this.year = year;
        data = new ArrayList<>();
        for (AllStudentsData d : CommonModule.getAllStudentsDataArrayList() )
        {
            if (d.getYear().equals(year))
            {
                data.add(d);
            }
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_students, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        tv_student_name.setText(data.get(i).getFullname());
        tv_student_id.setText(String.valueOf(data.get(i).getId()));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_student_name = itemView.findViewById(R.id.tv_student_name);
            tv_student_id = itemView.findViewById(R.id.tv_student_id);


            itemView.setOnClickListener(v -> {
                Intent in = new Intent(context, StudentResultActivity.class);
                System.out.println( tv_student_id.getText().toString().trim());
                in.putExtra("studentId", tv_student_id.getText().toString().trim());
                context.startActivity(in);
            });
        }
    }
}
