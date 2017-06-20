package example.multithreading.impl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by govind.bhone on 6/19/2017.
 */

class AcquireLockRunnable implements Runnable {
    int id;
    boolean interruptable;
    ReentrantLock lock;

    AcquireLockRunnable(ReentrantLock lock, int id) {
        this(lock, id, true);
    }

    AcquireLockRunnable(ReentrantLock lock, int id, boolean interruptable) {
        this.lock = lock;
        this.id = id;
        this.interruptable = interruptable;
    }

    public void run() {
        print("Try lock");
        try {
            if (interruptable) {
                lock.lockInterruptibly();
            } else {
                lock.lock();
            }
        } catch (InterruptedException e) {
            print("Acquiring lock failed due to " + e);
            return;
        }
        print("Got lock(" + id + ")");
        try {
            try {
                if (id == 1) {
                    Thread.sleep(3000);
                } else {
                    Thread.sleep(2500);
                }
            } catch (InterruptedException e) {
                print("Sleep interrupted");
            }
        } finally {
            lock.unlock();
            print("Unlocked(" + id + ")");
        }
    }

    static void print(String p) {
        System.out.println(Thread.currentThread().getName() + ": " + p);
    }
}

public class ReintrantInteruptLockExample {
    private static ReentrantLock lock = new ReentrantLock();
    private boolean interruptable;

    ReintrantInteruptLockExample() {
        this(true);
    }

    ReintrantInteruptLockExample(boolean interruptable) {
        this.interruptable = interruptable;
    }

    public static void main(String[] args) throws InterruptedException {
        ReintrantInteruptLockExample lockInterruptable = new ReintrantInteruptLockExample();
        lockInterruptable.lockAndInterrupt();
    }

    void lockAndInterrupt() throws InterruptedException {
        Thread firstThread = new Thread(new AcquireLockRunnable(lock, 1,
                interruptable), "Thread(1)");
        firstThread.start();
        Thread.sleep(2000);
        Thread[] others = new Thread[6];
        for (int i = 2; i < 8; i++) {
            others[i - 2]= new Thread(new AcquireLockRunnable(lock, i,
                    interruptable), "Thread(" + i + ")");
            others[i - 2].start();
        }
        AcquireLockRunnable.print("Interrupt threads");
        for (int i = 0; i < 6; i++) {
            Thread.sleep(500 * i / 2);
            AcquireLockRunnable.print("Interrupt " + others[i].getName());
            others[i].interrupt();
        }
    }
}

