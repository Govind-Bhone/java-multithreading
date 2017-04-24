package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/15/2017.
 */
public class CreatingThreadUsingRunnableInterface {

    public static class MyRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("Hello Runnable !");
            Thread t = Thread.currentThread();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Thread myThread = new Thread(new MyRunable(),"MyRunnable");
        myThread.getName();
        myThread.setName("MyRunnable");
        myThread.start();
        System.out.println(myThread.getState());
        System.out.println(myThread.isAlive());
        myThread.stop();
        //Thread.sleep(5000);
        //myThread.start();


        Runnable myRunnable = new Runnable(){

            public void run(){
                System.out.println("Runnable running");
            }
        };

        Thread thread = new Thread(myRunnable);
        thread.start();

        myRunnable.run();
        //myRunnable.start();
    }
}
