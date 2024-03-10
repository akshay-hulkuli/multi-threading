package com.akshay.basics;

public class ThreadPriorityAndThreadScheduler {
    public static void main(String[] args) {
        System.out.println("The first thread to get started : " + Thread.currentThread().getName() +
                ", with priority : " + Thread.currentThread().getPriority());
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Inside high priority thread t1: " + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Inside low priority thread t2: " + i);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        System.out.println("This is the main thread running");
        /*
            The thread t1 prints all its lines
            Then the main thread prints its last line
            then thread t2 prints its lines
         */
    }
}
