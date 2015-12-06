package com.reet.thefanclub.habitb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<HabitItem> habitList;
    HabitAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview_forecast);
        displayHabitList();
    }

    private void displayHabitList(){
        habitList = new ArrayList<HabitItem>();
        habitList.add(new HabitItem("Guitar"));
        habitList.add(new HabitItem("Read"));
        habitList.add(new HabitItem("Shut the fuck up"));
        habitList.add(new HabitItem("Snort dat coke"));

        hAdapter = new HabitAdapter(habitList, this);
        ListView listView = (ListView) findViewById(R.id.listview_forecast);
        listView.setAdapter(hAdapter);
    }
}
