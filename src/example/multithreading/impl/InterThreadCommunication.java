package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */
/*

Thread communicate with each other using wait, notify and notifyAll method .
The Thread which is waiting for updation calls wait method then thread entered into waiting state .
The Thread which is performing updation calls the notify or notifyAll method to notify all the waiting threads to indicate
he has completed the operation .The waiting thread will get that notification and then they continue their execution with
those updated items if depend on result of first thread .

These methods are present in Object class .

why these methods in object class and not in thread class
Ans : because thread calls wait method on object which is resource object
hence it is in Object class .

We can used wait and notify methods on any object if and only if the code is in synchronized area
means thread should be owner of that object by holding lock of that object .

If a thread calls wait method on any object it immediately releases the lock of that object and entered into
waiting state ..

If thread calls notify method on any object then it releases the lock of that object but may not immediately
except wait , notify and notifyAll there is no other method where thread releases the lock .

*/

class SumCalculator extends Thread {
    int sum = 0;

    public void run() {
        synchronized (this) {
            System.out.println("child thread start calculation");
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            System.out.println("child thread trying to give notification ");
            this.notify();
        }
        //millions line of code

    }
}

public class InterThreadCommunication {
    public static void main(String args[]) throws InterruptedException {
        SumCalculator t = new SumCalculator();
        t.start();

        //t.join(); join method not recommended as it will wait till the completion of updation thread
        // though sum get calculated in the middle of the total operations
        // so we are waiting unnecessarily

        //Thread.sleep(1000);  not good way

        // t.wait(); throws IllegalMonitorStateException

        Thread.sleep(1000); // used for giving chance to child explicitly

        synchronized (t) {
            System.out.println("Main Thread trying to call wait method ");
            t.wait(2000);
        }
        System.out.println("Sum is " + t.sum);
    }
}

