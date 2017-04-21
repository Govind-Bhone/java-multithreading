package example.multithreading.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govind.bhone on 4/21/2017.
 */
public class SlippedConditions {
    private boolean isLocked = true;

    /*
    *create slipped conditions
     */
    public void lock() {
        synchronized (this) {
            while (isLocked) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    //do nothing, keep waiting
                }
            }

        }

        synchronized (this) {
            isLocked = true;
        }
    }

       /*
    *resolve slipped conditions
     */

    public void lock1() {
        synchronized (this) {
            while (isLocked) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    //do nothing, keep waiting
                }
            }
            isLocked = true;
        }
    }

    public synchronized void unlock() {
        isLocked = false;
        this.notify();
    }
}


//Fair Lock implementation with nested monitor lockout problem

class FairLock3 {

    public class QueueObject {
    }

    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();

        synchronized (this) {
            waitingThreads.add(queueObject);

            while (isLocked || waitingThreads.get(0) != queueObject) {

                synchronized (queueObject) {
                    try {
                        queueObject.wait();
                    } catch (InterruptedException e) {
                        waitingThreads.remove(queueObject);
                        throw e;
                    }
                }
            }
            waitingThreads.remove(queueObject);
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            QueueObject queueObject = waitingThreads.get(0);
            synchronized (queueObject) {
                queueObject.notify();
            }
        }
    }
}

/* Remove slipped condition from fair lock example */
//Fair Lock implementation with slipped conditions problem

class FairLock4{
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();

        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        boolean mustWait = true;
        while (mustWait) {

            synchronized (this) {
                mustWait = isLocked;
            }

            synchronized (queueObject) {
                if (mustWait) {
                    try {
                        queueObject.wait();
                    } catch (InterruptedException e) {
                        waitingThreads.remove(queueObject);
                        throw e;
                    }
                }
            }
        }

        synchronized (this) {
            waitingThreads.remove(queueObject);
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
    }
}

//Fair Lock implementation without nested monitor lockout problem,
//but with missed signals problem.

class FairLock5 {
    private boolean           isLocked       = false;
    private Thread            lockingThread  = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    public void lock() throws InterruptedException{
        QueueObject queueObject = new QueueObject();

        synchronized(this){
            waitingThreads.add(queueObject);
        }

        boolean mustWait = true;
        while(mustWait){


            synchronized(this){
                mustWait = isLocked || waitingThreads.get(0) != queueObject;
                if(!mustWait){
                    waitingThreads.remove(queueObject);
                    isLocked = true;
                    lockingThread = Thread.currentThread();
                    return;
                }
            }

            synchronized(queueObject){
                if(mustWait){
                    try{
                        queueObject.wait();
                    }catch(InterruptedException e){
                        waitingThreads.remove(queueObject);
                        throw e;
                    }
                }
            }
        }
    }
}