package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/14/2017.
 */

/*

When you create a thread it will have its own stack created. Two threads will have two stacks and one thread
never shares its stack with other thread.

All local variables defined in your program will be allocated memory in stack (As Jatin commented, memory
here means, reference-value for objects and value for primitive types) (Each method call by a thread creates a
stack frame on its own stack). As soon as method execution is completed by this thread, stack frame will be
removed.

There is great lecture by Stanford professor in youtube which may help you in understanding this concept.

In Below example ,

public class MyThreadSafeClass
{
  public String myMethod(String field1, String field2, String field3)
  {
     return new StringBuilder(field1).append(field2).append(field3).toString();
  }
}

Here StringBuilder is created as a local variable so it will be threadsafe

But in below example,
Thread safety is issue as stringBuilder is in global state


public class History
{
  // thread-safety issues !!!!
  // In fact, here you should use a StringBuffer or some locking.
  private StringBuilder historyBuilder = new StringBuilder();

  public void saveEvent(String event)
  {
     historyBuilder.append(event).append('\n');
  }

  public String getHistoryString()
  {
     return historyBuilder.toString();
  }
}



 */
class Loop {
    public static void loop() {
        for (int i = 0; i < 100000; i++) {
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
