package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/15/2017.
 */
public class CreatingThreadUsingThreadClass {

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello Thread !");
        }
    }

    public static void main(String args[]) {
        Thread myThread = new MyThread();

        myThread.start();

        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
            }
        };

        thread.start();
    }
}
