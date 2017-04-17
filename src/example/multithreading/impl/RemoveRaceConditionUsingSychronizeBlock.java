package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/15/2017.
 */
public class RemoveRaceConditionUsingSychronizeBlock {

    public static class Counter {

        protected long count = 0;

        public void add(long value) {
            this.count = this.count + value;
        }

        @Override
        public String toString() {
            return String.valueOf(count);
        }
    }

    public static class MyThread extends Thread {
        Counter counter = null;

        private Integer sumLock = new Integer(1);

        public MyThread(Counter counter) {
            this.counter = counter;
        }

        public void add(Counter counter) {
            counter.add(1);
            System.out.println("Count is " + counter + " for thread " + this.getName());
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this.sumLock) {
                    if (this.getName().equals("second")) {
                        add(counter);
/*
                       try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    } else {
                        add(counter);
                   /*    try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        Counter counter = new Counter();
        Thread myThread = new MyThread(counter);
        Thread myThread2 = new MyThread(counter);
        myThread.setName("first");
        myThread2.setName("second");

        myThread.start();
        myThread2.start();
    }
}
