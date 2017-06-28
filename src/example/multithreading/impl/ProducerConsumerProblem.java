package example.multithreading.impl;

import java.util.*;

/**
 * Created by govind.bhone on 6/25/2017.
 */

class QueueResource {
    Queue<Integer> queue = new PriorityQueue<>();
    boolean flag = false;

    public synchronized void produce() throws InterruptedException {
        if (flag) {
            this.wait();
        }
        Integer i = (new Random()).nextInt(10000);
        System.out.println("Queue is producing element" + i);
        queue.add(i);
        flag = true;
        this.notify();
    }

    public synchronized void consume() throws InterruptedException {
        if (!flag) {
             this.wait();
        }
        System.out.println("Queue is consuming element " + queue.remove());
        flag=false;
        this.notify();

    }
}

class Producer extends Thread{
    QueueResource queue =null;

    Producer(QueueResource queue){
        this.queue=queue;

    }

    public void run(){
        try {
            for(int i=0;i<10;i++){
                queue.produce();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread{
    QueueResource queue =null ;
    Consumer (QueueResource queue ){
        this.queue=queue;
    }

    public void run(){
        try {
            for(int i=0;i<10;i++){
                queue.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumerProblem {
    public static void main(String args[]){
        QueueResource queue = new QueueResource();
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);
        p.start();
        c.start();
    }
}
