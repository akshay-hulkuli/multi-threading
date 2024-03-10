package com.akshay.interthreadcommunication;

public class ProblemsWithSynchronization {
    private static int counter1 = 0;
    private static int counter2 = 0;

    /**
     * The synchronised keyword has a drawback.
     * The thread wants to access a synchronized block must acquire an intrinsic lock at class level.
     * Since a thread has acquired an intrinsic lock no other thread can access any other synchronised methods
     * Ex: Here if thread1 acquired lock to access increment1() then, thread2 can't access increment2()
     * It is not recommended to use synchronized keyword at method level
     */
    private static synchronized void increment1() {
        counter1++;
    }

    /**
     * Class level lock, since it is a static method.
     */
    private static void increment2() {
        synchronized (ProblemsWithSynchronization.class) {
            counter2++;
        }
    }

    /*
     * this is a object level lock since it is not a static method
         private void increment2() {
            synchronized (this) {
                counter2++;
            }
         }
     */

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
