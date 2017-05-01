package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/30/2017.
 */
/*
 Java MultiThreading concept is implemented using two models
1 . Green Thread Model => The thread which is completely managed by JVM without taking underline os support
2 . Native OS Model    => Threads life cycle manages by JVM with the help of underlying OS support

only few operating system like solaris support green thread model . green thread model is deprecated now .
All windows based operating system provide support for Native OS model ...

**************How to Stop a Thread *****************
we can stop thread using stop method but it is not good to kill the thread so it is deprecated method now

**************How to suspend and resume of thread ********************
we can suspend a thread using suspend method of thread class then immediately thread will be entered into
suspended state (pause step ) we can resume thread by calling resume method on that thread .
it will then make thread continue its execution
 */

class StopThread extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class GreenThread {
    public static void main(String args[]){
        StopThread t = new StopThread();
        t.start();
        System.out.println("End of main Thread");
        //t.stop();// deprecated
        System.out.println("Suspending the thread ");
        t.suspend();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resuming the thread");
        t.resume();

    }
}
