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
import com.example.modernacademystudentaffairsadmin.Modules.Data.SubjectsData;
import com.example.modernacademystudentaffairsadmin.R;
import com.example.modernacademystudentaffairsadmin.SubjectAttendanceActivity;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder>
{

    TextView tv_subject_name,tv_subject_id;
    Context context;
    String year;
    List<SubjectsData> data;

    public SubjectsAdapter (Context context)
    {
        this.context = context;
        this.data = CommonModule.getSubjectsDataArrayList();
    }
    @NonNull
    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_subjects, viewGroup, false);
        return new SubjectsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsAdapter.ViewHolder viewHolder, int i)
    {
        System.out.println(i);
        tv_subject_name.setText(data.get(i).getSubjectname());
        tv_subject_id.setText(String.valueOf(data.get(i).getId()));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_subject_name = itemView.findViewById(R.id.tv_subject_name);
            tv_subject_id = itemView.findViewById(R.id.tv_subject_id);


            itemView.setOnClickListener(v -> {
                Intent in = new Intent(context, SubjectAttendanceActivity.class);
                in.putExtra("subjectIdString", String.valueOf(data.get(getLayoutPosition()).getId()));
                context.startActivity(in);
            });

        }
    }
}
