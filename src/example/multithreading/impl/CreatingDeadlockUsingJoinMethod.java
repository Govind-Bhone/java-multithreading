package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/24/2017.
 */
class JoinThread3 extends Thread {

    public static Thread mt;

    public void run() {
        try {
            mt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= 10; i++) {
            System.out.println("child thread");
        }
    }
}

public class CreatingDeadlockUsingJoinMethod {
    public static void main(String args[]) throws InterruptedException {

        JoinThread3.mt = Thread.currentThread();

        Thread t = new JoinThread3();
        t.start();
        t.join();
        for (int i = 0; i <= 10; i++) {
            System.out.println("main thread");
            Thread.sleep(1000);
        }
    }
}
