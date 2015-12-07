package com.reet.thefanclub.habitb;

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

    private static class HabitHolder {
        public TextView name;
        public CheckBox completed[] = new CheckBox[7];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        HabitHolder holder = new HabitHolder();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_forecast, null);

            holder.name = (TextView) v.findViewById(R.id.list_item_forecast_textview);
            holder.completed[0] = (CheckBox) v.findViewById(R.id.box0);
            holder.completed[1] = (CheckBox) v.findViewById(R.id.box1);
            holder.completed[2] = (CheckBox) v.findViewById(R.id.box2);
            holder.completed[3] = (CheckBox) v.findViewById(R.id.box3);
            holder.completed[4] = (CheckBox) v.findViewById(R.id.box4);
            holder.completed[5] = (CheckBox) v.findViewById(R.id.box5);
            holder.completed[6] = (CheckBox) v.findViewById(R.id.box6);
        } else {
            holder = (HabitHolder) v.getTag();
        }

        HabitItem h = nameList.get(position);
        holder.name.setText("yolo");
        holder.completed[0].setChecked(h.isChecked(0));
        holder.completed[1].setChecked(h.isChecked(1));
        holder.completed[2].setChecked(h.isChecked(2));
        holder.completed[3].setChecked(h.isChecked(3));
        holder.completed[4].setChecked(h.isChecked(4));
        holder.completed[5].setChecked(h.isChecked(5));
        holder.completed[6].setChecked(h.isChecked(6));
        holder.completed[0].setTag(h);
        holder.completed[1].setTag(h);
        holder.completed[2].setTag(h);
        holder.completed[3].setTag(h);
        holder.completed[4].setTag(h);
        holder.completed[5].setTag(h);
        holder.completed[6].setTag(h);

        return v;
    }
}
