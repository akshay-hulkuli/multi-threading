package com.akshay.virtualthreadsec11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicsSEC1 {
    public static void main(String[] args) {

        // Approach 1 of creating virtual threads

        var builder = Thread.ofVirtual().name("virtual-", 0);
        var t1 = builder.start(new VirtualThread());
        var t2 = builder.start(new VirtualThread());
        // virtual threads are demon threads. hence we need to make the main thread wait.
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Approach 2 of creating virtual threads
        var factory = Thread.ofVirtual().name("virtual-", 0).factory();
        Thread t3 = factory.newThread(new VirtualThread());
        Thread t4 = factory.newThread(new VirtualThread());
        Thread t5 = factory.newThread(new VirtualThread());
        t3.start();
        t4.start();
        t5.start();
        try {
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Approach 3 using executor service. - It doesn't create a pool under the hood.
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(new VirtualThread());
            executorService.submit(new VirtualThread());
            executorService.submit(new VirtualThread());
        }

        // Since we have used try block no need to join threads, main thread waits.

    }
}

class VirtualThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Started VirtualThread " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed VirtualThread " + Thread.currentThread().getName());
    }
}
