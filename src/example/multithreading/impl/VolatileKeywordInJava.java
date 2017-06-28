package example.multithreading.impl;

import java.util.Scanner;

/**
 * Created by govind.bhone on 6/25/2017.
 */

/*

Volatile keyword make sure that the variable changed by thread in thread cache should be in sync with memory variable


 when single thread updating value and others are reading it



 */

class Counter2 extends Thread {
    private volatile
    boolean counting = true;
    private int counter = 1;

    public void run() {
        while (counting) {
            System.out.println(counter);
            counter++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopCounting() {
        counting = false;
    }
}

public class VolatileKeywordInJava {
    public static void main(String args[]) {
        Counter2 c = new Counter2();
        c.start();


        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        c.stopCounting();
    }
}
