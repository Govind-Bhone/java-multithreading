package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/24/2017.
 */

class YieldThread extends Thread {

    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("child thread");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}

public class ThreadingYieldFunctionality {
    public static void main(String args[]) throws InterruptedException {
        Thread t = new YieldThread();
        t.start();
        new Thread() {
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    System.out.println("second child thread");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println("main thread");
        }
    }
}
