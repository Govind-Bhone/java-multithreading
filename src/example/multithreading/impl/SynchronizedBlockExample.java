package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */
/*

1. lock on current object
synchronized (this){

}

2. lock on particular object b

synchronized(b){

}

3. lock on class (class level lock)

synchronized(Object.class){

}


we can't use primitive data types as mutex variable for synchronized block .
lock concept applicable for object type and class types .
but not for primitive types .hence it will not work
 */

class Display21 {

    /* sync block with primitive data types  */
    public void syncBlockwishcurrentObj(String name) {
        ;//hundreads lines of code
        int x = 10;
        //   synchronized (x) {
        for (int i = 0; i < 10; i++) {
            System.out.print("hello :");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }
        //  }
        ;//hundreads lines of code
    }

    /*method with multiple synchronized block */

    public void multipleLock() {
        Object a = new Object();
        synchronized (a) {
            Object b = new Object();
            synchronized (b) {
                Object c = new Object();
                synchronized (c) {
                    //critical section code
                }
            }
        }
    }

}

