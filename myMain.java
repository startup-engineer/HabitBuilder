import java.io.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * Created by Cameron on 12/7/2015.
 */
public class myMain {
    static LinkedList<HabitItem> queueHabitItems = new LinkedList<HabitItem>();
    static Stack<HabitItem> stackHabitItems = new Stack<HabitItem>();

    static HabitItem head = new HabitItem("A");
    static HabitItem B = new HabitItem("B");
    static HabitItem C = new HabitItem("C");
    static HabitItem D = new HabitItem("D");
    static HabitItem E = new HabitItem("E");
    static HabitItem F = new HabitItem("F");
    static HabitItem G = new HabitItem("G");
    static HabitItem H = new HabitItem("H");
    static HabitItem I = new HabitItem("I");
    static HabitItem J = new HabitItem("J");
    static HabitItem K = new HabitItem("K");
    static HabitItem L = new HabitItem("L");
    static HabitItem M = new HabitItem("M");
    static HabitItem N = new HabitItem("N");
    static HabitItem O = new HabitItem("O");

    public static void hardCodeHabits() {
        head.children.add(B);
        head.children.add(C);
        head.children.add(D);
        head.children.add(E);
        B.children.add(F);
        B.children.add(G);
        B.children.add(H);
        B.children.add(I);
        C.children.add(J);
        E.children.add(K);
        I.children.add(L);
        L.children.add(M);
        L.children.add(N);
        N.children.add(O);
    }

    // BREADTH FIRST SEARCH
    public static void traverseQueue(HabitItem myHead) {
        queueHabitItems.add(myHead);
        stackHabitItems.add(myHead);
        HabitItem node;
        HabitItem insertMe;

        // Breadth first Search begins
        while (!queueHabitItems.isEmpty()) {
            node = queueHabitItems.remove();
            for (int i = 0; i < node.children.size(); i++) {
                insertMe = node.children.get(i);
                queueHabitItems.offer(insertMe);
                stackHabitItems.push(insertMe);
            }
        }
        queueHabitItems.clear(); // Just to be safe :)
    }

    // Prints from the stack, assuming it's established
    public static void outputFile(String filename) {
        File path = new File(filename);
        try (FileOutputStream fout = new FileOutputStream(path);
             PrintWriter dos = new PrintWriter(fout)) {
            while (!stackHabitItems.isEmpty()) {
                HabitItem it = stackHabitItems.pop();
                dos.println("<");

                // 1.) Print Name
                if (it.name == "") {
                    dos.println("--");
                } else {
                    dos.println(it.name);
                }

                // 2.) Print DaysCompleted (boolean array)
                for (int i = 0; i < 7; i++) {
                    if (it.isDayCompleted[i]) {
                        dos.print("true");
                    } else {
                        dos.print("false");
                    }
                    dos.println();
                }

                // 3.) Print ID
                dos.print(it.id);

                dos.println();

                // 4.) Print Children
                if (it.children.isEmpty()) {
                    dos.print("--");
                } else {
                    for (int i = 0; i < it.children.size(); i++) {
                        dos.print(it.children.get(i).id);
                        dos.print(" ");
                    }
                }
                dos.println();
                dos.println("\\>");
            }
        } catch (Exception e) {
            System.out.println("Failed to open file output.txt");
        }

        stackHabitItems.clear(); // Just to be safe :)
    }

    public static void readFile() {
        try {
            Scanner x = new Scanner(new File("output.txt"));

            LinkedList<HabitItem> catalog = new LinkedList<HabitItem>();

    
            while (x.hasNext()) {
                String myNext = x.next();
                if (myNext.equals("<")) {
                    catalog.add(new HabitItem());
                    catalog.getLast().name = x.next();
                    System.out.print("Name: ");
                    System.out.println(catalog.getLast().name);
                    for (int i = 0; i < 7; i++) {
                        catalog.getLast().isDayCompleted[i] = Boolean.parseBoolean(x.next());
                        System.out.print(i);
                        System.out.print("Day: ");
                        System.out.println(catalog.getLast().isDayCompleted[i]);
                    }
                    catalog.getLast().id = Integer.parseInt(x.next());
                    System.out.print("ID: ");
                    System.out.println(catalog.getLast().id);


                    String child = x.next();
                    while (!child.equals("\\>")) {
                        if (child.equals("--")) {
                            child = x.next();
                            continue;
                        }
                        int id = Integer.parseInt(child);



                        for (Iterator<HabitItem> i = catalog.iterator(); i.hasNext(); ) {
                            HabitItem it = i.next();

                            if (id == it.id) {
                                catalog.getLast().children.add(it);
                                System.out.print("Child: ");
                                for (int j = 0; j < catalog.getLast().children.size(); j++) {
                                    System.out.print(catalog.getLast().children.get(j).name);
                                }
                                System.out.println();
                                break;
                            }
                        }
                        child = x.next();
                    }
                }

                HabitItem MYhead = catalog.getLast();
                traverseQueue(MYhead);

                outputFile("reOutput.txt");
            }
        } catch (Exception e) {
            System.out.println("Couldn't open the file!");
        }
    }


    public static void main(String[] args) {
        hardCodeHabits();
        traverseQueue(head);
        outputFile("output.txt");
        readFile();

    }
}


