package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */
/*
Synchronization is the only culprit to create deadlocks  .
Deadlock is not resolved but it can be prevented using some preventive measure .


1. Both syncFirst and syncLast is non synchronized no deadlock
2. syncFirst is synchronized but syncLast not synchronized  no deadlock
3. syncFirst and syncLast both synchronized deadlock

 */

class A1 {

    public synchronized  void syncFirst(B1 b) {
        System.out.println("In A1 m1 method ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.syncLast();
    }


    public synchronized void syncLast() {
        System.out.println("A1 last method");
    }
}

class B1 {

    public synchronized void syncFirst(A1 a) {
        System.out.println("In B1 m2 method ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.syncLast();
    }

    public synchronized void syncLast() {
        System.out.println("B1 last method");
    }
}

class DeadLockExample1 extends Thread {
    A1 a = new A1();
    B1 b = new B1();

    public void m1() {
        this.start();
        a.syncFirst(b);
    }

    public void run() {
        b.syncFirst(a);
    }

    public static void main(String args[]) {
        DeadLockExample1 d = new DeadLockExample1();
        d.m1();
    }
}
