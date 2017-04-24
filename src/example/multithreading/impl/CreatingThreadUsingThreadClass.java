package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/15/2017.
 */
public class CreatingThreadUsingThreadClass {

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello Thread !");
            System.out.println("This"+this.getName());
            System.out.println("This"+Thread.currentThread().getName());
        }
    }

    public static void main(String args[]) {
        Thread myThread = new MyThread();
        Thread.currentThread().setName("Govind-Main");

        System.out.println(Thread.currentThread()+ ":"+Thread.currentThread().getId());
        myThread.start();
        System.out.println(myThread.getName());

        Thread.yield();
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
            }
        };

        thread.start();

        /*Hybrid approach not recommended
        * why it works ?
        *
        * Runnable
        *    ^
        *    |
        *  Thread
        *    ^
        *    |
        *  MyThread
        *
        *    */
        Thread t2 = new Thread(myThread);
        t2.start();
    }
}
