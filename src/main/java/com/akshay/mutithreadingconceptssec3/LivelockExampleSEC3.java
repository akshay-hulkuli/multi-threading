package com.akshay.mutithreadingconceptssec3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExampleSEC3 {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        LivelockExampleSEC3 livelockExample = new LivelockExampleSEC3();
        Thread t1 = new Thread(livelockExample::worker1);
        Thread t2 = new Thread(livelockExample::worker2);

        t1.start();
        t2.start();
    }

    private void worker1() {
        while (true) {

            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("The worker1 acquired lock1...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The worker1 tries to acquire lock2...");
            if (lock2.tryLock()) {
                System.out.println("The worker1 acquired lock2...");
                lock2.unlock();
            } else {
                System.out.println("The worker1 cannot acquire lock2...");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    private void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker2 acquired lock2...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lock1.tryLock()) {
                System.out.println("The worker2 acquired lock1...");
                lock1.unlock();
            } else {
                System.out.println("The worker2 cannot acquire lock1...");
                continue;
            }
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }


}
