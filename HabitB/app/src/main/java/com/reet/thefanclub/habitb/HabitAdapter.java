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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reet Chowdhary on 12/5/2015.
 */

class HabitItem implements Parcelable{
    String name;
    boolean completed[] = new boolean[7];
    List<HabitItem> children = new ArrayList<HabitItem>();

    /*
    public HabitItem(Parcel in) {
        this.name = in.readString();

        boolean array[] = new boolean[7];
        in.readBooleanArray(array);

        for(int i = 0; i < 7; i++){
            this.completed[i] = array[i];
        }

        in.readList(children, List.class.getClassLoader());
    */

    public HabitItem(String name) {
        this.name = name;
        for(int i = 0; i < 7; i++){
            completed[i] = false;
        }
    }

    public String getName(){
        return name;
    }
    public boolean[] getCompleted(){
        return completed;
    }
    public void setCompleted(int index, boolean checked){
        completed[index] = checked;
    }
    public void addChild(HabitItem child){
        children.add(child);
    }
    public void addChildren(List<HabitItem> children){
        for(int i = 0; i < children.size(); i++)
            addChild(children.get(i));
    }
    public HabitItem getChild(int index){
        return children.get(index);
    }
    public List<HabitItem> getChildren(){
        return children;
    }
    public boolean isChecked(int index){
        return completed[index];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeBooleanArray(completed);
        dest.writeTypedList(children);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public HabitItem createFromParcel(Parcel in) {
            HabitItem item = new HabitItem(in.readString());
            boolean array[] = new boolean[7];
            in.readBooleanArray(array);
            for(int i = 0; i < 7; i++)
                item.setCompleted(i, array[i]);
            List<HabitItem> list = new ArrayList<HabitItem>();
            in.readTypedList(list, HabitItem.CREATOR);
            item.addChildren(list);
            return item;
        }

        public HabitItem[] newArray(int size) {
            return new HabitItem[size];
        }
    };
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        for(int i = 0; i < 7; i++){
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
                        // do some operations here
                    }
                }
            });
            checkBoxes[i].setChecked(nameList.get(position).isChecked(i));
        }
        return convertView;
    }
}
