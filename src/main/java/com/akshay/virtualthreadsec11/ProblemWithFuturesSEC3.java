package com.akshay.virtualthreadsec11;

import java.util.concurrent.*;

public class ProblemWithFuturesSEC3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("Submitting the callable thread");
        Future<String> future = executorService.submit(new FuturesTask());
        while(!future.isDone()) {
            System.out.println("Main thread is waiting");
        }
        // Blocks the main thread
        String result = future.get();
        System.out.println(result);
    }
}

class FuturesTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Executing FuturesTask");
        Thread.sleep(5000);
        System.out.println("Finished Executing FuturesTask");
        return "Executed FuturesTask";
    }
}
