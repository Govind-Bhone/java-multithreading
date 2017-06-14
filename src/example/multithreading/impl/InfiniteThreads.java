package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/14/2017.
 */
public class InfiniteThreads {
    public static int cnt=0;
    public static void main(String[] args) {
        while (true) {
            new MyThread().start();
        }
    }

}

class MyThread extends Thread{
    @Override
    public void run() {
        try {
            sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(InfiniteThreads.cnt++);
    }
}
