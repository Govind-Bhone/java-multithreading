package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/14/2017.
 */

class Loop {
    public static void loop() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        Loop.loop();
    }
}

public class ForLoopLocalVariableExecutionUsingTwoThread {
    public static void main(String args[]){
        Thread t1 = new MyThread1();
        Thread t2 = new MyThread1();

        t1.start();
        t2.start();
    }
}
