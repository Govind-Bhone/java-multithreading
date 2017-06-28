package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/27/2017.
 */

/*
Advantage of synchronized keyword is we can resolve data inconsistency problem
But disadvantage of using synchronized keyword is it increases waiting time of threads and creates
performance problem.

hence if there is no specific requirement then it is not recommended to use synchronized keyword .
*/

class Display {

    /* Generate irregular output when  same object method invoked by two threads */
    public void wish(String name) {
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


    /* Generate regular output when  same object method invoked by two threads
    * it require object level lock
    * */
    public synchronized void syncwish1(String name) {
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


    /* Generate regular output when  same object method invoked by two threads */
    public synchronized void syncwish(String name) {
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

    /* Generate regular output when  different object method invoked by two threads
    * it require class level lock
    * */
    public synchronized static void staticsyncwish(String name) {
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
}

class DisplayThread extends Thread {
    Display d;
    String name;

    public DisplayThread(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        //d.syncwish(name);
        // d.wish(name);
        //d.syncwish(name);
        d.staticsyncwish(name);//Display.staticsyncwish(name)
    }
}

class DisplayThread2 extends Thread {
    Display d;
    String name;

    public DisplayThread2(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        //d.syncwish1(name);
       // d.wish(name);
        //d.syncwish(name);
        d.staticsyncwish(name);//Display.staticsyncwish(name)
    }
}

public class SynchronizeMethodIssue {
    public static void main(String args[]) {
        Display d1 = new Display();
        Display d2 = new Display();
/*        DisplayThread t1 = new DisplayThread(d1, "dhoni");
        DisplayThread t2 = new DisplayThread(d2, "yuvraj");
        t1.start();
        t2.start();*/

        DisplayThread t1 = new DisplayThread(d1, "dhoni");
        DisplayThread2 t2 = new DisplayThread2(d2, "yuvraj");
        t1.start();
        t2.start();
    }
}
