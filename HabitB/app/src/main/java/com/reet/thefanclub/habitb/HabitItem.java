package com.reet.thefanclub.habitb;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reet Chowdhary on 12/11/2015.
 */
class HabitItem implements Parcelable, java.io.Serializable{
    String name;
    boolean completed[] = new boolean[7];
    List<HabitItem> children = new ArrayList<HabitItem>();

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
    public void setChildren(List<HabitItem> newChildren){
        children.clear();
        for(int i = 0; i < newChildren.size(); i++)
            children.add(newChildren.get(i));
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

    private void readObject(ObjectInputStream aInputStream)
            throws ClassNotFoundException, IOException {
        // always perform the default de-serialization first
        aInputStream.defaultReadObject();

    }

    /**
     * This is the default implementation of writeObject. Customise if
     * necessary.
     */
    private void writeObject(ObjectOutputStream aOutputStream)
            throws IOException {
        // perform the default serialization for all non-transient, non-static
        // fields
        aOutputStream.defaultWriteObject();
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
