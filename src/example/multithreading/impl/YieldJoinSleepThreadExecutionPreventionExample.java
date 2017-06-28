package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/24/2017.
 */
/*
Yield ,join and sleep method are used to prevent thread execution .

Yield Method : yield method pauses to current executing thread to give chance for waiting thread
for same priority . if there is no waiting thread or all waiting thread have low priority then
same thread can continue its execution ..
  If multiple thread are waiting with same priority then which waiting thread will get the chance
we can't expect it depends on thread scheduler .

The thread which is yielded when it will get chance once again it depends on thread scheduler ..
and we can't expect exactly .

Join Method : If you want to wait your thread until completing other thread  then we can used join method .
for example , if t1 wants to wait till t2 get completed then t1 will call t2.join method .
 */

class YieldThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("yield Thread");
            Thread.yield();
        }
    }

}

class JoinThread12 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("join Thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SleepThread extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("show slide "+i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class YieldJoinSleepThreadExecutionPreventionExample {
    public static void main(String args[]) throws InterruptedException {

        //Example for Yield
        YieldThread1 y = new YieldThread1();
        y.start();
        Thread.sleep(1);

        for (int i = 0; i < 10; i++) {
            System.out.println("Yield Main Thread");
        }

       /* //Example for join
        JoinThread12 j = new JoinThread12();
        j.start();
        j.join(5000);

        for(int i=0;i<10;i++){
            System.out.println("Join Main Thread");
        }

        //Example for sleep
        SleepThread s = new SleepThread();
        s.start();
*/
    }
}
