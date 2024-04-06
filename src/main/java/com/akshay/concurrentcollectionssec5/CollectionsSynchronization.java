package com.akshay.concurrentcollectionssec5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSynchronization {
    public static void main(String[] args) {
        // Using this either throws exception or wrong size will be printed
        // List<Integer> list = new ArrayList<>();

        // This synchronization is achieved using the intrinsic lock hence not that efficient
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
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
        System.out.println("The size of the list is " + list.size());
    }
}
