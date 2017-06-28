package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/25/2017.
 */

/*

it is based on locking mechanism on object . In java every object have unique lock .
first thread got the lock on object and when other thread wanted to access that object
it will not get that lock . when first thread done with his work then he releases the lock
and other thread can get the hold on object and lock it .

If thread wants to execute synchronized method on the given object first it has to get lock of that object
once thread got the lock then it is allowed to execute any synchronized method on that object


if we defined two synchronized methods m1 and m2 in object o1
then if while executing m1 object locks get acquired then m2 also can't get executed
as m1 and m2 require lock on same objects ..


so we should go with synchronization whenever we have to do update operations (inset/update/delete/replace)
for read operation non synchronize method will work better .

 Local variables get stored on stack frame so they will not get affected by multiple threads .
 */
class State2 {
    int sum =0; // both were updating same state
    public void sum(String threadName) {
        //int sum = 0; // both were updating different variable on method stack
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println("Thread " + threadName + " print " + sum);
    }
}

class LocalVariableNotAffectedByThreads extends Thread {
    State2 s = null;

    public LocalVariableNotAffectedByThreads(State2 s) {
        this.s = s;
    }

    public void run() {
        s.sum(this.getName());
    }
}

public class SynchronizedKeyWordExample {
    public static void main(String args[]) {
        State2 s2 = new State2();
        LocalVariableNotAffectedByThreads t1 = new LocalVariableNotAffectedByThreads(s2);
        LocalVariableNotAffectedByThreads t2 = new LocalVariableNotAffectedByThreads(s2);
        t1.start();
        t2.start();
    }
}
