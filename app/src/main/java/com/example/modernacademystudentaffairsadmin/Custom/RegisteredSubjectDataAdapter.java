package com.example.modernacademystudentaffairsadmin.Custom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.modernacademystudentaffairsadmin.Modules.Data.RegisteredSubjectData;

import java.util.ArrayList;

public class RegisteredSubjectDataAdapter extends ArrayAdapter<RegisteredSubjectData>
{

    private Context context;
    private ArrayList<RegisteredSubjectData> values;

    public RegisteredSubjectDataAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RegisteredSubjectData> values)
    {
        super(context, resource);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public RegisteredSubjectData getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return values.get(position).getId();
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
        label.setText(String.valueOf(values.get(position).getSubjectname()));
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
        label.setText(String.valueOf(values.get(position).getSubjectname()));
        return label;
    }


}
