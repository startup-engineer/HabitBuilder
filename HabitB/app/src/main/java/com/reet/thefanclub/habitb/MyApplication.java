package com.reet.thefanclub.habitb;

import android.app.Application;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Reet Chowdhary on 12/11/2015.
 */
public class MyApplication extends Application {

    Application application = this;
    public boolean isActivityVisible(List<HabitItem> habitList) {
        /*
        if(!activityVisible){
            try {
                FileOutputStream fileOut = this
                        .openFileOutput("output", Context.MODE_APPEND);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                    out.writeObject(habitList.get(0));
                out.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        return activityVisible;
    }

    public void activityResumed(List<HabitItem> habitList) {
        /*
        activityVisible = true;
        try {
            FileInputStream fileIn = this.openFileInput("output");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HabitItem name = (HabitItem) in.readObject();
            in.close();
            fileIn.close();

            habitList.set(0, name);
            // verify the object state
            System.out.println(name.getName());
            System.out.println(name.getChild(0).getName());
            System.out.println(name.getChild(1).getName());
            System.out.println(name.getChild(2).getName());
            System.out.println(name.getChild(0).getChild(0).getName());
            System.out.println(name.getChild(0).getChild(1).getName());
            System.out.println(name.getChild(0).getChild(2).getName());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        */
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}
