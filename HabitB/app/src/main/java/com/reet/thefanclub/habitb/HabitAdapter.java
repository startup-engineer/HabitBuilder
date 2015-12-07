package com.reet.thefanclub.habitb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Reet Chowdhary on 12/5/2015.
 */

class HabitItem {
    String name;
    boolean completed[] = new boolean[7];

    public String getName(){
        return name;
    }
    public boolean[] getCompleted(){
        return completed;
    }

    public boolean isChecked(int index){
        return completed[index];
    }

    public HabitItem(String name) {
        super();
        this.name = name;
        for(int i = 0; i < 7; i++){
            completed[i] = false;
        }
    }
}

public class HabitAdapter extends ArrayAdapter<HabitItem> {

    private List<HabitItem> nameList;
    private Context context;

    public HabitAdapter(List<HabitItem> nameList, Context context) {
        super(context, R.layout.list_item_forecast, nameList);
        this.nameList = nameList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item_forecast, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.list_item_forecast_textview);
        CheckBox cb0 = (CheckBox) convertView.findViewById(R.id.box0);
        CheckBox cb1 = (CheckBox) convertView.findViewById(R.id.box1);
        CheckBox cb2 = (CheckBox) convertView.findViewById(R.id.box2);
        CheckBox cb3 = (CheckBox) convertView.findViewById(R.id.box3);
        CheckBox cb4 = (CheckBox) convertView.findViewById(R.id.box4);
        CheckBox cb5 = (CheckBox) convertView.findViewById(R.id.box5);
        CheckBox cb6 = (CheckBox) convertView.findViewById(R.id.box6);

        name.setText(nameList.get(position).getName());
        cb0.setChecked(((CheckBox) convertView.findViewById(R.id.box0)).isChecked());
        cb1.setChecked(((CheckBox) convertView.findViewById(R.id.box1)).isChecked());
        cb2.setChecked(((CheckBox) convertView.findViewById(R.id.box2)).isChecked());
        cb3.setChecked(((CheckBox) convertView.findViewById(R.id.box3)).isChecked());
        cb4.setChecked(((CheckBox) convertView.findViewById(R.id.box4)).isChecked());
        cb5.setChecked(((CheckBox) convertView.findViewById(R.id.box5)).isChecked());
        cb6.setChecked(((CheckBox) convertView.findViewById(R.id.box6)).isChecked());

        return convertView;
    }
}
