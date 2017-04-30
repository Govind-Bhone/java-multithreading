package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/29/2017.
 */

class ThreadB extends Thread {
    public int total = 0;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                total += i;
            }
            this.notify(); // IllegalMonitorException without synchronized
            System.out.println("Exiting the ThreadB...");
        }
    }
}

public class ThreadSignalingForSumCalc {

    public static void main(String args[]) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();

        /* it is not recommended as
        * 1.  if processing of b thread takes less time (performance penalty)
        * 2. what if processing of b takes more time (invalid input )
        * */
        //Thread.sleep(1000);

        /*
        calling join method also not recommended in case we are waiting for updation of some object
        for example in above case there might be chances that after for loop there is milions lines of code
        so we need to wait long time unneccesarily in that case
        * */
        //b.join();

        //b.wait();// IllegalMonitorException

        synchronized (b) {
            b.wait();
        }
        System.out.println("Total is " + b.total);
        System.out.println("Exiting the main program ....");
    }
}
