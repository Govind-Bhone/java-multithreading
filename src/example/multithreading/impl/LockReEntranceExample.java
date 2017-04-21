package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/21/2017.
 */
public class LockReEntranceExample {
    /* Lock Entrace basic example */
    public class Reentrant{

        public synchronized void outer(){
            inner();
        }

        public synchronized void inner(){
            //do something
        }
    }


    /* Lock which is not reentrant */
    public class Lock{

        boolean isLocked = false;

        public synchronized void lock()
                throws InterruptedException{
            while(isLocked){
                wait();
            }
            isLocked = true;
        }

    }

    /*Lock which is reentrant */

    public class Lock2{

        boolean isLocked = false;
        Thread  lockedBy = null;
        int     lockedCount = 0;

        public synchronized void lock()
                throws InterruptedException{
            Thread callingThread = Thread.currentThread();
            while(isLocked && lockedBy != callingThread){
                wait();
            }
            isLocked = true;
            lockedCount++;
            lockedBy = callingThread;
        }


        public synchronized void unlock(){
            if(Thread.currentThread() == this.lockedBy){
                lockedCount--;

                if(lockedCount == 0){
                    isLocked = false;
                    notify();
                }
            }
        }
    }
}
