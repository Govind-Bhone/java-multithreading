package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/30/2017.
 */

/*
Two threads are waiting for each other forever such type of waiting is called deadlock .
synchronized  keyword is responsible for creating deadlock . so we need to use it perfectly .
There are no resolution techniques for deadlock but prevention techniques are there .
If d1 d2 last method not synchronized no deadlock
if d1 d2 synchronized but not last method no deadlock
if d1 d2 last are synchronized then it is deadlock

long waiting of thread where waiting never ends is called deadlock
whereas long waiting of thread where waiting ends at certain point is called starvation
for example , low priority thread has to wait until completing all high priority threads
it may be long waiting but ends at certain point , which is nothing but starvation
 */

class A {
    public synchronized void d1 (B b ){
        System.out.println("Thread 1 start execution of d1 method");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread 1 trying to call b's last method");
        b.lock();
    }

    public synchronized void lock (){
        System.out.println("Inside a's lock method");
    }
}

 class B {
    public synchronized void d2 (A a ){
        System.out.println("Thread 2 start execution of d2 method");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread 2 trying to call a's last method");
        a.lock();
    }

    public synchronized void lock (){
        System.out.println("Inside b's lock method");
    }
}

class DeadThread extends Thread{
    A a = new A();
    B b = new B();

    public void m1 (){
        this.start();
        a.d1(b);
    }

    public void run(){
        b.d2(a);
    }

}

public class DeadLockExample {
    public static void main(String args[]){
        DeadThread t1 = new DeadThread();
        t1.m1();
    }
}
