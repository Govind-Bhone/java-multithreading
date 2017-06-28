package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */
/*

when more than one thread updating value
 */

public class SynchronizedCounter {
    public int counter = 0;

    public void increment() {
        counter = counter + 1;

    }

    public static void main(String args[]) {
        SynchronizedCounter s = new SynchronizedCounter();
        s.doJob();
    }

    public void doJob() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter is " + counter);
    }
}
