package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */
/*
Thread interrupt will used to interrupt the thread to move into ready state for following cases
1. Sleeping Thread
2. Waiting Thread

If we call interrupt on thread which is  in running state then interrupt do the following
1. No exception thrown
2. it will not get wasted


But later when thread goes into waiting/sleeping it get used to interrupt .
 */

class Interrupted extends Thread {
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("I am lazy thread");
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }

    }
}

class Interrupted2 extends Thread{
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("I am active thread");
        }
        System.out.println("wanted to sleep now ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }

    }
}

public class ThreadInterruptExample {
    public static void main(String args[]) {

        Interrupted i = new Interrupted();
        i.start();
        i.interrupt();
        System.out.println("End of Thread ");

        Interrupted2 i2 = new Interrupted2();
        i2.start();
        i2.interrupt();
    }
}
