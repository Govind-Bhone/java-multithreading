package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/30/2017.
 */

/*
The thread which is executing in background is called daemon thread .
eg . garbage collector
     attach listener
     signal dispatcher

The main objective of daemon thread is to provide support for non daemon threads
(main thread) . for example if main threads runs with low memory then jvm runs garbage collector
to destroy useless object so that no of bytes of free memory will be improved with this memory main thread
can continue its execution .
usually daemon threads have low priority but based on requirement daemon threads can run with high priority also
using public boolean isDaemon() we can check wheather it is daemon or not
using public void setDaemon(Boolean b)

we can set daemon nature before starting thread after start it will throw IllegalThreadStateException

By Default main thread always non Daemon and other threads inherit it from parent to child .

Once last non daemon thread completes all daemon threads terminated automatically .
 */

class MyDaemon extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("End of Child Thread");
    }
}
public class DaemonThread {
    public static void main(String args[]) {
        System.out.println(Thread.currentThread().isDaemon());
        //Thread.currentThread().setDaemon(true); // IllegalThreadStateException

        Thread t = new Thread();
        System.out.println(t.currentThread().isDaemon());
        t.setDaemon(true);
        System.out.println(t.isDaemon());

        MyDaemon d= new MyDaemon();
        d.setDaemon(true);
        d.start();
        System.out.println("End of main Thread");
    }
}
