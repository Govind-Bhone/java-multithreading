package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/24/2017.
 */

class PriorityThread extends Thread {

    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("child thread");

        }
    }
}

public class PriorityOfThread {
    public static void main(String args[]) throws InterruptedException {
        Thread t = new PriorityThread();
        t.start();
        t.setPriority(10);

        new Thread() {
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    System.out.println("second child thread");
                }
            }
        }.start();

        for (int i = 0; i <= 10; i++) {
            System.out.println("main thread");
        }
        Thread.currentThread().setPriority(1);
    }
}
