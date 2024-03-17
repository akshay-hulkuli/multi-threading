package com.akshay.interthreadcommunication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocksSEC6 {
    private static int count = 0;
    // making this lock static as it is used by the static method.
    /*
        These locks can be acquired in one method and can be released in some other independent method.
     */
    private static Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++)
                count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
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

        System.out.println("The count value is : " + count);
    }
}
