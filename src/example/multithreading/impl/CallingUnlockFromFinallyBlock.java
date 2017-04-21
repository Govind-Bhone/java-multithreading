package example.multithreading.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by govind.bhone on 4/21/2017.
 */
public class CallingUnlockFromFinallyBlock {

    Lock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
        try {
            //do critical section code, which may throw exception
            FileReader f = new FileReader(new File("input.txt"));

        } catch (IOException ex) {
            ex.fillInStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
