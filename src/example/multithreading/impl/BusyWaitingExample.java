package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */

class ChatMessage {
    protected boolean hasDataToProcess = false;

    public synchronized boolean hasDataToProcess() {
        return this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasData) {
        this.hasDataToProcess = hasData;
    }
}

class T3 implements Runnable {
    ChatMessage m;
    String[] s1 = {"Hi", "How are you ?", "I am also doing fine!"};

    public T3(ChatMessage m1) {
        this.m = m1;
        new Thread(this, "Question").start();
    }

    public synchronized void process() throws InterruptedException {
        for (int i = 0; i < s1.length; i++) {
            while (m.hasDataToProcess) {

            }
            System.out.println("T3 :" + s1[i]);
            m.setHasDataToProcess(true);
        }
    }

    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T4 implements Runnable {
    ChatMessage m;
    String[] s2 = {"Hi", "I am good, what about you?", "Great!"};

    public T4(ChatMessage m2) {
        this.m = m2;
        new Thread(this, "Answer").start();
    }

    public synchronized void process() throws InterruptedException {
        for (int i = 0; i < s2.length; i++) {
            while (!m.hasDataToProcess) {

            }
            System.out.println("T4 :" + s2[i]);
            m.setHasDataToProcess(false);
        }
    }

    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BusyWaitingExample {
    public static void main(String args[]) {
        ChatMessage m = new ChatMessage();
        new T3(m);
        new T4(m);
    }
}
