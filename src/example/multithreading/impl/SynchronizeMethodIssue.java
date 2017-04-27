package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/27/2017.
 */

class Display {
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
}

class DisplayThread extends Thread {
    Display d;
    String name;

    public DisplayThread(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.syncwish(name);
        //d.wish(name);
    }
}

public class SynchronizeMethodIssue {
    public static void main(String args[]) {
        Display d = new Display();
        DisplayThread t1 = new DisplayThread(d, "dhoni");
        DisplayThread t2 = new DisplayThread(d, "yuvraj");
        t1.start();
        t2.start();
    }
}
