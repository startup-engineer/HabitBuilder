package com.reet.thefanclub.habitb;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDismissCallback{
    final ArrayList<HabitItem> habitList = new ArrayList<HabitItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DynamicListView lv;
        final HabitAdapter hAdapter;

        lv = (DynamicListView) findViewById(R.id.listview_forecast);

        final Activity activity = this;
        hAdapter = new HabitAdapter(habitList, this);
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(hAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                HabitItem item = (HabitItem) habitList.get(position);
                if(!item.children.isEmpty()){
                    Intent intent = new Intent(activity, subActivity.class)
                            .putExtra("selectedItem", item);
                    intent.putExtra("selectedPosition", position);
                    startActivityForResult(intent, 2);
                } else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                    adb.setTitle("Create Sublist");
                    adb.setMessage("Would you like to create a sublist for " + item.getName() + "?");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder adb2 = new AlertDialog.Builder(activity);
                            adb2.setTitle("Add Sublist Item");
                            adb2.setMessage("What item would you like to add?");
                            final EditText input = new EditText(activity);
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            adb2.setView(input);
                            adb2.setNegativeButton("Cancel", null);
                            adb2.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    habitList.get(position).addChild(new HabitItem(input.getText().toString()));
                                    hAdapter.notifyDataSetChanged();
                                }
                            });
                            adb2.show();
                        }
                    });
                    adb.show();
                }
            }
        });

        lv.enableDragAndDrop();
        lv.setDraggableManager(new TouchViewDraggableManager(R.id.list_item));
        lv.setOnItemLongClickListener(
                new DynamicListView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                                   final int position, final long id) {
                        lv.startDragging(position);
                        return true;
                    }
                }
        );

        lv.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            habitList.remove(position);
                            hAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        animationAdapter.setAbsListView(lv);

        assert animationAdapter.getViewAnimator() != null;
        animationAdapter.getViewAnimator().setInitialDelayMillis(100);

        lv.setAdapter(animationAdapter);

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

        final ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 2){
            HabitItem result = data.getParcelableExtra("result");
            int position = data.getIntExtra("position", -1);

            habitList.get(position).setChildren(result.getChildren());
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDismiss(@NonNull ViewGroup listView, @NonNull int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            habitList.remove(position);
        }
    }
}
