package com.reet.thefanclub.habitb;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final ListView lv;
        final ArrayList<HabitItem> habitList;
        HabitAdapter hAdapter;

        lv = (ListView) rootView.findViewById(R.id.listview_forecast);

        habitList = new ArrayList<HabitItem>();
        habitList.add(new HabitItem("Guitar"));
        habitList.add(new HabitItem("Read"));
        habitList.add(new HabitItem("Exercise"));
        habitList.add(new HabitItem("Homework"));

        habitList.get(0).addChild(new HabitItem("Scales"));
        habitList.get(0).addChild(new HabitItem("Chords"));
        habitList.get(0).addChild(new HabitItem("Stretch"));

        habitList.get(0).getChildren().get(0).addChild(new HabitItem("1A"));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  HabitItem item = (HabitItem) habitList.get(position);
                  if(item.getChildren().size() == 0){
                      Toast toast = Toast.makeText(getContext(), "First item in " + item.getName() + " is null", Toast.LENGTH_SHORT);
                      toast.show();
                  }
                  Intent intent = new Intent(getActivity(), subActivity.class)
                          .putExtra("selectedItem", item);
                  startActivity(intent);
              }
          }

        );

        hAdapter = new HabitAdapter(habitList, getActivity());
        lv.setAdapter(hAdapter);

        return rootView;
    }

}
