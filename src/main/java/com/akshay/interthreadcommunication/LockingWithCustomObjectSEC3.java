package com.akshay.interthreadcommunication;

public class LockingWithCustomObjectSEC3 {
    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    private static void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void main(String[] arg) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment1();
                increment2();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
                increment1();
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

        System.out.println("Counter1 : " + counter1 + ", Counter2: " + counter2);
    }
}
