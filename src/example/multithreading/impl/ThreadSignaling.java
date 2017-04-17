package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
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
