package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/24/2017.
 */

/*
Thread can interrupt sleeping(sleep) thread and waiting thread (join) using interrupt method
   public void interrupt()
 */

class SleepingThread extends Thread {
    public void run() {
    /*    for (int i = 0; i <= 12; i++) {
            System.out.println("Sleeping the thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep Thread got interrupted");
            }
        }*/


        try {
            for (int i = 0; i <= 12; i++) {
                System.out.println("Sleeping the thread");
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            System.out.println("Sleep Thread got interrupted");
        }
    }
}


class WaitingThread extends Thread {
    static Thread mt;

    public void run() {
        try {
            for (int i = 0; i <= 12; i++) {
                System.out.println("Waiting the thread");
                mt.join();
            }
        } catch (InterruptedException e) {
            System.out.println("waiting thread got interrupted");
        }
    }
}


public class ThreadInterruption {
    public static void main(String args[]) throws InterruptedException {
        WaitingThread.mt = Thread.currentThread();

//        Thread t1 = new SleepingThread();
        Thread t2 = new WaitingThread();

  //      t1.start();
        t2.start();
    //    t1.interrupt();
        t2.interrupt();
        Thread.sleep(6000);
        System.out.println("Main Thread ");

    }
}
