package com.akshay.executorservicessec4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskToBeExecuted implements Runnable {
    private int id;

    public TaskToBeExecuted(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing the task : " + id + " with the thread : " + Thread.currentThread().getName());
        long delay = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class FixedThreadPoolExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i <= 5; i++) {
            Thread thread = new Thread(new TaskToBeExecuted(i));
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
