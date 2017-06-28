package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/28/2017.
 */

class Display3 {

    /* sync block are performant than sync method */
    public void syncBlockwishcurrentObj(String name) {
        ;;;;;;;;;;;;;;;//hundreads lines of code
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print("hello :");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
            }
        }
              ;//hundreads lines of code
    }

    /* sync block are performant than sync method */
    public void syncBlockwishclass(String name) {
        ;//hundreads lines of code
        synchronized (Display3.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print("hello :");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
            }
        }
        ;//hundreads lines of code
    }
}

class DisplayThread3 extends Thread {
    Display3 d;
    String name;

    public DisplayThread3(Display3 d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.syncBlockwishcurrentObj(name);
        //d.wish(name);
        //d.syncwish(name);
        //d.staticsyncwish(name);//Display.staticsyncwish(name)
    }
}

class DisplayThread4 extends Thread {
    Display3 d;
    String name;

    public DisplayThread4(Display3 d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.syncBlockwishclass(name);
        //d.wish(name);
        //d.syncwish(name);
        //d.staticsyncwish(name);//Display.staticsyncwish(name)
    }
}

public class SynchronizedBlockIssue {
    public static void main(String args[]) {

        //test for regular object
        /*Display3 d = new Display3();
        DisplayThread3 t1 = new DisplayThread3(d, "dhoni");
        DisplayThread3 t2 = new DisplayThread3(d, "yuvraj");
        t1.start();
        t2.start();
*/

        //test for irregular object
    /*    Display3 d1 = new Display3();
        Display3 d2 = new Display3();
        DisplayThread3 t3 = new DisplayThread3(d1, "dhoni");
        DisplayThread3 t4 = new DisplayThread3(d2, "yuvraj");
        t3.start();
        t4.start();
        */

        //test for class level lock

        //test for irregular object
        Display3 d3 = new Display3();
        Display3 d4 = new Display3();
        DisplayThread4 t5 = new DisplayThread4(d3, "dhoni");
        DisplayThread4 t6 = new DisplayThread4(d4, "yuvraj");
        t5.start();
        t6.start();
    }
}

