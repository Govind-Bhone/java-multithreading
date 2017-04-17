package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */

class Counter1{

    long count = 0;

    public volatile long count1 =0 ;

    public synchronized void add(long value){
        this.count += value;
    }

    public void add1(long value){
        this.count1 += value;
    }

    @Override
    public String toString() {
        return String.valueOf(count)+":"+String.valueOf(count1);
    }
}

class CounterThread1 extends Thread{

    protected Counter1 counter = null;

    public CounterThread1(Counter1 counter){
        this.counter = counter;
    }

    public void run() {
        for(int i=0; i<10000; i++){
            if(this.getName().equals("second")){
                counter.add(1);
                counter.add1(1);
                System.out.println("Count is " + counter + " for thread " + this.getName());
            }else {
                counter.add(1);
                counter.add1(1);
                System.out.println("Count is " + counter + " for thread " + this.getName());
            }

        }
    }
}

public class AvoidVisibilityProblemUsingVolatile {

    public static void main(String args[]){
        Counter1 counter = new Counter1();
        Thread myThread = new CounterThread1(counter);
        Thread myThread2 = new CounterThread1(counter);
        myThread.setName("first");
        myThread2.setName("second");

        myThread.start();
        myThread2.start();
    }

}
