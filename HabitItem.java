/**
 * Created by Cameron on 12/8/2015.
 */
/**
 * Created by Cameron on 12/7/2015.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Cameron on 12/7/2015.
 */

public class HabitItem  {
    public static int Counter = 0;
    String name;
    boolean isDayCompleted[] = new boolean[7];
    List<HabitItem> children = new ArrayList<HabitItem>();
    int id;


    public String getName(){
        return name;
    }
    public boolean[] getIsDayCompleted(){
        return isDayCompleted;
    }
    public void setCompleted(int index, boolean checked){
        isDayCompleted[index] = checked;
    }

    public boolean isChecked(int index){
        return isDayCompleted[index];
    }

    public HabitItem() {}

    public HabitItem(String name) {
        super();
        Counter++;
        this.id = Counter;
        this.name = name;
        for(int i = 0; i < 7; i++){
            isDayCompleted[i] = false;
        }
    }

    public HabitItem(int id) {
        super();
        Counter++;
        this.id = id;
        for(int i = 0; i < 7; i++){
            isDayCompleted[i] = false;
        }
    }





}

