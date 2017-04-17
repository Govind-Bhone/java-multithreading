package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/17/2017.
 */
public class RemoveRaceConditionUsingThreadLocalVariable {
    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>();

        private ThreadLocal<String> threadLocal1 =
                new ThreadLocal<String>() {
                    @Override
                    protected String initialValue() {
                        return "This is the initial value";
                    }
                };

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(threadLocal.get());
            System.out.println(threadLocal1.get());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }


}
