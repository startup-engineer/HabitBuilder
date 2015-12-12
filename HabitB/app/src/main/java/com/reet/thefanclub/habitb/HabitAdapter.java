package com.reet.thefanclub.habitb;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.util.Swappable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reet Chowdhary on 12/5/2015.
 */

public class HabitAdapter extends ArrayAdapter<HabitItem> implements Swappable{

    private List<HabitItem> nameList;
    private Context context;

    public HabitAdapter(List<HabitItem> nameList, Context context) {
        super(context, R.layout.habit_item, nameList);
        this.nameList = nameList;
        this.context = context;
    }

    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.habit_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.list_item_forecast_textview);
        CheckBox cb0 = (CheckBox) convertView.findViewById(R.id.box0);
        CheckBox cb1 = (CheckBox) convertView.findViewById(R.id.box1);
        CheckBox cb2 = (CheckBox) convertView.findViewById(R.id.box2);
        CheckBox cb3 = (CheckBox) convertView.findViewById(R.id.box3);
        CheckBox cb4 = (CheckBox) convertView.findViewById(R.id.box4);
        CheckBox cb5 = (CheckBox) convertView.findViewById(R.id.box5);
        CheckBox cb6 = (CheckBox) convertView.findViewById(R.id.box6);

        CheckBox checkBoxes[] = {cb0, cb1, cb2, cb3, cb4, cb5, cb6};

        name.setText(nameList.get(position).getName());
        cb0.setChecked(nameList.get(position).isChecked(0));
        cb1.setChecked(nameList.get(position).isChecked(1));
        cb2.setChecked(nameList.get(position).isChecked(2));
        cb3.setChecked(nameList.get(position).isChecked(3));
        cb4.setChecked(nameList.get(position).isChecked(4));
        cb5.setChecked(nameList.get(position).isChecked(5));
        cb6.setChecked(nameList.get(position).isChecked(6));

        final int boxId[] = {R.id.box0, R.id.box1, R.id.box2, R.id.box3,
                R.id.box4, R.id.box5, R.id.box6};

        for (int i = 0; i < 7; i++) {
            final int finalI = i;
            checkBoxes[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    CheckBox cb = (CheckBox) v.findViewById(boxId[finalI]);

                    if (cb.isChecked()) {
                        nameList.get(position).setCompleted(finalI, true);
                        Toast toast = Toast.makeText(context, "You clicked " +
                                nameList.get(position).getName(), Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (!cb.isChecked()) {
                        nameList.get(position).setCompleted(finalI, false);

                    }
                }
            });
            checkBoxes[i].setChecked(nameList.get(position).isChecked(i));
        }

        return convertView;
    }

    @Override
    public void swapItems(int positionOne, int positionTwo) {
        HabitItem temp = nameList.get(positionOne);
        nameList.set(positionOne, nameList.get(positionTwo));
        nameList.set(positionTwo, temp);
    }
}
