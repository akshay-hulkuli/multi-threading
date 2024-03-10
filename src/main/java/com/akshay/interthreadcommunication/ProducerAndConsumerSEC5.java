package com.akshay.interthreadcommunication;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;

    private final Object lock = new Object();
    private int value = 0;


    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for consumer to consume items");
                    lock.wait();
                } else {
                    System.out.println("Adding value: " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                    //Still does further operations
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for producer to produce items");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Removing value: " + list.remove(list.size() - 1));
                    lock.notify();
                    //Still does further operations
                }
                Thread.sleep(500);
            }
        }
    }
}

public class ProducerAndConsumerSEC5 {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
