package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/21/2017.
 */
//lock implementation with nested monitor lockout problem

public class NestedMonitorLockOutProblem{
    protected MonitorObject monitorObject = new MonitorObject();
    protected boolean isLocked = false;

    public void lock() throws InterruptedException{
        synchronized(this){
            while(isLocked){
                synchronized(this.monitorObject){
                    this.monitorObject.wait();
                }
            }
            isLocked = true;
        }
    }

    public void unlock(){
        synchronized(this){
            this.isLocked = false;
            synchronized(this.monitorObject){
                this.monitorObject.notify();
            }
        }
    }
}