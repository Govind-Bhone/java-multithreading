package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */

/*

The while loop is also a nice solution if you have multiple threads waiting, which are all awakened using notifyAll(),
but only one of them should be allowed to continue. Only one thread at a time will be able to obtain the lock on the
monitor object, meaning only one thread can exit the wait() call and clear the wasSignalled flag. Once this thread then
exits the synchronized block in the doWait() method, the other threads can exit the wait() call and check the wasSignalled
member variable inside the while loop. However, this flag was cleared by the first thread waking up, so the rest of the
awakened threads go back to waiting, until the next signal arrives.
 */

public class SignalToAvoidSpuriousWakeup {
    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait() {
        synchronized (myMonitorObject) {
            while (!wasSignalled) {
                try {
                    myMonitorObject.wait();
                } catch (InterruptedException e) {}
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}
