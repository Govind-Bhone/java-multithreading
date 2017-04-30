package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */


/**
 *  wait ,notify , notifyAll methods are present in object class and not
 * in Thread class because thread invokes those methods on object
 * and also to call signal methods on any object compulsary thread need to be owner of that object
 * and thread will be owner of that object iff thread has lock of that object means it should be called inside
 * synchronized area
 * otherwise we will get runtime exception saying IllegalMonitorException
 * if threads calls wait method on any object it immediately releases the lock of that object
 * and entered into waiting state ...
 *
 * if thread calls notify method on any object it releases the lock of that object
 * but may not immediately
 * except wait , notify and notifyAll there is no other method where thread releases the lock
 *
 */

class Chat {
    boolean flag = false;

    public synchronized void Question(String msg) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        flag = true;
        this.notify();
    }

    public  void Answer(String msg) {
        synchronized(this) {
            if (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(msg);
            flag = false;
            this.notify();
        }

    }
}

class T1 implements Runnable {
    Chat m;
    String[] s1 = {"Hi", "How are you ?", "I am also doing fine!"};

    public T1(Chat m1) {
        this.m = m1;
        new Thread(this, "Question").start();

    }

    public void run() {
        for (int i = 0; i < s1.length; i++) {
            m.Question(s1[i]);
        }
    }
}

class T2 implements Runnable {
    Chat m;
    String[] s2 = {"Hi", "I am good, what about you?", "Great!"};

    public T2(Chat m2) {
        this.m = m2;
        new Thread(this, "Answer").start();
    }

    public void run() {
        for (int i = 0; i < s2.length; i++) {
            m.Answer(s2[i]);
        }
    }
}

public class ThreadSignaling {
    public static void main(String args[]) {
        Chat m = new Chat();
        new T1(m);
        new T2(m);
    }
}
