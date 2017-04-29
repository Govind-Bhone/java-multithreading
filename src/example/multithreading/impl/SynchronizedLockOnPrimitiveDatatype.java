package example.multithreading.impl;

/**
 * Created by govind.bhone on 4/28/2017.
 */

/* sync lock will work only with object and class and not work with primitive datatype
 object level lock
 class level lock
 */

class Display5 {

    int x = 5;

    /* Generate irregular output when  same object method invoked by two threads */
    public void wish() {
        //synchronized (x) { // compile time error unexpected type found int required object
            for (int i = 0; i < 10; i++) {
                System.out.print("hello :");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(x);
            }
        //}

    }
}

