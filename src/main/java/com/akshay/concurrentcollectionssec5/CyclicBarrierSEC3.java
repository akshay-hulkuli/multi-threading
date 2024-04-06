package com.akshay.concurrentcollectionssec5;

/*
 In the case of latch only single thread waits for others
 In case of cyclic barrier multiple threads can wait for each other
 We can reuse cyclic barrier and on the other hand we can not reuse latches.
 */

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BarrierWorker implements Runnable {

    private int id;
    private Random random;

    private CyclicBarrier cyclicBarrier;

    public BarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.random = new Random();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " + this.id + " starts the work...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Work is done at thread - " + id);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("After await - " + id);
    }
}

public class CyclicBarrierSEC3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("Starting new thread since all the operations were completed");
        });

        for (int i = 0; i < 5; i++)
            executorService.execute(new BarrierWorker(i, cyclicBarrier));

        executorService.shutdown();
    }
}
