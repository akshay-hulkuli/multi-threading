package com.akshay.virtualthreadsec11;

import java.util.concurrent.*;

public class CompletableFutureExampleSEC4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // this is a synchronous and blocking approach
        Future<String> future1 = executorService.submit(() -> {
            return "Hello World";
        });
        String value1 = future1.get();
        Future<String> future2 = executorService.submit(() -> {
            return "Hello World";
        });
        String value2 = future2.get();
        Future<String> future3 = executorService.submit(() -> {
            return "Hello World";
        });
        String value3 = future3.get();


        // Asynchronous way using completable Future
        try (ExecutorService cpuService = Executors.newFixedThreadPool(5);
             ExecutorService ioService = Executors.newFixedThreadPool(5)) {
            CompletableFuture
                    .supplyAsync(() -> "Hello World", cpuService)
                    .thenApplyAsync(s -> s + "hello", ioService)
                    .thenApply(s -> s.toUpperCase())
                    .thenAccept(System.out::println);

            CompletableFuture
                    .supplyAsync(() -> "Hello")
                    .thenCombine(CompletableFuture.supplyAsync(() -> "World"), (s1, s2) -> s1 + "-" + s2)
                    .thenApply(String::toUpperCase)
                    .thenAccept(System.out::println);
        }

    }
}
