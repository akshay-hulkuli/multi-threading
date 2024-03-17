package com.akshay.mutithreadingconceptssec3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSEC2 {

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /**
     * <ol>
     *     <li>Here we can see how a deadlock occurs.</li>
     *     <li>The thread1 has aquired lock1 and waiting for lock2</li>
     *     <li>The thread2 has aquired lock2 and waiting for lock1.</li>
     *     <li>To eliminate the deadlocks we need to ensure that the threads acquire locks in the same order.</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        DeadLockSEC2 deadLock = new DeadLockSEC2();
        Thread t1 = new Thread(() -> {
            deadLock.worker1();
        });

        Thread t2 = new Thread(() -> {
            deadLock.worker2();
        });

        t1.start();
        t2.start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquires lock1...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("Worker1 acquires lock2....");

        lock1.unlock();
        lock2.unlock();

    }

    public void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquires lock2....");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("Worker2 acquires lock1...");
        lock2.unlock();
        lock1.unlock();
    }
}
