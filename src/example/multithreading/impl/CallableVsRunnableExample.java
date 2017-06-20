package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/19/2017.
 */
/*
A Callable needs to implement call() method while a Runnable needs to implement run() method.
A Callable can return a value but a Runnable cannot.
A Callable can throw checked exception but a Runnable cannot.
A Callable can be used with ExecutorService#invokeXXX methods but a Runnable cannot be.
public interface Runnable {
    void run();
}

public interface Callable<V> {
    V call() throws Exception;


Runnable also used with executor service as follows
1) ExecutorService.execute(Runnable)
2) ExecutorService.submit(Runnable)
 */

import java.util.concurrent.*;
import java.util.*;

public class CallableVsRunnableExample {
    public CallableVsRunnableExample() {
        System.out.println("creating service");
        ExecutorService service = Executors.newFixedThreadPool(10);

        List<MyCallable> futureList = new ArrayList<MyCallable>();
        for (int i = 0; i < 12; i++) {
            MyCallable myCallable = new MyCallable((long) i);
            futureList.add(myCallable);
        }
        System.out.println("Start");
        try {
            List<Future<Long>> futures = service.invokeAll(futureList);
            for (Future<Long> future : futures) {
                try {
                    System.out.println("future.isDone = " + future.isDone() + ":"+future.get());
                    //System.out.println("future: call ="+future.get());
                } catch (Exception err1) {
                    err1.printStackTrace();
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        service.shutdown();
    }

    public static void main(String args[]) {
        CallableVsRunnableExample demo = new CallableVsRunnableExample();
    }

    class MyCallable implements Callable<Long> {
        Long id = 0L;

        public MyCallable(Long val) {
            this.id = val;
        }

        public Long call() {
            int a = 4, b = 1;
            System.out.println("a/b:" + (a / b));
            return Long.valueOf(a/b);
        }
    }
}

