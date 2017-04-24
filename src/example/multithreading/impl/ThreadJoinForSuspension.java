package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/24/2017.
 */

class JoinThread extends Thread {

    public void run() {
        for (int i = 0; i <= 20; i++) {
            System.out.println("child thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadJoinForSuspension {
    public static void main(String args[]) throws InterruptedException {
        Thread t = new JoinThread();
        t.start();
        t.join(5000);
        t.join(3000,300);
        t.join();
        for (int i = 0; i <= 10; i++) {
            System.out.println("main thread");
        }
    }
}
