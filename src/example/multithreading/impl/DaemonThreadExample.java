package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/19/2017.
 */
/*
Daemon thread in java is a service provider thread that provides services to the user thread. Its life depend
 on the mercy of user threads i.e. when all the user threads dies, JVM terminates this thread automatically.

There are many java daemon threads running automatically e.g. gc, finalizer etc.


once thread started we can't make it as daemon thread else
it will give IllegalThreadStateException

 */


public class DaemonThreadExample extends Thread {
    public void run() {
        if (Thread.currentThread().isDaemon()) {//checking for daemon thread
            System.out.println("daemon thread work");
        } else {
            System.out.println("user thread work");
        }
    }

    public static void main(String[] args) {
        DaemonThreadExample t1 = new DaemonThreadExample();//creating thread
        DaemonThreadExample t2 = new DaemonThreadExample();
        DaemonThreadExample t3 = new DaemonThreadExample();

        t1.setDaemon(true);//now t1 is daemon thread

        t1.start();//starting threads
        t2.start();
        t3.start();
    }
}