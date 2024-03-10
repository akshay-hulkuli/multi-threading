package com.akshay.interthreadcommunication;

public class Synchronization {

    private static int counter = 0;
    private static int synchronizedCounter = 0;


    /**
     * This is a synchronized method only one thread can execute this method at a time.
     * synchronized keyword can be applied to a method or to a block to ensure synchronization
     */
    private static synchronized void increment() {
        synchronizedCounter++;
    }

    private static void processData() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
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
    }

    public static void main(String[] args) {
        System.out.println("The initial non-synchronised counter value is : " + counter);
        System.out.println("The initial synchronised counter value is : " + synchronizedCounter);
        processData();
        System.out.println("The non-synchronised counter value after processData() method invocation : " + counter);
        System.out.println("The synchronised counter value after processData() method invocation : " + synchronizedCounter);

        /*
         * One of the example output ->
             * The initial non-synchronised counter value is : 0
             * The initial synchronised counter value is : 0
             * The non-synchronised counter value after processData() method invocation : 19963
             * The synchronised counter value after processData() method invocation : 20000
         * Here you can see direct access of memory might cause inconsistent data.
         */
    }
}
