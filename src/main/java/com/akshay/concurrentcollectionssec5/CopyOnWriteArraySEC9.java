package com.akshay.concurrentcollectionssec5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CopyOnWriteArraySEC9 {
    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray(new ArrayList<>());
        concurrentArray.simulate();
    }
}

class ReadClass implements Runnable {

    private List<Integer> list;

    public ReadClass(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(list);
        }
    }
}

class WriteClass implements Runnable {

    private List<Integer> list;
    private Random random;

    public WriteClass(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list.set(random.nextInt(list.size()), random.nextInt(10));
        }
    }
}

class ConcurrentArray {
    private List<Integer> list;
    public ConcurrentArray(List<Integer> list) {
        this.list = list;
        list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
    }

    public void simulate() {
        Thread t1 = new Thread(new WriteClass(list));
        Thread t2 = new Thread(new WriteClass(list));
        Thread t3 = new Thread(new WriteClass(list));
        Thread t4 = new Thread(new ReadClass(list));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
