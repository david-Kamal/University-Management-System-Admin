package com.example.modernacademystudentaffairsadmin.Custom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.modernacademystudentaffairsadmin.Modules.CommonModule;
import com.example.modernacademystudentaffairsadmin.Modules.Data.AllStudentsData;
import com.example.modernacademystudentaffairsadmin.Modules.Data.StudentSubjectData;

import java.util.ArrayList;

public class StudentCustomAdapter extends ArrayAdapter<AllStudentsData>
{

    private Context context;
    private ArrayList<AllStudentsData> studentsData = new ArrayList<AllStudentsData>();
    private ArrayList<StudentSubjectData> studentSubjectData = new ArrayList<StudentSubjectData>();

    public StudentCustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StudentSubjectData> studentSubjectData)
    {
        super(context, resource);
        this.context = context;
        this.studentSubjectData = studentSubjectData;
        for (AllStudentsData data: CommonModule.getAllStudentsDataArrayList())
        {
            for (StudentSubjectData subjectData : studentSubjectData)
            {
                System.out.println("subjectData   " + subjectData.getStuedntid());
                System.out.println("data    " + subjectData.getStuedntid());
                if (data.getId() == subjectData.getStuedntid())
                {
                    studentsData.add(data);
                }
            }
        }
    }

    @Override
    public int getCount(){
        return studentsData.size();
    }

    @Override
    public AllStudentsData getItem(int position){
        return studentsData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return studentsData.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // create a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(String.valueOf(studentsData.get(position).getFullname()));
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(String.valueOf(studentsData.get(position).getFullname()));
        return label;
    }
}
