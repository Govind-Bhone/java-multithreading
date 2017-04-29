package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/29/2017.
 */
class X {

    //LOck on resource x
    public synchronized void m1() {

        Object y = new Object();

        //LOck on resource y
        synchronized (y) {
            Object z = new Object();

            //LOck on resource z
            synchronized (z) {

                // This code has three locks on x , y ,z
                System.out.println("Hello");
            }
        }
    }
}

public class MultipleObjectLockInJava {

    public static void main(String args[]) {
        X x = new X();
        x.m1();

    }
}