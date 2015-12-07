package com.reet.thefanclub.habitb;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ListView lv;
        ArrayList<HabitItem> habitList;
        HabitAdapter hAdapter;

        lv = (ListView) rootView.findViewById(R.id.listview_forecast);

        habitList = new ArrayList<HabitItem>();
        habitList.add(new HabitItem("Guitar"));
        habitList.add(new HabitItem("Read"));
        habitList.add(new HabitItem("Exercise"));
        habitList.add(new HabitItem("Homework"));

        hAdapter = new HabitAdapter(habitList, getActivity());
        lv.setAdapter(hAdapter);
        /*
        String[] forecastArray = {
                "first thing",
                "Second thing",
                "third thing",
                "fourth thing"
        };

        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray)
        );

        ArrayAdapter<String> mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        weekForecast
                );

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        */
        return rootView;
    }

}
