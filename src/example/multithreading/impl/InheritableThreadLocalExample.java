package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */
public class InheritableThreadLocalExample {

    static final ThreadLocal<String> userName = new ThreadLocal<String>();
    static final ThreadLocal<String> inherited = new InheritableThreadLocal<String>();
    public static void main(String... args) {
        userName.set("Jane");
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String name = userName.get();
                System.out.println(threadName + ": Welcome " + name);
            }
        };
        run.run();
        new Thread(run).start();

        inherited.set("Jane");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String name = inherited.get();
                System.out.println(threadName +": Welcome "+name);
            }
        };
        runnable.run();
        new Thread(runnable).start();


    }

}
