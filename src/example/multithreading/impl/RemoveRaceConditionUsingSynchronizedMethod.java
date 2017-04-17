package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */

class Counter{

    long count = 0;

    public synchronized void add(long value){
        this.count += value;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}

class CounterThread extends Thread{

    protected Counter counter = null;

    public CounterThread(Counter counter){
        this.counter = counter;
    }

    public void run() {
        for(int i=0; i<10; i++){
            counter.add(1);
            System.out.println("Count is " + counter + " for thread " + this.getName());
        }
    }
}

public class RemoveRaceConditionUsingSynchronizedMethod {
    public static void main(String[] args){
        Counter counter = new Counter();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
    }
}
