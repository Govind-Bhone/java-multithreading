package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/29/2017.
 */

class ThreadB extends Thread {
    public int total = 0;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
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
        /*Below sleep is just to run the child run method before main thread wait execuction method
        * to demo signaling disaster due to loss of notification message */
       Thread.sleep(1000);

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
            b.wait(1000);
        }
        System.out.println("Total is " + b.total);
        System.out.println("Exiting the main program ....");
    }


}

/* Producer - Consumer Problem
producer thread is responsible for producing items in queue and consumer thread is responsible for to consume
items from queue . if queue is empty then consumer thread will call wait method and entered into waiting state
after producing items to the queue producer calls notify method then waiting consumer get that notification
and will ready to consume updated messages
 */
