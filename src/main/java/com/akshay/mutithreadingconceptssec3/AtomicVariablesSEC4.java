package com.akshay.mutithreadingconceptssec3;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesSEC4 {

    public AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        AtomicVariablesSEC4 atomicVariablesSEC4 = new AtomicVariablesSEC4();
        Thread t1 = new Thread(atomicVariablesSEC4::increment, "Thread1");
        Thread t2 = new Thread(atomicVariablesSEC4::increment, "Thread2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The counter value after thread execution is " + atomicVariablesSEC4.counter);
    }

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            counter.getAndIncrement();
        }
    }
}
