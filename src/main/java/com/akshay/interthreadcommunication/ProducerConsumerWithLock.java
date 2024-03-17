package com.akshay.interthreadcommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer is started...");
        condition.await();
        System.out.println("Producer is restarted...");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method is started...");
        Thread.sleep(3000);
        condition.signal();
        lock.unlock();
    }
}
