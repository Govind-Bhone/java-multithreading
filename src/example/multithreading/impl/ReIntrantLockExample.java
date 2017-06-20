package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/19/2017.
 */
/*

In Java 5.0, a new addition called Reentrant Lock was made to enhance intrinsic locking capabilities.
Prior to this, "synchronized" and "volatile" were the means for achieving concurrency.

public synchronized void doAtomicTransfer(){
     //enter synchronized block , acquire lock over this object.
    operation1()
    operation2();
} // exiting synchronized block, release lock over this object.

Synchronized uses intrinsic locks or monitors. Every object in Java has an intrinsic lock
associated with it. Whenever a thread tries to access a synchronized block or method, it acquires the
intrinsic lock or the monitor on that object. In case of static methods, the thread acquires the lock over
the class object. An intrinsic locking mechanism is a clean approach in terms of writing code, and is pretty
good for most of the use-cases. So why do we need the additional feature of explicit locks? Let's discuss.

An intrinsic locking mechanism can have some functional limitations, such as:

1.) It is not possible to interrupt a thread waiting to acquire a lock (lock Interruptibly).

2.) It is not possible to attempt to acquire a lock without being willing to wait for it forever (try lock).

3.) Cannot implement non-block-structured locking disciplines, as intrinsic locks must be
released in the same block in which they are acquired.

1. Timed and Polled Lock Acquisition

***************************DeadLock using synchronize *************************************
public void transferMoneyWithSync(Account fromAccount, Account toAccount,
float amount) throws InsufficientAmountException {

synchronized (fromAccount) {
// acquired lock on fromAccount Object
synchronized (toAccount) {
// acquired lock on toAccount Object
if (amount > fromAccount.getCurrentAmount()) {
throw new InsufficientAmountException(
"Insufficient Balance");
} else {
fromAccount.debit(amount);
toAccount.credit(amount);
}
}
}
}

**************Avoid above problem using timed and polled lock-acquisition [trylock()]**************
public boolean transferMoneyWithTryLock(Account fromAccount,
Account toAccount, float amount) throws InsufficientAmountException, InterruptedException {
	// we are defining a stopTime
	long stopTime = System.nanoTime() + 5000;
	while (true) {
		if (fromAccount.lock.tryLock()) {
		try {
			if (toAccount.lock.tryLock()) {
			try {
				if (amount > fromAccount.getCurrentAmount()) {
						throw new InsufficientAmountException(
										"Insufficient Balance");
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}

			} finally {
				toAccount.lock.unlock();
			}
			}

		} finally {
			fromAccount.lock.unlock();
		}
	}
			if(System.nanoTime() < stopTime)
				return false;

				Thread.sleep(100);
	}//while
}


2. Incorruptible Lock Acquisition

This can be helpful when we want to send a KILL signal to all the waiting locks. Let's see one example:
Suppose we have a shared line to send messages. We would want to design it in such a way that if another thread comes
and interrupts the current thread, the lock should release and perform the exit or shut down operations to cancel the
current task.

public boolean sendOnSharedLine(String message) throws InterruptedException{
lock.lockInterruptibly();
try{
return cancellableSendOnSharedLine(message);
} finally {
lock.unlock();
}
}

private boolean cancellableSendOnSharedLine(String message){


3. Non Block Structural locking use
4. Un Fairness to process locking unorderly if required

*/

