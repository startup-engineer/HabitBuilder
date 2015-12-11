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
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;

public class subActivity extends AppCompatActivity {
    final List<HabitItem> habitList = new ArrayList<HabitItem>();
    HabitItem item;
    int parentPositionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i  = getIntent();
        item = (HabitItem) i.getParcelableExtra("selectedItem");
        parentPositionSelected = i.getIntExtra("selectedPosition", -1);

        ListView lv = (ListView) findViewById(R.id.sub_listview);
        List<HabitItem> habitListTemp = item.getChildren();
        for(int j = 0; j < habitListTemp.size(); j++)
            habitList.add(habitListTemp.get(j));
        final Activity activity = this;
        final HabitAdapter hAdapter = new HabitAdapter(habitList, this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  HabitItem item = (HabitItem) habitList.get(position);
                  Intent intent = new Intent(activity, subActivity.class)
                          .putExtra("selectedItem", item);
                  intent.putExtra("selectedPosition", position);
                  startActivityForResult(intent, 2);
              }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + habitList.get(position).getName());
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        habitList.remove(positionToRemove);
                        hAdapter.notifyDataSetChanged();
                    }
                });
                adb.show();
                return true;
            }


        });
        lv.setAdapter(hAdapter);

        final ActionButton actionButton = (ActionButton) findViewById(R.id.sub_action_button);
        actionButton.setImageResource(R.drawable.fab_plus_icon);
        actionButton.setRippleEffectEnabled(true);
        actionButton.setButtonColorRipple(getResources().getColor(R.color.fab_material_blue_500));
        actionButton.show();

        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                adb.setTitle("Add Item");
                adb.setMessage("What item would you like to add?");
                final EditText input = new EditText(activity);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                adb.setView(input);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        habitList.add(new HabitItem(input.getText().toString()));
                        hAdapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        item.setChildren(habitList);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", item);
        returnIntent.putExtra("position", parentPositionSelected);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
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

}
