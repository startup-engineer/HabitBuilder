package com.reet.thefanclub.habitb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class subActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i  = getIntent();
        HabitItem item = (HabitItem) i.getParcelableExtra("selectedItem");
        ListView lv = (ListView) findViewById(R.id.sub_listview);
        final List<HabitItem> habitList = item.getChildren();
        final Activity activity = this;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  HabitItem item = (HabitItem) habitList.get(position);
                  Intent intent = new Intent(activity, subActivity.class)
                          .putExtra("selectedItem", item);
                  startActivity(intent);
              }
          }

        );

        HabitAdapter hAdapter = new HabitAdapter(habitList, this);
        lv.setAdapter(hAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
