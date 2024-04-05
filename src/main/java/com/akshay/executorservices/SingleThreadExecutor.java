package com.akshay.executorservices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("task with id : " + id + " is in work - thread id : " + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SingleThreadExecutor {
    public static void main(String[] args) {
        /*
            The single thread executes sequentially 5 tasks.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task(i));
            executorService.submit(thread);
        }

        // This prevents new threads getting submitted and terminates pool after current tasks are completed
        executorService.shutdown();

        try {
            // Waits till 5 sec to complete current tasks if not forcefully shuts it down
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            e.printStackTrace();
        }
    }
}
