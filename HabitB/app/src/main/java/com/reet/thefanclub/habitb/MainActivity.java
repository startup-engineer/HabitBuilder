package com.reet.thefanclub.habitb;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final ArrayList<HabitItem> habitList = new ArrayList<HabitItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lv;
        final HabitAdapter hAdapter;

        lv = (ListView) findViewById(R.id.listview_forecast);

        habitList.add(new HabitItem("Guitar"));
        habitList.add(new HabitItem("Read"));
        habitList.add(new HabitItem("Exercise"));
        habitList.add(new HabitItem("Homework"));

        habitList.get(0).addChild(new HabitItem("Scales"));
        habitList.get(0).addChild(new HabitItem("Chords"));
        habitList.get(0).addChild(new HabitItem("Stretch"));

        habitList.get(0).getChildren().get(0).addChild(new HabitItem("1A"));
        habitList.get(0).getChildren().get(0).addChild(new HabitItem("2"));
        habitList.get(0).getChildren().get(0).addChild(new HabitItem("3"));
        habitList.get(0).getChildren().get(0).addChild(new HabitItem("4"));

        final Activity activity = this;
        hAdapter = new HabitAdapter(habitList, this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  HabitItem item = (HabitItem) habitList.get(position);
                  if (item.getChildren().size() == 0) {
                      Toast toast = Toast.makeText(getApplicationContext(), "First item in " + item.getName() + " is null", Toast.LENGTH_SHORT);
                      toast.show();
                  }
                  Intent intent = new Intent(activity, subActivity.class)
                          .putExtra("selectedItem", item);
                  intent.putExtra("selectedPosition", position);
                  startActivityForResult(intent, 2);
              }
        });
        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(activity);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + habitList.get(position).getName());
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        habitList.remove(positionToRemove);
                        hAdapter.notifyDataSetChanged();
                    }});
                adb.show();
                return true;
            }


        });

        lv.setAdapter(hAdapter);

        final ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.setImageResource(R.drawable.fab_plus_icon);
        actionButton.setRippleEffectEnabled(true);
        actionButton.setButtonColorRipple(getResources().getColor(R.color.fab_material_blue_500));
        actionButton.show();

        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionButton.playHideAnimation();
                Intent i = new Intent(activity, UserInput.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                habitList.add(new HabitItem(result));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if(requestCode == 2){
            HabitItem result = data.getParcelableExtra("result");
            int position = data.getIntExtra("position", -1);

            habitList.get(position).setChildren(result.getChildren());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
